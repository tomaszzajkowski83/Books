import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {
        request.getSession().removeAttribute("username");
        request.getSession().removeAttribute("login");
        response.sendRedirect("index.jsp");
    }
}
