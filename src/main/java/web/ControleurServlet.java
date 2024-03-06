package web;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.connector.Response;
import dao.ICamionDao;
import dao.CamionDaoImpl;
import metier.entities.Camion;

@WebServlet (name="cs",urlPatterns= {"/controleur","*.do"})
public class ControleurServlet extends HttpServlet {
ICamionDao metier;
@Override
public void init() throws ServletException {
metier = new CamionDaoImpl();
}
@Override
protected void doGet(HttpServletRequest request,
 HttpServletResponse response)
 throws ServletException, IOException {
String path=request.getServletPath();
if (path.equals("/index.do"))
{
request.getRequestDispatcher("camions.jsp").forward(request,response);
}
else if (path.equals("/chercher.do"))
{
String motCle=request.getParameter("motCle");
CamionModele model= new CamionModele();
model.setMotCle(motCle);
List<Camion> prods = metier.camionsParMC(motCle);
model.setCamions(prods);
request.setAttribute("model", model);
request.getRequestDispatcher("camions.jsp").forward(request,response);
}
else if (path.equals("/saisie.do") )
{
request.getRequestDispatcher("saisieCamion.jsp").forward(request,response);
}
else if (path.equals("/save.do") && request.getMethod().equals("POST"))
{
 String nom=request.getParameter("nom");
double prix = Double.parseDouble(request.getParameter("prix"));
Camion p = metier.save(new Camion(nom,prix));
request.setAttribute("camion", p);
request.getRequestDispatcher("confirmation.jsp").forward(request,response);
}
else if (path.equals("/supprimer.do"))
{
 Long id= Long.parseLong(request.getParameter("id"));
 metier.deleteCamion(id);
 response.sendRedirect("chercher.do?motCle=");
}
else if (path.equals("/editer.do") )
{
Long id= Long.parseLong(request.getParameter("id"));
 Camion p = metier.getCamion(id);
 request.setAttribute("camion", p);
request.getRequestDispatcher("editerCamion.jsp").forward(request,response);
}
else if (path.equals("/update.do") )
{
Long id = Long.parseLong(request.getParameter("id"));
String nom=request.getParameter("nom");
double prix = Double.parseDouble(request.getParameter("prix"));
Camion p = new Camion();
p.setIdCamion(id);
p.setNomCamion(nom);
p.setPrix(prix);
metier.updateCamion(p);
request.setAttribute("camion", p);
request.getRequestDispatcher("confirmation.jsp").forward(request,response);
}
else
{
response.sendError(Response.SC_NOT_FOUND);
}
}
@Override
protected void doPost(HttpServletRequest request,
 HttpServletResponse response) throws
ServletException, IOException {
doGet(request,response);
}

}
