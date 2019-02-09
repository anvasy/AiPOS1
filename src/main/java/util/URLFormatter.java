package util;

import structure.ResponseStatus;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Formatter for URL-string
 * @author Aliaksandr Yakutsin
 */
public class URLFormatter {
    private static Pattern resourcePattern = Pattern.compile("\\/[a-z0-9=?\\-\\/&]*$");
    private static Pattern hostPattern = Pattern.compile("(((.+\\.)?(.+)\\.[a-z]{2,4})|localhost)(:\\d+)?");
    private static Pattern ipPattern = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}(:\\d+)?");
    private static Pattern responseStatusPattern = Pattern.compile("^HTTP\\/\\d\\.\\d\\s+\\d{3}\\s+.+");

    /**
     * Returns server's response status
     * @param response server response
     * @return response status
     * @see ResponseStatus
     */
    public static ResponseStatus getResponseStatus(String response) {
        Matcher statusMatcher = responseStatusPattern.matcher(response);
        if (statusMatcher.find()) {
            String status = statusMatcher.group();
            Pattern pattern = Pattern.compile("\\d{3}");
            Matcher matcher = pattern.matcher(status);
            if(matcher.find())
                return ResponseStatus.getStatusByCode(Integer.valueOf(matcher.group()));
            return null;
        }
        return null;
    }


    /**
     * Checking is host valid
     * @param host Address of target node at network to send request
     * @return if host is valid returns true, else returns false
     */
    public static boolean validateHost(String host) {
        Matcher matcher = hostPattern.matcher(host);
        if(matcher.find())
            return true;
        else {
            matcher = ipPattern.matcher(host);
            return matcher.find();
        }
    }

    /**
     * Formats host string for work with it
     * @param host host
     * @return formatted host
     */
    public static String prepareHost(String host) {
        Matcher matcher = hostPattern.matcher(host);
        if (matcher.find())
            host = matcher.group();
        return host.replaceAll("http://", "");
    }
}
