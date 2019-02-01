package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLFormatter {
    public static final String HOST_PATTERN= "((.)+\\.)?.+\\.[a-z]{2,4}";
    public static final String RESOURSE_PATTERN = "(\\/.+)*";
    public static final String PARAMS_PATTERN = "(\\w+\\=(\\d|\\w)+)(\\&\\w+\\=(\\d|\\w)+)*";
    private static Pattern pattern;
    private static Matcher matcher;

    public static String getSubstring(String url,String regex){
        pattern=Pattern.compile(regex);
        matcher=pattern.matcher(url);
        String substr="";
        if (matcher.find())
        {
            substr+=matcher.group();
        }
        return substr;
    }

    public String getRequest(String URL){
        String host=getSubstring(URL,HOST_PATTERN);
        String resourses=getSubstring(URL,RESOURSE_PATTERN);
        String params=getSubstring(URL,PARAMS_PATTERN);
    }

}
