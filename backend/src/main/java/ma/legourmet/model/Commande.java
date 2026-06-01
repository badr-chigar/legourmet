package ma.legourmet.model;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Entity @Table(name="commandes")
public class Commande {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    private String reference;
    private String tableNumero;
    private double total;
    private String statut = "RECUE"; // RECUE | EN_PREPARATION | SERVIE | PAYEE
    private LocalDateTime creeLe = LocalDateTime.now();
    @OneToMany(mappedBy="commande", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<LigneCommande> lignes = new ArrayList<>();
    public Long getId(){return id;} public void setId(Long i){id=i;}
    public String getReference(){return reference;} public void setReference(String r){reference=r;}
    public String getTableNumero(){return tableNumero;} public void setTableNumero(String t){tableNumero=t;}
    public double getTotal(){return total;} public void setTotal(double t){total=t;}
    public String getStatut(){return statut;} public void setStatut(String s){statut=s;}
    public LocalDateTime getCreeLe(){return creeLe;} public void setCreeLe(LocalDateTime d){creeLe=d;}
    public List<LigneCommande> getLignes(){return lignes;} public void setLignes(List<LigneCommande> l){lignes=l;}
}
