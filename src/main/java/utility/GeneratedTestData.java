package utility;

import com.github.javafaker.Faker;

public class GeneratedTestData {

    static Faker faker = new Faker();

    public static String generatedLogin(){
        return faker.superhero().name().replaceAll("\\s","") + faker.internet().uuid().substring(0,5);
    }

    public static String generatedEmail(){
        return faker.superhero().name().replaceAll("\\s","_") + faker.internet().emailAddress();
    }

    public static String generatedPassword(){
        return faker.superhero().name().replaceAll("\\s","") + faker.internet().password();
    }

    public static String generatedListName(){
        return faker.address().firstName();
    }

    public static String generatedGiftName(){
        return faker.beer().name();
    }

    public static String generatedDescription(){
        return faker.book().title();
    }

    public static String generatedUrl(){
        String url = faker.internet().url();
        return "https://" + url;
    }

    public static String generatedGiftPrice(){
        return String.valueOf(faker.number().numberBetween(100,10000));
    }
}
