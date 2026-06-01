package ma.legourmet.dto;
import java.util.List;
public class CommandeRequest {
    private String tableNumero; private List<LigneRequest> lignes;
    public String getTableNumero(){return tableNumero;} public void setTableNumero(String t){tableNumero=t;}
    public List<LigneRequest> getLignes(){return lignes;} public void setLignes(List<LigneRequest> l){lignes=l;}
}
