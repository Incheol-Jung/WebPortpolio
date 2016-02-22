/**
 * 
 * <pre>
 * 1. 프로젝트명	: TestApiSrv
 * 2. 패키지명		: org.jij.WebPortpolio.utils.security
 * 3. 파일명		: Base64.java
 * 4. 작성일		: 2015. 11. 24.
 * 5. 작성자		: sjc
 * 6. 설명			: 
 * </pre>
 */
package org.jij.WebPortpolio.utils.security;

public final class Base64 {
	private static final int BASELENGTH = 255;
	private static final int LOOKUPLENGTH = 64;
	private static final int TWENTYFOURBITGROUP = 24;
	private static final int EIGHTBIT = 8; 
	private static final int SIXTEENBIT = 16;
	private static final int FOURBYTE = 4;
	private static final int SIGN = -128;
	private static final byte PAD = 61;
	private static byte[] base64AlphabetForCookie = new byte[255];
	private static byte[] lookUpBase64AlphabetForCookie = new byte[64];
	private static byte[] base64AlphabetOriginal = new byte[255];
	private static byte[] lookUpBase64AlphabetOriginal = new byte[64];

	static {
		for (int i = 0; i < 255; ++i) {
			base64AlphabetForCookie[i] = -1;
			base64AlphabetOriginal[i] = -1;
		}
		for (int i = 90; i >= 65; --i) {
			base64AlphabetForCookie[i] = (byte) (i - 65);
			base64AlphabetOriginal[i] = (byte) (i - 65);
		}
		for (int i = 122; i >= 97; --i) {
			base64AlphabetForCookie[i] = (byte) (i - 97 + 26);
			base64AlphabetOriginal[i] = (byte) (i - 97 + 26);
		}
		for (int i = 57; i >= 48; --i) {
			base64AlphabetForCookie[i] = (byte) (i - 48 + 52);
			base64AlphabetOriginal[i] = (byte) (i - 48 + 52);
		}

		base64AlphabetForCookie[45] = 62;
		base64AlphabetForCookie[95] = 63;
		base64AlphabetOriginal[43] = 62;
		base64AlphabetOriginal[47] = 63;

		for (int i = 0; i <= 25; ++i) {
			lookUpBase64AlphabetForCookie[i] = (byte) (65 + i);
			lookUpBase64AlphabetOriginal[i] = (byte) (65 + i);
		}

		int i = 26;
		for (int j = 0; i <= 51; ++j) {
			lookUpBase64AlphabetForCookie[i] = (byte) (97 + j);
			lookUpBase64AlphabetOriginal[i] = (byte) (97 + j);

			++i;
		}

		i = 52;
		for (int j = 0; i <= 61; ++j) {
			lookUpBase64AlphabetForCookie[i] = (byte) (48 + j);
			lookUpBase64AlphabetOriginal[i] = (byte) (48 + j);

			++i;
		}

		lookUpBase64AlphabetForCookie[62] = 45;
		lookUpBase64AlphabetForCookie[63] = 95;
		lookUpBase64AlphabetOriginal[62] = 43;
		lookUpBase64AlphabetOriginal[63] = 47;
	}

	public static boolean isBase64(String isValidString) {
		return isArrayByteBase64(isValidString.getBytes());
	}

	public static boolean isBase64(byte octect) {
		return ((octect == 61) || (base64AlphabetForCookie[octect] != -1));
	}

	public static boolean isArrayByteBase64(byte[] arrayOctect) {
		int length = arrayOctect.length;
		if (length == 0) {
			return true;
		}
		for (int i = 0; i < length; ++i) {
			if (!(isBase64(arrayOctect[i])))
				return false;
		}
		return true;
	}

	public static byte[] getBase64Alphabet(boolean originalTable) {
		return ((originalTable) ? base64AlphabetOriginal
				: base64AlphabetForCookie);
	}

	public static byte[] getLookUpBase64Alphabet(boolean originalTable) {
		return ((originalTable) ? lookUpBase64AlphabetOriginal
				: lookUpBase64AlphabetForCookie);
	}

	public static byte[] encodeForCookie(byte[] binaryData) {
		return encode(binaryData, false);
	}

	public static byte[] encode(byte[] binaryData) {
		return encode(binaryData, true);
	}

	public static byte[] encode(byte[] binaryData, boolean originalTable) {
		int lengthDataBits = binaryData.length * 8;
		int fewerThan24bits = lengthDataBits % 24;
		int numberTriplets = lengthDataBits / 24;
		byte[] encodedData = null;
		byte[] lookUpBase64AlphabetTable = getLookUpBase64Alphabet(originalTable);

		if (fewerThan24bits != 0) {
			encodedData = new byte[(numberTriplets + 1) * 4];
		} else {
			encodedData = new byte[numberTriplets * 4];
		}

		byte k = 0;
		byte l = 0;
		byte b1 = 0;
		byte b2 = 0;
		byte b3 = 0;

		int encodedIndex = 0;
		int dataIndex = 0;
		int i = 0;

		for (i = 0; i < numberTriplets; ++i) {
			dataIndex = i * 3;
			b1 = binaryData[dataIndex];
			b2 = binaryData[(dataIndex + 1)];
			b3 = binaryData[(dataIndex + 2)];

			l = (byte) (b2 & 0xF);
			k = (byte) (b1 & 0x3);

			encodedIndex = i * 4;
			byte val1 = ((b1 & 0xFFFFFF80) == 0) ? (byte) (b1 >> 2)
					: (byte) (b1 >> 2 ^ 0xC0);
			byte val2 = ((b2 & 0xFFFFFF80) == 0) ? (byte) (b2 >> 4)
					: (byte) (b2 >> 4 ^ 0xF0);
			byte val3 = ((b3 & 0xFFFFFF80) == 0) ? (byte) (b3 >> 6)
					: (byte) (b3 >> 6 ^ 0xFC);

			encodedData[encodedIndex] = lookUpBase64AlphabetTable[val1];
			encodedData[(encodedIndex + 1)] = lookUpBase64AlphabetTable[(val2 | k << 4)];
			encodedData[(encodedIndex + 2)] = lookUpBase64AlphabetTable[(l << 2 | val3)];
			encodedData[(encodedIndex + 3)] = lookUpBase64AlphabetTable[(b3 & 0x3F)];
		}

		dataIndex = i * 3;
		encodedIndex = i * 4;
		if (fewerThan24bits == 8) {
			b1 = binaryData[dataIndex];
			k = (byte) (b1 & 0x3);
			byte val1 = ((b1 & 0xFFFFFF80) == 0) ? (byte) (b1 >> 2)
					: (byte) (b1 >> 2 ^ 0xC0);
			encodedData[encodedIndex] = lookUpBase64AlphabetTable[val1];
			encodedData[(encodedIndex + 1)] = lookUpBase64AlphabetTable[(k << 4)];
			encodedData[(encodedIndex + 2)] = 61;
			encodedData[(encodedIndex + 3)] = 61;
		} else if (fewerThan24bits == 16) {
			b1 = binaryData[dataIndex];
			b2 = binaryData[(dataIndex + 1)];
			l = (byte) (b2 & 0xF);
			k = (byte) (b1 & 0x3);

			byte val1 = ((b1 & 0xFFFFFF80) == 0) ? (byte) (b1 >> 2)
					: (byte) (b1 >> 2 ^ 0xC0);
			byte val2 = ((b2 & 0xFFFFFF80) == 0) ? (byte) (b2 >> 4)
					: (byte) (b2 >> 4 ^ 0xF0);

			encodedData[encodedIndex] = lookUpBase64AlphabetTable[val1];
			encodedData[(encodedIndex + 1)] = lookUpBase64AlphabetTable[(val2 | k << 4)];
			encodedData[(encodedIndex + 2)] = lookUpBase64AlphabetTable[(l << 2)];
			encodedData[(encodedIndex + 3)] = 61;
		}

		return encodedData;
	}

	public static byte[] decodeForCookie(byte[] binaryData) {
		return decode(binaryData, false);
	}

	public static byte[] decode(byte[] binaryData) {
		return decode(binaryData, true);
	}

	public static byte[] decode(byte[] base64Data, boolean originalTable) {
		if (base64Data.length == 0) {
			return new byte[0];
		}

		int numberQuadruple = base64Data.length / 4;
		byte[] decodedData = null;
		byte b1 = 0;
		byte b2 = 0;
		byte b3 = 0;
		byte b4 = 0;
		byte marker0 = 0;
		byte marker1 = 0;
		byte[] base64AlphabetTable = getBase64Alphabet(originalTable);

		int encodedIndex = 0;
		int dataIndex = 0;

		int lastData = base64Data.length;

		while (base64Data[(lastData - 1)] == 61) {
			if (--lastData == 0) {
				return new byte[0];
			}
		}
		decodedData = new byte[lastData - numberQuadruple];

		for (int i = 0; i < numberQuadruple; ++i) {
			dataIndex = i * 4;
			marker0 = base64Data[(dataIndex + 2)];
			marker1 = base64Data[(dataIndex + 3)];

			b1 = base64AlphabetTable[base64Data[dataIndex]];
			b2 = base64AlphabetTable[base64Data[(dataIndex + 1)]];

			if ((marker0 != 61) && (marker1 != 61)) {
				b3 = base64AlphabetTable[marker0];
				b4 = base64AlphabetTable[marker1];

				decodedData[encodedIndex] = (byte) (b1 << 2 | b2 >> 4);
				decodedData[(encodedIndex + 1)] = (byte) ((b2 & 0xF) << 4 | b3 >> 2 & 0xF);
				decodedData[(encodedIndex + 2)] = (byte) (b3 << 6 | b4);
			} else if (marker0 == 61) {
				decodedData[encodedIndex] = (byte) (b1 << 2 | b2 >> 4);
			} else if (marker1 == 61) {
				b3 = base64AlphabetTable[marker0];

				decodedData[encodedIndex] = (byte) (b1 << 2 | b2 >> 4);
				decodedData[(encodedIndex + 1)] = (byte) ((b2 & 0xF) << 4 | b3 >> 2 & 0xF);
			}
			encodedIndex += 3;
		}
		return decodedData;
	}
}
