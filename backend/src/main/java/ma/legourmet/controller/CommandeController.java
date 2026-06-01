package ma.legourmet.controller;
import ma.legourmet.dto.CommandeRequest;
import ma.legourmet.dto.LigneRequest;
import ma.legourmet.model.*;
import ma.legourmet.repository.CommandeRepository;
import ma.legourmet.repository.PlatRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
@RestController @RequestMapping("/api/commandes")
public class CommandeController {
    private final CommandeRepository repo; private final PlatRepository platRepo;
    public CommandeController(CommandeRepository r, PlatRepository p){repo=r; platRepo=p;}
    @GetMapping public List<Commande> all(){ return repo.findAll(); }
    @PostMapping public ResponseEntity<?> create(@RequestBody CommandeRequest req){
        if (req.getLignes()==null || req.getLignes().isEmpty())
            return ResponseEntity.badRequest().body(Map.of("error","Commande vide"));
        Commande c = new Commande();
        c.setReference("CMD-"+System.currentTimeMillis()); c.setTableNumero(req.getTableNumero());
        double total=0;
        for (LigneRequest lr : req.getLignes()){
            Plat p = platRepo.findById(lr.getPlatId()).orElse(null); if (p==null) continue;
            LigneCommande l = new LigneCommande(); l.setPlatNom(p.getNom()); l.setPrix(p.getPrix());
            l.setQuantite(lr.getQuantite()); l.setCommande(c); c.getLignes().add(l);
            total += p.getPrix()*lr.getQuantite();
        }
        c.setTotal(total); return ResponseEntity.ok(repo.save(c));
    }
    @PatchMapping("/{id}/statut")
    public Commande statut(@PathVariable Long id,@RequestBody Map<String,String> b){
        Commande c = repo.findById(id).orElseThrow(); c.setStatut(b.get("statut")); return repo.save(c);
    }
}
