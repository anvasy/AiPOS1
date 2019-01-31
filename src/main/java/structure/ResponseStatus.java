package structure;

public enum ResponseStatus {
    OK(200), CREATED(201), ACCEPTED(202), NO_CONTENT(204), PARTIAL_CONTENT(206),
    MOVED_PERMANENTLY(301), FOUND(302), NOT_MODIFIED(304),
    BAD_REQUEST(400), FORBIDDEN(403), NOT_FOUND(404), GONE(410),
    INTERNAL_SERVER_ERROR(500), BAD_GATEWAY(502), SERVICE_UNAVAILABLE(503);

    private final int code;
    private String message;

    ResponseStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static ResponseStatus getStatusByCode(int code) {
        for (ResponseStatus status : ResponseStatus.values())
            if (status.code == code)
                return status;
        return null;
    }
}
