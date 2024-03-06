 package web;
import java.util.ArrayList;
import java.util.List;
import metier.entities.Camion;
public class CamionModele {
private String motCle;
List<Camion> camions = new ArrayList<>();
public String getMotCle() {
return motCle;
}
public void setMotCle(String motCle) {
this.motCle = motCle;
}
public List<Camion> getCamions() {
return camions;
}
public void setCamions(List<Camion> camions) {
this.camions = camions;
}
}