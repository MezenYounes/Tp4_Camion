package metier.entities;

import java.io.Serializable;
public class Camion implements Serializable{
private Long idCamion;
private String nomCamion;
private double prix;
public Camion() {
super();
}
public Camion(String nomCamion, double prix) {
super();
this.nomCamion = nomCamion;
this.prix = prix;
}
public Long getIdCamion() {
return idCamion;
}
public void setIdCamion(Long idCamion) {
this.idCamion = idCamion;
}
public String getNomCamion() {
return nomCamion;
}
public void setNomCamion(String nomCamion) {
this.nomCamion = nomCamion;
}
public double getPrix() {
return prix;
}
public void setPrix(double prix) {
this.prix = prix;
}
}
