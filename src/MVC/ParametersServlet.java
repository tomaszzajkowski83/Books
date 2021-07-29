package MVC;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class ParametersServlet extends HttpServlet {

    private ServletContext context;
    private String resBundleServ;

    public void init() {
        context = getServletContext();
        resBundleServ = context.getInitParameter("resBundleServ");
    }

    public void doPost(HttpServletRequest req,
                               HttpServletResponse resp)
            throws ServletException, IOException
    {


        // Włączenie serwletu przygotowującego informacje z z zasobów
        // (ResourceBundle). Informacja będzie dostępna poprzez
        // statyczne metody klasy MVC.BundleInfo

        RequestDispatcher disp = context.getRequestDispatcher(resBundleServ);
        disp.include(req, resp);

        // Pobranie potrzebnej informacji
        // ktora została wczesniej przygotowana
        // przez klasę MVC.BundleInfo na podstawie zlokalizowanych zasobów

        // Zlokalizowana strona kodowa
        String charset = BundleInfo.getCharset();

        // Napisy nagłówkowe
        String[] headers = BundleInfo.getHeaders();

        // Nazwy parametrów (pojawią się w formularzu,
        // ale również są to nazwy parametrów dla MVC.Command)
        String[] pnames = BundleInfo.getCommandParamNames();

        // Opisy parametrów - aby było wiadomo co w formularzu wpisywać
        String[] pdes   = BundleInfo.getCommandParamDescr();

        // Napis na przycisku
        String submitMsg = BundleInfo.getSubmitMsg();

        // Ew. końcowe napisy na stronie
        String[] footers = BundleInfo.getFooters();

        // Ustalenie właściwego kodowania zlecenia
        // - bez tego nie będzie można własciwie odczytać parametrów
        req.setCharacterEncoding(charset);

        // Pobranie aktualnej sesji
        // w jej atrybutach są/będą przechowywane
        // wartości parametrów

        HttpSession session = req.getSession();

        // Generowanie strony

        resp.setCharacterEncoding(charset);
        PrintWriter out = resp.getWriter();

        out.println("<center><h2>");
        for (int i=0; i<headers.length; i++)
            out.println(headers[i]);
        out.println("</center></h2><hr>");

        // formularz
        out.println("<form method=\"post\">");
        for (int i=0; i<pnames.length; i++) {
            out.println(pdes[i] + "<br>");
            out.print("<input type=\"text\" size=\"30\" name=\"" +
                    pnames[i] +  "\"");

                    // Jezeli są już wartości parametrów - pokażemy je w formularzu
                    String pval = (String) session.getAttribute("param_"+pnames[i]);
            if (pval != null) out.print(" value=\"" + pval + "\"");
            out.println("><br>");
        }
        out.println("<br><input type=\"submit\" value=\"" + submitMsg + "\">");
        out.println("</form>");

        // Pobieranie parametrów z formularza

        for (int i=0; i<pnames.length; i++) {
            String paramVal = req.getParameter(pnames[i]);
            // Jeżeli brak parametru (ów) - konczymy
            if (paramVal == null) return;

            // Jest parametr - zapiszmy jego wartość jako atrybut sesji.
            // Zostanie on pobrany przez Controller
            // który ustali te wartości dla wykonania MVC.Command

            session.setAttribute("param_" + pnames[i], paramVal);

        }
    }

    //..metody doGet i doPost - wywołują serviceRequest
}
