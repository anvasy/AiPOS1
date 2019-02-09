package structure;

/**
 * @author Aliaksandr Yakutsin
 */
public enum Headers {
    ACCEPT{
        @Override
        public String toString() {
            return "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8";
        }
    },
    ACCEPT_LANGUAGE{
        @Override
        public String toString() {
            return "Accept-Language: ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7";
        }
    },
    USER_AGENT{
        @Override
        public String toString() {
            return "User-Agent:" +
                    " Mozilla/5.0" +
                    " (Windows NT 10.0; Win64; x64" +
                    ") AppleWebKit/537.36" +
                    " (KHTML, like Gecko)" +
                    " Chrome/66.0.3359.139" +
                    " Safari/537.36";
        }
    }
}
