package ma.legourmet.model;
import javax.persistence.*;
@Entity @Table(name="ingredients")
public class Ingredient {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    private String nom;
    private double stock;
    private String unite;
    private double seuil;
    public Long getId(){return id;} public void setId(Long i){id=i;}
    public String getNom(){return nom;} public void setNom(String n){nom=n;}
    public double getStock(){return stock;} public void setStock(double s){stock=s;}
    public String getUnite(){return unite;} public void setUnite(String u){unite=u;}
    public double getSeuil(){return seuil;} public void setSeuil(double s){seuil=s;}
}
