import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class DeleteAcountServlet extends HttpServlet {
    DataSource dataSource;

    public void init() throws ServletException {
        try {
            Context init = new InitialContext();
            Context contx = (Context) init.lookup("java:comp/env");
            dataSource = (DataSource) contx.lookup("jdbc/ksidb");
        } catch (NamingException exc) {
            throw new ServletException(
                    "Nie mogę uzyskać źródła java:comp/env/jdbc/ksidb", exc);
        }
    }

    public void doGet(HttpServletRequest req,
                       HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html; charset=windows-1250");
        String usr = (String) req.getSession().getAttribute("username");
        //String usr = (String) getServletContext().getAttribute("username");
        //String pswd = req.getParameter("password");

        Connection con = null;
        try {
            synchronized (dataSource) {
                con = dataSource.getConnection();
            }
            PreparedStatement prstmt = con.prepareStatement("delete from URZYTKOWNICY where username = ?");
            prstmt.setString(1, usr);
            int rs = prstmt.executeUpdate();
            if (rs<=0) {
                req.getSession().setAttribute("login","User exists");
                req.setAttribute("forward","sign");
                throw new Exception("Nie udalo sie usunąc użytkownika");
                //getServletContext().getRequestDispatcher("/signin.jsp").forward(req,resp);
                //return;
            }
            req.removeAttribute("login");
            getServletContext().getRequestDispatcher("/index.jsp").forward(req,resp);
            prstmt.close();
        } catch (Exception exc) {
            req.setAttribute("books", exc.getMessage());
            req.getRequestDispatcher("welcome.jsp").forward(req, resp);
        } finally {
            try {
                con.close();
            } catch (Exception exc) {
            }
        }
    }
}

