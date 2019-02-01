package util;

import structure.ResponseStatus;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLFormatter {
    private static Pattern resourcePattern = Pattern.compile("\\/[a-z0-9=?\\-\\/&]*$");
    private static Pattern hostPattern = Pattern.compile("(.+\\.)?.+\\.[a-z]{2,4}(:\\d+)?");
    private static Pattern responseStatusPattern = Pattern.compile("^HTTP\\/\\d\\.\\d\\s+\\d{3}\\s+.+");

    public static String getSubstring(String url,String regex){
        Matcher matcher = resourcePattern.matcher(url);
        String substr = "";
        if (matcher.find()) {
            substr += matcher.group();
        }
        return substr;
    }

    public static ResponseStatus getResponseStatus(String response) {
        Matcher statusMatcher = responseStatusPattern.matcher(response);
        if (statusMatcher.find()) {
            String status = statusMatcher.group();
            Pattern pattern = Pattern.compile("\\d{3}");
            Matcher matcher = pattern.matcher(status);
            if (matcher.find())
                return ResponseStatus.getStatusByCode(Integer.valueOf(matcher.group()));
            return null;
        }
        return null;
    }

    public static boolean validateHost(String host) {
        Matcher matcher = hostPattern.matcher(host);
        return matcher.find();
    }

    //delete later
    public static void check(String str) {
        Matcher matcher = resourcePattern.matcher(str);
        if (matcher.find())
           System.out.println(matcher.group());

        matcher = hostPattern.matcher(str);
        if (matcher.find())
            System.out.println(matcher.group());

    }
}
