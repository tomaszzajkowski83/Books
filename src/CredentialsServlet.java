import javax.naming.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import javax.sql.*;

public class CredentialsServlet extends HttpServlet {
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
        PrintWriter out = resp.getWriter();
        String username = req.getParameter("login");
        String password = req.getParameter("password");

        Connection con = null;
        try {
            synchronized (dataSource) {
                con = dataSource.getConnection();
            }
            PreparedStatement prstmt = con.prepareStatement("select * from urzytkownicy where username = ?");
            prstmt.setString(1, username);
            ResultSet rs = prstmt.executeQuery();
            if (!rs.next()) {
                req.getSession().setAttribute("login","No user");
                req.setAttribute("forward","sign");
                getServletContext().getRequestDispatcher("/index.jsp").forward(req,resp);
                return;
            }
            String pa = rs.getString("password");
            String us = rs.getString("username");
            if(pa.equals(password)){
                req.getSession().setAttribute("username",username);
                getServletContext().getRequestDispatcher("/welcome.jsp").forward(req,resp);
                return;
            }
            req.getSession().setAttribute("login","Wrong password");
            getServletContext().getRequestDispatcher("/index.jsp").forward(req,resp);
            rs.close();
            prstmt.close();
        } catch (Exception exc) {
            out.println(exc.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception exc) {
            }
        }
        out.close();
    }
}
