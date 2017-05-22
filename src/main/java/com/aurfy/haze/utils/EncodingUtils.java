package com.aurfy.haze.utils;

import java.nio.charset.Charset;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author gqi
 *
 */
public final class EncodingUtils {

	private static final Logger logger = LoggerFactory.getLogger(EncodingUtils.class);

	public static final Charset ASCII = Charset.forName("US-ASCII");
	public static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
	public static final Charset Big5 = Charset.forName("Big5");
	public static final Charset GB2312 = Charset.forName("GB2312");
	public static final Charset GBK = Charset.forName("GBK");
	public static final Charset GB18030 = Charset.forName("GB18030");
	public static final Charset UTF_8 = Charset.forName("UTF-8");
	public static final Charset UTF_16 = Charset.forName("UTF-16");
	public static final Charset UTF_32 = Charset.forName("UTF-32");

	private EncodingUtils() {
	}

	/**
	 * Converts the specified string to a byte array.
	 *
	 * @param data
	 *            the string to be encoded
	 * @param charset
	 *            the desired character encoding
	 * @return The resulting byte array.
	 */
	public static byte[] getBytes(final String data, final Charset charset) {
		return data.getBytes(charset);
	}

	public static byte[] getISO8859Bytes(final String data) {
		return getBytes(data, ISO_8859_1);
	}

	public static byte[] getAsciiBytes(final String data) {
		return getBytes(data, ASCII);
	}

	public static byte[] getBig5Bytes(final String data) {
		return getBytes(data, Big5);
	}

	public static byte[] getGBKBytes(final String data) {
		return getBytes(data, GBK);
	}

	public static byte[] getUTF8Bytes(final String data) {
		return getBytes(data, UTF_8);
	}

	public static byte[] getUTF16Bytes(final String data) {
		return getBytes(data, UTF_16);
	}

	/**
	 * Converts the byte array of characters to a string.
	 *
	 * @param data
	 *            the byte array to be encoded
	 * @param charset
	 *            the desired character encoding
	 * @return The string representation of the byte array
	 */
	public static String getString(final byte[] data, final Charset charset) {
		return new String(data, charset);
	}

	public static String getISO8859String(final byte[] data) {
		return getString(data, ISO_8859_1);
	}

	public static String getAsciiString(final byte[] data) {
		return getString(data, ASCII);
	}

	public static String getBig5String(final byte[] data) {
		return getString(data, Big5);
	}

	public static String getGBKString(final byte[] data) {
		return getString(data, GBK);
	}

	public static String getUTF8String(final byte[] data) {
		return getString(data, UTF_8);
	}

	public static String getUTF16String(final byte[] data) {
		return getString(data, UTF_16);
	}

	/**
	 * Encode string by base64
	 * 
	 * @param keyArray
	 * @param charset
	 * @return
	 */
	public static String encodeBase64(byte[] keyArray, Charset charset) {
		return new String(Base64.encodeBase64(keyArray), charset);
	}

	/**
	 * Decode string by base64
	 * 
	 * @param keyArray
	 * @param charsetName
	 * @return
	 */
	public static String decodeBase64(byte[] keyArray, Charset charsetName) {
		return new String(Base64.decodeBase64(keyArray), charsetName);
	}

	/**
	 * Decode string by base64
	 * 
	 * @param key
	 * @param charsetName
	 * @return
	 */
	public static byte[] decodeBase64(String key, Charset charsetName) {
		return Base64.decodeBase64(key.getBytes(charsetName));
	}

}
