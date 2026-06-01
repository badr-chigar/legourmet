package ma.legourmet.controller;
import ma.legourmet.model.Reservation;
import ma.legourmet.repository.ReservationRepository;
import ma.legourmet.repository.RestaurantTableRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api")
public class ReservationController {
    private final ReservationRepository resaRepo;
    private final RestaurantTableRepository tableRepo;
    public ReservationController(ReservationRepository r, RestaurantTableRepository t){resaRepo=r; tableRepo=t;}
    @GetMapping("/tables") public List<ma.legourmet.model.RestaurantTable> tables(){ return tableRepo.findAll(); }
    @GetMapping("/reservations") public List<Reservation> all(){ return resaRepo.findAll(); }
    @PostMapping("/reservations") public Reservation create(@RequestBody Reservation r){ return resaRepo.save(r); }
    @PatchMapping("/reservations/{id}/statut")
    public Reservation statut(@PathVariable Long id,@RequestBody java.util.Map<String,String> b){
        Reservation r = resaRepo.findById(id).orElseThrow(); r.setStatut(b.get("statut")); return resaRepo.save(r);
    }
}
