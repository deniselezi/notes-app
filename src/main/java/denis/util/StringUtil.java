package denis.util;

public final class StringUtil {
    private StringUtil() {

    }

    // checks if a given string contains any invalid characters and returns true/false
    public static boolean isValid(String stringToCheck, String invalidCharacters) {
        for (int c = 0 ; c < stringToCheck.length() ; c++) {
            char Character = stringToCheck.charAt(c);
            if (invalidCharacters.contains(String.valueOf(Character))) {
                return false;
            }
        }
        return true;
    }
}
