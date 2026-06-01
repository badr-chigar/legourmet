package ma.legourmet.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
@Entity @Table(name="lignes_commande")
public class LigneCommande {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    private String platNom;
    private double prix;
    private int quantite;
    @ManyToOne @JoinColumn(name="commande_id") @JsonIgnore private Commande commande;
    public Long getId(){return id;} public void setId(Long i){id=i;}
    public String getPlatNom(){return platNom;} public void setPlatNom(String p){platNom=p;}
    public double getPrix(){return prix;} public void setPrix(double p){prix=p;}
    public int getQuantite(){return quantite;} public void setQuantite(int q){quantite=q;}
    public Commande getCommande(){return commande;} public void setCommande(Commande c){commande=c;}
}
