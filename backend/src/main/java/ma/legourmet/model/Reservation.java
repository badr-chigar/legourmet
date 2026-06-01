package ma.legourmet.model;
import javax.persistence.*;
import java.time.LocalDate;
@Entity @Table(name="reservations")
public class Reservation {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    private String clientNom;
    private String telephone;
    private LocalDate date;
    private String heure;
    private int couverts;
    @ManyToOne @JoinColumn(name="table_id") private RestaurantTable table;
    private String statut = "CONFIRMEE"; // CONFIRMEE | ANNULEE | HONOREE
    public Long getId(){return id;} public void setId(Long i){id=i;}
    public String getClientNom(){return clientNom;} public void setClientNom(String n){clientNom=n;}
    public String getTelephone(){return telephone;} public void setTelephone(String t){telephone=t;}
    public LocalDate getDate(){return date;} public void setDate(LocalDate d){date=d;}
    public String getHeure(){return heure;} public void setHeure(String h){heure=h;}
    public int getCouverts(){return couverts;} public void setCouverts(int c){couverts=c;}
    public RestaurantTable getTable(){return table;} public void setTable(RestaurantTable t){table=t;}
    public String getStatut(){return statut;} public void setStatut(String s){statut=s;}
}
