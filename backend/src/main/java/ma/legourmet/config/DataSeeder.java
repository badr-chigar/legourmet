package ma.legourmet.config;

import ma.legourmet.model.*;
import ma.legourmet.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class DataSeeder implements CommandLineRunner {
    private final PlatRepository platRepo; private final RestaurantTableRepository tableRepo;
    private final ReservationRepository resaRepo; private final IngredientRepository ingRepo;
    private final CommandeRepository cmdRepo;
    public DataSeeder(PlatRepository p, RestaurantTableRepository t, ReservationRepository r, IngredientRepository i, CommandeRepository c){
        platRepo=p; tableRepo=t; resaRepo=r; ingRepo=i; cmdRepo=c;
    }
    @Override public void run(String... a){
        if (platRepo.count() > 0) return;
        plat("Margherita","Sauce tomate, mozzarella, basilic frais","PIZZA",65);
        plat("Quattro Formaggi","Quatre fromages italiens fondants","PIZZA",85);
        plat("Pepperoni","Pepperoni piquant, mozzarella","PIZZA",80);
        plat("Calzone","Pizza pliée jambon-champignons","PIZZA",78);
        plat("Spaghetti Bolognese","Sauce bolognaise maison","PATES",70);
        plat("Penne Arrabbiata","Sauce tomate épicée, ail","PATES",62);
        plat("Bruschetta","Pain grillé, tomates, basilic","ENTREE",35);
        plat("Salade César","Poulet, parmesan, croûtons","ENTREE",45);
        plat("Tiramisu","Dessert italien au café","DESSERT",40);
        plat("Panna Cotta","Crème vanille, coulis fruits rouges","DESSERT",38);
        plat("Limonade maison","Citron pressé, menthe","BOISSON",20);
        plat("Café espresso","Café italien","BOISSON",15);

        table("T1",2,"SALLE"); table("T2",4,"SALLE"); table("T3",4,"TERRASSE");
        table("T4",6,"TERRASSE"); table("T5",2,"BAR"); table("T6",8,"SALLE");

        ing("Farine",50,"kg",10); ing("Mozzarella",18,"kg",5); ing("Sauce tomate",22,"L",6);
        ing("Pepperoni",4,"kg",3); ing("Basilic",2,"botte",2); ing("Café",6,"kg",2);

        RestaurantTable t2 = tableRepo.findAll().get(1);
        resa("Karim Bennani","0661-223344", LocalDate.now(), "20:00", 4, t2);
        resa("Sofia El Amrani","0677-889900", LocalDate.now().plusDays(1), "13:30", 2, tableRepo.findAll().get(0));

        // commande de démo
        Commande c = new Commande();
        c.setReference("CMD-"+System.currentTimeMillis()); c.setTableNumero("T2"); c.setStatut("EN_PREPARATION");
        LigneCommande l1 = new LigneCommande(); l1.setPlatNom("Margherita"); l1.setPrix(65); l1.setQuantite(2); l1.setCommande(c);
        LigneCommande l2 = new LigneCommande(); l2.setPlatNom("Limonade maison"); l2.setPrix(20); l2.setQuantite(2); l2.setCommande(c);
        c.getLignes().add(l1); c.getLignes().add(l2); c.setTotal(65*2+20*2);
        cmdRepo.save(c);
    }
    private void plat(String nom,String desc,String cat,double prix){
        Plat p=new Plat(); p.setNom(nom); p.setDescription(desc); p.setCategorie(cat); p.setPrix(prix); platRepo.save(p);
    }
    private void table(String num,int couv,String zone){
        RestaurantTable t=new RestaurantTable(); t.setNumero(num); t.setCouverts(couv); t.setZone(zone); tableRepo.save(t);
    }
    private void ing(String nom,double stock,String unite,double seuil){
        Ingredient i=new Ingredient(); i.setNom(nom); i.setStock(stock); i.setUnite(unite); i.setSeuil(seuil); ingRepo.save(i);
    }
    private void resa(String nom,String tel,LocalDate d,String h,int couv,RestaurantTable t){
        Reservation r=new Reservation(); r.setClientNom(nom); r.setTelephone(tel); r.setDate(d); r.setHeure(h); r.setCouverts(couv); r.setTable(t); resaRepo.save(r);
    }
}
