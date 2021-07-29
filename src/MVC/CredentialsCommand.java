package MVC;

import MVC.Commander;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.*;

public class CredentialsCommand extends Commander implements Serializable {

    DataSource dataSource;

    public CredentialsCommand(){}
    public void execute(){
        clearResult();
        try {
            Context init = new InitialContext();
            Context contx = (Context) init.lookup("java:comp/env");
            dataSource = (DataSource) contx.lookup("jdbc/ksidb");
        } catch (NamingException exc) {
            //---------------------
        }
    }
}
