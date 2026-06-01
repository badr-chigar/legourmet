package ma.legourmet.model;
import javax.persistence.*;
@Entity @Table(name="tables_resto")
public class RestaurantTable {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    private String numero;
    private int couverts;
    private String zone;   // SALLE | TERRASSE | BAR
    public Long getId(){return id;} public void setId(Long i){id=i;}
    public String getNumero(){return numero;} public void setNumero(String n){numero=n;}
    public int getCouverts(){return couverts;} public void setCouverts(int c){couverts=c;}
    public String getZone(){return zone;} public void setZone(String z){zone=z;}
}
