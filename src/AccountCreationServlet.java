import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AccountCreationServlet extends HttpServlet {
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

    public void doPost(HttpServletRequest req,
                              HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html; charset=windows-1250");
        String usr = req.getParameter("login");
        String pswd = req.getParameter("password");

        Connection con = null;
        try {
            synchronized (dataSource) {
                con = dataSource.getConnection();
            }
            PreparedStatement prstmt = con.prepareStatement("insert into URZYTKOWNICY (username, password) values(?,?)");
            prstmt.setString(1, usr);
            prstmt.setString(2, pswd);
            int rs = prstmt.executeUpdate();
            if (rs<=0) {
                req.getSession().setAttribute("login","User exists");
                req.setAttribute("forward","sign");
                getServletContext().getRequestDispatcher("/signin.jsp").forward(req,resp);
                return;
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
