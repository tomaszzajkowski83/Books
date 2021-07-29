package international;

import java.util.ListResourceBundle;

public class Messages_pl extends ListResourceBundle {

    static final Object[][] contents = {
            { "hello", "Logowanie do serwisu!" },
            {"welcome","Witaj "},
            {"register","Tworzenie nowego konta!!"},
            {"No user","Użutkownik nie istnieje w bazie aplikacji!"},
            {"User exists","Użytkownik o podanym nicku istnieje. Zmien nazwę lub spróbuj sie zalogować!"},
            {"Wrong password","Błędne hasło!"},
            {"username", "Login"},
            {"password", "Hasło"},
            {"login","Zaloguj"},
            {"signmessage","Użutkownik nie istnieje w bazie aplikacji. Zarejestruj się...."},
            {"sign","Utwórz nowe konto"}
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
