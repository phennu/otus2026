package exceptions;

public class BrowserNotFoundException extends RuntimeException {
    public BrowserNotFoundException (String browser){
        super(String.format("Browser %s is not supported", browser));
    }

}
