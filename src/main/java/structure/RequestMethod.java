package structure;

/**
 * @author Aliaksandr Yakutsin
 */
public enum RequestMethod {
    GET{
        @Override
        public String toString() {
            return "GET / HTTP/1.0";
        }
    },
    POST{
        @Override
        public String toString() {
            return "POST / HTTP/1.0";
        }
    },
    HEAD{
        @Override
        public String toString() {
            return "HEAD / HTTP/1.0";
        }
    }
}
