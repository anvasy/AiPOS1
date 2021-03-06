package structure;

/**
 * @author Anna Vasilyeva
 */
public enum ResponseStatus {
    /**
     *Everything is OK
     */
    OK(200),
    /**
     *New resourse have been created
     */
    CREATED(201),
    /**
     *Request accepted, but not processed yet
     */
    ACCEPTED(202),
    /**
     *Request processed, but response doesn't have content
     */
    NO_CONTENT(204),
    /**
     *Request processed, but response has only part of content
     */
    PARTIAL_CONTENT(206),

    MOVED_PERMANENTLY(301, "This and all future requests should be directed to the given URI."),
    /**
     *Requested resourse is available with other URI
     */
    FOUND(302),
    /**
     *If client uses GET-method with headers "If-Modified-Since"
     */
    NOT_MODIFIED(304),
    BAD_REQUEST(400, "The server cannot or will not process the request due to an apparent client error."),
    FORBIDDEN(403, "The request was valid, but the server is refusing action."), NOT_FOUND(404, "The requested resource could not be found"),
    GONE(410, "The resource requested is no longer available and will not be available again."),
    INTERNAL_SERVER_ERROR(500, "Internal server error."), BAD_GATEWAY(502, "Bad Gateway."), SERVICE_UNAVAILABLE(503, "The server is currently unavailable.");

    private final int code;
    private String message;

    ResponseStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    ResponseStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static ResponseStatus getStatusByCode(int code) {
        for (ResponseStatus status : ResponseStatus.values())
            if (status.code == code)
                return status;
        return null;
    }
}