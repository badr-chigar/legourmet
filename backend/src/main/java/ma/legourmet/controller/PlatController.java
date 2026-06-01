package ma.legourmet.controller;
import ma.legourmet.model.Plat;
import ma.legourmet.repository.PlatRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/plats")
public class PlatController {
    private final PlatRepository repo;
    public PlatController(PlatRepository r){repo=r;}
    @GetMapping public List<Plat> all(){ return repo.findAll(); }
    @PostMapping public Plat create(@RequestBody Plat p){ return repo.save(p); }
    @PutMapping("/{id}") public ResponseEntity<Plat> update(@PathVariable Long id,@RequestBody Plat d){
        return repo.findById(id).map(p->{ p.setNom(d.getNom()); p.setDescription(d.getDescription());
            p.setPrix(d.getPrix()); p.setCategorie(d.getCategorie()); p.setDisponible(d.isDisponible());
            return ResponseEntity.ok(repo.save(p)); }).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}") public ResponseEntity<Void> del(@PathVariable Long id){ repo.deleteById(id); return ResponseEntity.noContent().build(); }
}
