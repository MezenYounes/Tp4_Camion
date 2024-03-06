package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import metier.SingletonConnection;
import metier.entities.Camion;
public class CamionDaoImpl implements ICamionDao {
@Override
public Camion save(Camion p) {
Connection conn=SingletonConnection.getConnection();
 try {
PreparedStatement ps= conn.prepareStatement("INSERT INTO CAMIONS(NOM_CAMION,PRIX) VALUES(?,?)");
ps.setString(1, p.getNomCamion());
ps.setDouble(2, p.getPrix());
ps.executeUpdate();
PreparedStatement ps2= conn.prepareStatement("SELECT MAX(ID_CAMION) as MAX_ID FROM CAMIONS");
ResultSet rs =ps2.executeQuery();
if (rs.next()) {
p.setIdCamion(rs.getLong("MAX_ID"));
}
ps.close();
ps2.close();
} catch (SQLException e) {
e.printStackTrace();
}
return p;
}
@Override
public List<Camion> camionsParMC(String mc) {
 List<Camion> prods= new ArrayList<Camion>();
 Connection conn=SingletonConnection.getConnection();
 try {
PreparedStatement ps= conn.prepareStatement("select * from CAMIONS where NOM_CAMION LIKE ?");
ps.setString(1, "%"+mc+"%");
ResultSet rs = ps.executeQuery();
while (rs.next()) {
	Camion p = new Camion();
p.setIdCamion(rs.getLong("ID_CAMION"));
p.setNomCamion(rs.getString("NOM_CAMION"));
p.setPrix(rs.getDouble("PRIX"));
prods.add(p);
}
} catch (SQLException e) {
e.printStackTrace();
}
return prods;
}
@Override
public Camion getCamion(Long id) {
Connection conn=SingletonConnection.getConnection();
Camion p = new Camion();
try {
PreparedStatement ps= conn.prepareStatement("select * from CAMIONS where ID_CAMION = ?");
ps.setLong(1, id);
ResultSet rs = ps.executeQuery();
if (rs.next()) {
p.setIdCamion(rs.getLong("ID_CAMION"));
p.setNomCamion(rs.getString("NOM_CAMION"));
p.setPrix(rs.getDouble("PRIX"));
}
} catch (SQLException e) {
e.printStackTrace();
}
return p;
}

@Override
public Camion updateCamion(Camion p) {
	Connection conn=SingletonConnection.getConnection();
	 try {
	PreparedStatement ps= conn.prepareStatement("UPDATE CAMIONS SET ID_CAMION=?,PRIX=? WHERE ID_CAMION=?");
	ps.setString(1, p.getNomCamion());
	ps.setDouble(2, p.getPrix());
	ps.setLong(3, p.getIdCamion());
	ps.executeUpdate();
	ps.close();
	} catch (SQLException e) {
	e.printStackTrace();
	}
	return p;
	}

@Override
public void deleteCamion(Long id) {

	Connection conn=SingletonConnection.getConnection();
	 try {
	PreparedStatement ps= conn.prepareStatement("DELETE FROM CAMIONS WHERE ID_CAMION = ?");
	ps.setLong(1, id);
	ps.executeUpdate();
	ps.close();
	} catch (SQLException e) {
	e.printStackTrace();
	}
	}

}