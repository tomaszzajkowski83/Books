package MVC;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.locks.*;

public class ControllerServlet extends HttpServlet {

    private ServletContext context;
    private Command command;            // obiekt klasy wykonawczej
    private String presentationServ;    // nazwa serwlet prezentacji
    private String getParamsServ;       // mazwa serwletu pobierania parametrów
    private Object lock = new Object(); // semafor dla synchronizacji
    // odwołań wielu wątków
    public void init() {

        context = getServletContext();

        presentationServ = context.getInitParameter("presentationServ");
        getParamsServ = context.getInitParameter("getParamsServ");
        String commandClassName = context.getInitParameter("commandClassName");

        // Załadowanie klasy MVC.Command i utworzenie jej egzemplarza
        // który będzie wykonywał pracę
        try {
            Class commandClass = Class.forName(commandClassName);
            command = (Command) commandClass.newInstance();
        } catch (Exception exc) {
            throw new NoCommandException("Couldn't find or instantiate " +
                    commandClassName);
        }
    }

    // Obsługa zleceń
    public void serviceRequest(HttpServletRequest req,
                               HttpServletResponse resp)
            throws ServletException, IOException
    {

        resp.setContentType("text/html");

        // Wywolanie serwletu pobierania parametrów
        RequestDispatcher disp = context.getRequestDispatcher(getParamsServ);
        disp.include(req,resp);

        // Pobranie bieżącej sesji
        // i z jej atrybutów - wartości parametrów
        // ustalonych przez servlet pobierania parametrów
        // Różne informacje o aplikacji (np. nazwy parametrów)
        // są wygodnie dostępne poprzez własną klasę MVC.BundleInfo

        HttpSession ses = req.getSession();

        String[] pnames = BundleInfo.getCommandParamNames();
        for (int i=0; i<pnames.length; i++) {

            String pval = (String) ses.getAttribute("param_"+pnames[i]);

            if (pval == null) return;  // jeszcze nie ma parametrów

            // Ustalenie tych parametrów dla MVC.Command
            command.setParameter(pnames[i], pval);
        }

        // Wykonanie działań definiowanych przez MVC.Command
        // i pobranie wyników
        // Ponieważ do serwletu może naraz odwoływać sie wielu klientów
        // (wiele watków) - potrzebna jest synchronizacja
        // przy czym rrygiel zamkniemy tutaj, a otworzymy w innym fragmnencie kodu
        // - w serwlecie przentacji (cały cykl od wykonania cmd do poazania wyników jest sekcją krytyczną)

        Lock mainLock = new ReentrantLock();

        mainLock.lock();
        // wykonanie
        command.execute();

        // pobranie wyników
        List results = (List) command.getResults();

        // Pobranie i zapamiętanie kodu wyniku (dla servletu prezentacji)
        ses.setAttribute("StatusCode", new Integer(command.getStatusCode()));

        // Wyniki - będą dostępne jako atrybut sesji
        ses.setAttribute("Results", results);
        ses.setAttribute("Lock", mainLock);    // zapiszmy lock, aby mozna go było otworzyć później


        // Wywołanie serwletu prezentacji
        disp = context.getRequestDispatcher(presentationServ);
        disp.forward(req, resp);
    }


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {
        serviceRequest(request, response);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException
    {
        serviceRequest(request, response);
    }

}