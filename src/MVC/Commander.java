package MVC;

import java.util.*;
import java.io.*;

public class Commander implements Serializable, Command {

    private Map parameterMap = new HashMap();
    private List resultList = new ArrayList();

    private int statusCode;

    public Commander() {}

    public void init() {}

    public void setParameter(String name, Object value) {
        parameterMap.put(name, value);
    }

    public Object getParameter(String name) {
        return parameterMap.get(name);
    }

    public void execute() {}

    public List getResults() {
        return resultList;
    }

    public void addResult(Object o) {
        resultList.add(o);
    }

    public void addResult(String s) {
        addResult(new Object[] { s } );
    }


    public void clearResult() {
        resultList.clear();
    }

    public void setStatusCode(int code) {
        statusCode = code;
    }

    public int getStatusCode() {
        return statusCode;
    }

}
