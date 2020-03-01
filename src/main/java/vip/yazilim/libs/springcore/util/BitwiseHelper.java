package vip.yazilim.libs.springcore.util;

/**
 *
 * @author Emre Sen, Dec 9, 2019
 * @contact maemresen@yazilim.vip
 */
public class BitwiseHelper {

	public static void main(String[] args) {
		short value = 64;
		boolean[][] bits = getBits(value);
		for (int i = 1; i >= 0; i--) {
			for (int j = 7; j >= 0; j--) {
				System.out.print(bits[i][j] ? 1 : 0);
			}
			System.out.print(" ");
		}
	}

	public static boolean[][] getBits(String value) {
		return getBits(Long.valueOf(value));
	}

	public static boolean[][] getBits(long intValue) {
		int byteOrder = 0;

		boolean[][] bits = new boolean[8][8];
		do {
			byte byteValue = (byte) (intValue & 0xff);
			bits[byteOrder] = getBits(byteValue);
			intValue >>= 8;
			byteOrder++;
		} while (byteOrder < 4);
		return bits;
	}

	public static boolean[][] getBits(int intValue) {
		int byteOrder = 0;

		boolean[][] bits = new boolean[4][8];
		do {
			byte byteValue = (byte) (intValue & 0xff);
			bits[byteOrder] = getBits(byteValue);
			intValue >>= 8;
			byteOrder++;
		} while (byteOrder < 4);
		return bits;
	}

	public static boolean[][] getBits(short shortValue) {
		int byteOrder = 0;

		boolean[][] bits = new boolean[2][8];
		do {
			byte byteValue = (byte) (shortValue & 0xff);
			bits[byteOrder] = getBits(byteValue);
			shortValue >>= 8;
			byteOrder++;
		} while (byteOrder < 2);
		return bits;
	}

	public static boolean[] getBits(byte byteValue) {
		int n = 8;
		final boolean[] set = new boolean[n];
		while (--n >= 0) {
			boolean result = (byteValue & 0x80) != 0;
			set[n] = result;
			byteValue <<= 1;
		}
		return set;
	}

}
