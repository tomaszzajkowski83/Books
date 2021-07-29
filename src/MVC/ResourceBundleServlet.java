package MVC;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

// Serwlet włączany wyłącznie z serwletu pobierania parametrów
// Ładuje  ResourceBundle i przekazuje go klasie MVC.BundleInfo,
// która odczytuje info i daje wygodną formę jej pobierania
// w innych serwletach.
// Ładowanie zasobów i ich przygotowanie przez klasę MVC.BundleInfo
// następuje tylko raz na sesję.


public class ResourceBundleServlet extends HttpServlet {

    private String resBundleName;

    public void init() {
        resBundleName = getServletContext().getInitParameter("resBundleName");
    }

    public void serviceRequest(HttpServletRequest req,
                               HttpServletResponse resp)
            throws ServletException, IOException
    {
        HttpSession ses = req.getSession();
        ResourceBundle paramsRes = (ResourceBundle) ses.getAttribute("resBundle");

        // W tej sesji jeszcze nie odczytaliśmy zasobów
        if (paramsRes == null) {
            Locale loc = req.getLocale();
            paramsRes = ResourceBundle.getBundle(resBundleName, loc);
            ses.setAttribute("resBundle", paramsRes);

            // Przygotowanie zasobów w wygodnej do odczytu formie
            BundleInfo.generateInfo(paramsRes);
        }

        // ... a jeśli sesja się nie zmieniła - to nie mamy nic do roboty
    }
//...
}
