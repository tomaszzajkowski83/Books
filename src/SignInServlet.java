import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class SignInServlet extends HttpServlet {

    public void doPost(HttpServletRequest req,
                       HttpServletResponse resp)
            throws ServletException, IOException {

        Locale locale = req.getLocale();
        ResourceBundle msg = (ResourceBundle) req.getSession().getAttribute("resourceBundle");
        String message = msg.getString("signmessage");
        String username = msg.getString("username");
        String password = msg.getString("password");
        String usrpersist = req.getParameter("login");
        String label = msg.getString((String) req.getAttribute("forward"));

        PrintWriter out = resp.getWriter();
        out.println("<html><head><title>Biblioteka PJATKowego człowieka</title> <link rel=\"stylesheet\" href=\"./style.css\"/>" +
                "</head><body><h1><b>Biblioteka PJATKowego Człowieka</b></h1><hr>" +
                "<div style=\"text-align: center; background-color: aqua\"><h2>" + message + "</h2></div><hr>" +
                "<div style=\"text-align: center; background-image: url('pjatk.png')\">" +
                "<form method=\"post\" action=\"http://localhost:8080/TPO3/credentials\"><br><br>" +
                "<span style=\"color: gold\">" + username + "</span>&nbsp;&nbsp;<input type=\"text\" value=\"" + usrpersist + "\" name=\"login\"> <br><br>" +
                "<span style=\"color: gold\">" + password + "</span>&nbsp;&nbsp;<input type=\"text\" name=\"password\"> <br><br>" +
                "<input class=\"logBtn\" type=\"submit\" value=\"" + label + "\"><br><br><br><br><br>" +
                "<br><label style=\"color: white\">Copyright to s1832<br><br>Tomasz Zajkowski</label><br><br></form></div>" +
                "<label class=\"container\">Aby przetestowac funkcje administratora dane logowania to admin/admin<br>Sprawdź rownież jak działa lokalizacja aplikacji :)" +
                "</label></body></html>");
    }
}
