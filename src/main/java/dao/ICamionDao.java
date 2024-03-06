package dao;
import java.util.List;
import metier.entities.Camion;
public interface ICamionDao {
public Camion save(Camion p);
public List<Camion> camionsParMC(String mc);
public Camion getCamion(Long id);
public Camion updateCamion(Camion p);
public void deleteCamion(Long id);
}
