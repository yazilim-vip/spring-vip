package vip.yazilim.libs.springcore.util;

/**
 * 
 * 
 *
 * @author Emre Sen - Dec 26, 2019
 * @contact maemresen@yazilim.vip
 *
 */
public class DataTypeHelper {

    /**
     * Get booleanValue true if it is not null and its value is true
     * 
     * @param booleanValue input non-safe boolean value
     * @return true | false
     */
    public static boolean getSafeBoolean(Boolean booleanValue) {
        return booleanValue != null && booleanValue;
    }

    /**
     * Get string value as empty if it is null
     * @param string input string
     * @return null-safe string value
     */
    public static String getSafeString(String string) {
        if (string == null) {
            return "";
        }
        return string;
    }
    

    /**
     * TODO: explain commnet
     * @param object input string
     * @return null safe string
     */
    public static String getNullSafeToString(Object object) {
        if (object == null) {
            return "";
        }
        return String.valueOf(object);
    }
    

    /**
     * Convert input hexadecimal string into an integer
     * 
     * @param hexString input hexadecimal value
     * @return integer value of given hexadecimal
     */
    public static int hexToInt(String hexString) {
        return Integer.parseInt(hexString, 16);
    }

    /**
     * Convert input integer value into hexadecimal string
     * 
     * @param intValue input integer value
     * @return hexadecimal string value of the given integer
     */
    public static String intToHex(int intValue) {
        return String.format("%X", intValue);
    }

    /**
     * Convert input integer value into hexadecimal string
     * 
     * @param intValue input integer value
     * @param digit    Values: 2,4 | number of represented digits.
     * @return hexadecimal string value of the given integer
     */
    public static String intToHex(int intValue, int digit) {
        return String.format("%0" + digit + "X", intValue);
    }

    /**
     * Convert input integer value into byte
     * 
     * @param intValue input integer value
     * @return byte value of the given integer
     */
    public static byte intToByte(int intValue) {
        return new Integer(intValue).byteValue();
    }
}
