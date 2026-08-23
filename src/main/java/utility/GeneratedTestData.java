package utility;

import java.util.UUID;

public class GeneratedTestData {

    private static String randomString() {
        return UUID.randomUUID().toString().substring(0, 5);
    }

    public static String generatedLogin(){
        return "username" + randomString();
    }

    public static String generatedEmail(){
        return "email" + randomString() +"@mail.ru";
    }

    public static String generatedPassword(){
        return "pass" + randomString();
    }

    public static String generatedListName(){
        return "listName" + randomString();
    }
}
