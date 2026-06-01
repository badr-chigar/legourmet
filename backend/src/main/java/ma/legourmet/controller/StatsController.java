package ma.legourmet.controller;
import ma.legourmet.repository.*;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
@RestController @RequestMapping("/api/stats")
public class StatsController {
    private final CommandeRepository cmdRepo; private final ReservationRepository resaRepo;
    private final PlatRepository platRepo; private final IngredientRepository ingRepo;
    public StatsController(CommandeRepository c, ReservationRepository r, PlatRepository p, IngredientRepository i){
        cmdRepo=c; resaRepo=r; platRepo=p; ingRepo=i;
    }
    @GetMapping public Map<String,Object> stats(){
        Map<String,Object> m = new LinkedHashMap<>();
        m.put("commandes", cmdRepo.count());
        m.put("reservations", resaRepo.findAll().stream().filter(r -> r.getDate()!=null && !r.getDate().isBefore(LocalDate.now())).count());
        m.put("plats", platRepo.count());
        double ca = cmdRepo.findAll().stream().filter(c->"PAYEE".equals(c.getStatut())||"SERVIE".equals(c.getStatut())).mapToDouble(c->c.getTotal()).sum();
        // inclure les commandes en cours dans le CA estimé du jour
        double caJour = cmdRepo.findAll().stream().mapToDouble(c->c.getTotal()).sum();
        m.put("chiffreAffaires", Math.round(caJour*100.0)/100.0);
        long rupture = ingRepo.findAll().stream().filter(i->i.getStock()<=i.getSeuil()).count();
        m.put("ingredientsBas", rupture);
        return m;
    }
}
