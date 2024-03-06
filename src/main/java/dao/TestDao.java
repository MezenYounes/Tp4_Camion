package dao;

import java.util.List;
import metier.entities.Camion;
public class TestDao {
public static void main(String[] args) {
	CamionDaoImpl pdao= new CamionDaoImpl();
	Camion prod= pdao.save(new Camion("izuzu",2800));
System.out.println(prod);
List<Camion> prods =pdao.camionsParMC("HP");
for (Camion p : prods)
System.out.println(p);
}
}

