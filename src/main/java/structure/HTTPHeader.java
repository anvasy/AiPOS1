package structure;

public enum HTTPHeader {

    AUTHORIZATION ("Authorization"),
    CONTENT_ENCODING("Content-Encoding"),
    CONTENT_LENGTH("Content-Length"),
    CONTENT_TYPE("Content-Type"),
    DATE("Date"),
    EXPIRES("Expires"),
    FROM("From"),
    IF_MODIFIED_SINCE("If-Modified-Since"),
    LAST_MODIFIED("Last-Modified"),
    USER_AGENT("User-Agent");

    private final String name;
    private String state;

    HTTPHeader(String name) {
        this.name = name;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public String getName() {
        return name;
    }
}
