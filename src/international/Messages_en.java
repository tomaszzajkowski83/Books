package international;

import java.util.ListResourceBundle;

public class Messages_en extends ListResourceBundle {

    static final Object[][] contents = {
            { "hello", "Login to service!" },
            {"welcome","Hello "},
            {"register","Register new account!"},
            {"No user","There is no such user in service database!"},
            {"User exists","User with given username already exist. Choose another ID or try to log in!"},
            {"Wrong password","You enter incorrect password!"},
            {"username", "Username"},
            {"password", "Password"},
            {"login","Log In"},
            {"signmessage","User does not exist in service database please sign in a new user...."},
            {"sign","Create a new account"}
    };
    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
