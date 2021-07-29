import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SearchServlet extends HttpServlet {

    public void doGet(HttpServletRequest req,
                       HttpServletResponse resp)
            throws ServletException, IOException {
        String sb = "<form>\n" +
                "    <label>Wybierz sposób wyszukiwania</label><br>\n" +
                "    <input type=\"radio\" id=\"searchAll\"\n" +
                "    name=\"searching\" value=\"all\" checked>\n" +
                "    <label for=\"searchAll\">Show All</label>\n" +
                "    <input type=\"radio\" id=\"searchTitle\"\n" +
                "           name=\"searching\" value=\"title\">\n" +
                "    <label for=\"searchTitle\">Title</label>\n" +
                "    <input type=\"radio\" id=\"searchAuthor\"\n" +
                "           name=\"searching\" value=\"autor\">\n" +
                "    <label for=\"searchAuthor\">Author</label><br>\n" +
                "    <label style=\"padding-bottom: 5px\">Wpisz wyszukiwana frazę!</label>&nbsp;&nbsp;\n" +
                "    <input type=\"text\" name=\"patern\">&nbsp;&nbsp;\n" +
                "    <button class=\"logBtn\" type=\"submit\" formaction=\"http://localhost:8080/TPO3/books\" value=\"\">Potwierdź</button>\n" +
                "</form>";
        req.setAttribute("books",sb);
        req.getRequestDispatcher("welcome.jsp").forward(req,resp);
    }
}

