package za.ac.cput.mobile.scanner.admin.exception;

public class RestException extends RuntimeException {

    private final int statusCode;
    private final String reasonPhrase;

    public RestException(int statusCode, String reasonPhrase) {
        super();
        this.statusCode = statusCode;
        this.reasonPhrase = reasonPhrase;
    }

    public RestException(Throwable cause) {
        super(cause);
        statusCode = 500;
        reasonPhrase = "Server error";
    }

    @Override
    public String getMessage() {
        String message = super.getMessage();
        String errorMessage = message == null ? reasonPhrase : message;
        return String.format("%d - %s\n\t%s", statusCode, reasonPhrase, errorMessage);
    }
}
