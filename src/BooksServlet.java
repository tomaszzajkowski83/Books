import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import javax.naming.*;
import java.sql.*;
import javax.sql.*;

public class BooksServlet extends HttpServlet {

    DataSource dataSource;
    StringBuilder sb, queryBuilder;
    String filter;
    String searchType;

    public void init() throws ServletException {
        try {
            sb = new StringBuilder();
            queryBuilder = new StringBuilder();
            Context init = new InitialContext();
            Context contx = (Context) init.lookup("java:comp/env");
            dataSource = (DataSource) contx.lookup("jdbc/ksidb");
        } catch (NamingException exc) {
            throw new ServletException(
                    "Nie mogę uzyskać źródła java:comp/env/jdbc/ksidb", exc);
        }
    }

    public void serviceRequest(HttpServletRequest req,
                               HttpServletResponse resp)
            throws ServletException, IOException {
        filter = req.getParameter("patern").toLowerCase();
        searchType = req.getParameter("searching");
        resp.setContentType("text/html; charset=windows-1250");
        sb.append("<h2>Lista dostępnych książek</h2>");

        Connection con = null;
        try {
            synchronized (dataSource) {
                con = dataSource.getConnection();
            }
            Statement stmt = con.createStatement();
            queryBuilder.append("select autor.name, tytul, rok, cena from pozycje join autor on pozycje.autid = autor.autid");
            if (searchType.equals("title")) {
                queryBuilder.append(" where lower(tytul) like '%" + filter + "%'");
            } else if (searchType.equals("autor")) {
                queryBuilder.append(" where lower(name) like '%" + filter + "%'");
            }
            ResultSet rs = stmt.executeQuery(queryBuilder.toString());
            sb.append("<ol>");
            while (rs.next()) {
                String autor = rs.getString("name");
                String tytul = rs.getString("tytul");
                String dataWyd = rs.getString("rok");
                float cena = rs.getFloat("cena");
                sb.append(("<li>" + autor + "&nbsp;&nbsp;" + "\"" + tytul + "\"" + "&nbsp;  - rok wyd: " + dataWyd + "&nbsp; - cena: " + cena + "</li>"));
            }
            sb.append("</body>");
            rs.close();
            stmt.close();
            req.setAttribute("books", sb.toString());
            req.getRequestDispatcher("welcome.jsp").forward(req, resp);
            sb.setLength(0);
            queryBuilder.setLength(0);
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


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        serviceRequest(request, response);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        serviceRequest(request, response);
    }

}
