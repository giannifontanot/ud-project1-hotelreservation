package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {

    public static boolean VerifyEmail(String email) {
        //Verify email using a regex
        Pattern emailPattern = Pattern.compile("^(.+)@(.+)com$");
        Matcher matcher = emailPattern.matcher(email);

        try {
            if (!matcher.find()) {
                throw new IllegalArgumentException("Email address is not valid");
            }

        }catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }
}
