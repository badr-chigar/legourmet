package ma.legourmet.repository;
import ma.legourmet.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CommandeRepository extends JpaRepository<Commande, Long> {}
