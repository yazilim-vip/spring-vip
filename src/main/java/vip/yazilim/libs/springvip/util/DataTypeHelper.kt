package vip.yazilim.libs.springvip.util


/**
 * Get booleanValue true if it is not null and its value is true
 *
 * @param booleanValue input non-safe boolean value
 * @return true | false
 */
fun getSafeBoolean(booleanValue: Boolean?): Boolean {
    return booleanValue != null && booleanValue
}

/**
 * Get string value as empty if it is null
 * @param string input string
 * @return null-safe string value
 */
fun getSafeString(string: String?): String {
    return string ?: ""
}

/**
 * TODO: explain commnet
 * @param object input string
 * @return null safe string
 */
fun getNullSafeToString(`object`: Any?): String {
    return `object`?.toString() ?: ""
}

/**
 * Convert input hexadecimal string into an integer
 *
 * @param hexString input hexadecimal value
 * @return integer value of given hexadecimal
 */
fun hexToInt(hexString: String): Int {
    return hexString.toInt(16)
}

/**
 * Convert input integer value into hexadecimal string
 *
 * @param intValue input integer value
 * @return hexadecimal string value of the given integer
 */
fun intToHex(intValue: Int): String {
    return String.format("%X", intValue)
}

/**
 * Convert input integer value into hexadecimal string
 *
 * @param intValue input integer value
 * @param digit    Values: 2,4 | number of represented digits.
 * @return hexadecimal string value of the given integer
 */
fun intToHex(intValue: Int, digit: Int): String {
    return String.format("%0" + digit + "X", intValue)
}

/**
 * Convert input integer value into byte
 *
 * @param intValue input integer value
 * @return byte value of the given integer
 */
fun intToByte(intValue: Int): Byte {
    return intValue.toByte()
}
