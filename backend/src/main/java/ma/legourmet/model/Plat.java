package ma.legourmet.model;
import javax.persistence.*;
@Entity @Table(name="plats")
public class Plat {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    private String nom;
    @Column(length=600) private String description;
    private double prix;
    private String categorie;   // PIZZA | PATES | ENTREE | DESSERT | BOISSON
    private boolean disponible = true;
    public Long getId(){return id;} public void setId(Long i){id=i;}
    public String getNom(){return nom;} public void setNom(String n){nom=n;}
    public String getDescription(){return description;} public void setDescription(String d){description=d;}
    public double getPrix(){return prix;} public void setPrix(double p){prix=p;}
    public String getCategorie(){return categorie;} public void setCategorie(String c){categorie=c;}
    public boolean isDisponible(){return disponible;} public void setDisponible(boolean d){disponible=d;}
}
