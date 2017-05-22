package com.aurfy.haze.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

/**
 * 
 * @author gqi
 *
 */
public final class StringUtils extends org.apache.commons.lang3.StringUtils {

	static final String DEFAULT_ENCODING = "UTF-8";
	private static final String GBK_ENCODING = "GBK";
	private static final Logger log = LoggerFactory.getLogger(StringUtils.class);

	private static final DecimalFormat DEFAULT_DECIMAL_FORMATTER = new DecimalFormat("#.##");

	public static final char DEFAULT_JOINER = '&';
	public static final String AREA_CODE_CHINA = "86";

	private StringUtils() {
	}

	/**
	 * Get an escaped string for regular expression to use
	 * 
	 * @param input
	 *            the input string
	 * @return escaped pattern
	 */
	public static String escapeForRegex(String input) {
		if (isBlank(input)) {
			return input;
		}
		input = input.replaceAll("\\\\", "\\\\\\\\");
		input = input.replaceAll("\\(", "\\\\(");
		input = input.replaceAll("\\)", "\\\\)");
		input = input.replaceAll("\\[", "\\\\[");
		input = input.replaceAll("\\]", "\\\\]");
		input = input.replaceAll("\\{", "\\\\{");
		input = input.replaceAll("\\}", "\\\\}");
		input = input.replaceAll("\\|", "\\\\|");
		input = input.replaceAll("\\^", "\\\\^");
		input = input.replaceAll("\\$", "\\\\\\$");
		input = input.replaceAll("\\.", "\\\\.");
		input = input.replaceAll("\\?", "\\\\?");
		input = input.replaceAll("\\+", "\\\\+");
		input = input.replaceAll("\\*", "\\\\*");
		return input;
	}

	public static String formatDouble(double d) {
		return DEFAULT_DECIMAL_FORMATTER.format(d);
	}

	/**
	 * list all the enum values as a comma joined string, with value quoted.
	 * 
	 * @param <T>
	 * @param clazz
	 * @return
	 */
	public static <T> String listEnumAsQuotedString(Class<T> clazz) {
		T[] enums = clazz.getEnumConstants();
		List<String> quotedValues = new ArrayList<String>();
		for (T e : enums) {
			quotedValues.add("\"" + e.toString() + "\"");
		}
		return join(quotedValues, ",");
	}

	public static String join(int[] array, String separator) {
		if (array == null || array.length == 0) {
			return null;
		}
		StringBuffer buffer = new StringBuffer(array[0]);
		for (int i = 1; i < array.length; i++) {
			buffer.append(separator);
			buffer.append(array[i]);
		}
		return buffer.toString();
	}

	public static String formatMessage(String msg, Object... args) {
		if (isBlank(msg) || ArrayUtils.getLength(args) == 0) {
			return msg;
		} else {
			// return MessageFormat.format(msg, args);
			FormattingTuple ft = MessageFormatter.arrayFormat(msg, args);
			return ft.getMessage();
		}
	}

	/**
	 * Take the fixed length of chars from the given string, right pad if not enough length, or full pad if null.
	 * 
	 * @param str
	 * @param length
	 * @param padChar
	 * @return
	 */
	public static String toFixLenStr(String str, int length, char padChar) {
		if (length < 0) {
			throw new RuntimeException("Length can not be less than zero.");
		}
		char[] result = new char[length];
		if (str == null) {
			for (int i = 0; i < length; ++i) {
				result[i] = padChar;
			}
		} else {
			char[] tmp = str.toCharArray();
			int copyLen = Math.min(length, tmp.length);
			System.arraycopy(tmp, 0, result, 0, copyLen);
			if (copyLen < length) {
				for (int i = copyLen; i < length; ++i) {
					result[i] = padChar;
				}
			}
		}
		return new String(result);
	}

	public static String substr(String str, int startIndex) {
		if (isEmpty(str)) {
			return str;
		} else if (startIndex >= 0) {
			return substring(str, startIndex);
		} else {
			int len = -startIndex;
			if (str.length() <= len) {
				return str;
			} else {
				return substring(str, str.length() - len);
			}
		}
	}

	/**
	 * <p>
	 * Get the substring using the specified charset.
	 * </p>
	 * <p>
	 * Length is calculated by bytes. If the result substring does not stand for a valid string in the given charset, it
	 * will be cut off (drop the leading and/or trailing bytes if necessary).
	 * </p>
	 * <p>
	 * i.e.:
	 * <ul>
	 * <li>substrByBytes("ab中文c", 0, 3, "UTF-8") = "ab"</li>
	 * <li>substrByBytes("ab中文c", 0, 4, "GBK") = "ab中"</li>
	 * <li>substrByBytes("ab中文c", 1, 5, "UTF-8") = "ab中"</li>
	 * <li>substrByBytes("ab中文c", 1, 5, "GBK") = "ab中文"</li>
	 * <li>substrByBytes("ab中文c", 100, 1, "UTF-8") = ""</li>
	 * <li>substrByBytes("中文abc", 1, 4, "UTF-8") = ""</li>
	 * <li>substrByBytes("中文abc", 1, 6, "UTF-8") = "中"</li>
	 * </ul>
	 * </p>
	 * 
	 * @param str
	 *            the original string
	 * @param startIndex
	 *            start index (zero-based)
	 * @param len
	 *            length of bytes to retrieve
	 * @param charset
	 *            the charset
	 * @return
	 */
	public static String substrByBytes(String str, int startIndex, int len, String charset) {
		if (str == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer();

		try {
			int str_len = str.length();
			startIndex = startIndex < 0 ? 0 : startIndex;
			if (startIndex > 0) {
				StringBuffer temp = new StringBuffer();
				for (int j = 0; j < str_len; j++) {
					temp.append(str.charAt(j));
					if (temp.toString().getBytes(charset).length >= startIndex) {
						startIndex = j + 1;
						len = len - (temp.toString().getBytes(charset).length - startIndex);
						break;
					}
				}
			}

			for (int i = startIndex; i < str_len; i++) {
				char tmp_c = str.charAt(i);
				sb.append(tmp_c);
				if (sb.toString().getBytes(charset).length > len) {
					sb.deleteCharAt(i - startIndex);
					break;
				}
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			log.warn("UnsupportedEncodingException", e);
		}
		return "";
	}

	public static String maskCardNumber(String cardNumber) {
		return addMask(cardNumber, 6, 4, 4);
	}

	public static String maskPhoneNumber(String phoneNumber) {
		return addMask(phoneNumber, 3, 3, 5);
	}

	public static String maskPhoneNumberWithAreaCode(String phoneNumber, String areaCode) {

		if (isEmpty(areaCode) || AREA_CODE_CHINA.equals(areaCode)) {
			return addMask(phoneNumber, 3, 3, 5);
		} else {
			return areaCode + addMask(phoneNumber, 0, 4, 5);
		}
	}

	public static String addMask(String str, int lenBeforeMask, int lenAfterMask, int maxMaskLength) {
		if (isEmpty(str)) {
			return str;
		}
		int len = str.length();
		if (len <= lenBeforeMask + lenAfterMask) {
			return str;
		}
		int x = Math.min(maxMaskLength, len - lenBeforeMask - lenAfterMask);
		return substring(str, 0, lenBeforeMask) + repeat("*", x) + substring(str, len - lenAfterMask);
	}

	public static int getLength4GBKCharset(String str) {
		int len = 0;
		if (StringUtils.isNotEmpty(str)) {
			try {
				len = str.getBytes(GBK_ENCODING).length;
			} catch (UnsupportedEncodingException e) {
				if (log.isErrorEnabled()) {
					log.error(formatMessage("Encoding \"{0}\" not supported.", GBK_ENCODING), e);
				}
				len = str.length();
			}
		}
		return len;
	}

	public static String joinMapValue(Map<String, String> map, char connector, boolean appendKey4EmptyValue) {
		StringBuffer b = new StringBuffer();
		if (map != null && !map.isEmpty()) {
			for (Map.Entry<String, String> entry : map.entrySet()) {
				if (StringUtils.isEmpty(entry.getValue())) {
					if (appendKey4EmptyValue) {
						b.append(entry.getKey());
						b.append('=');
						b.append(connector);
					}
				} else {
					b.append(entry.getKey());
					b.append('=');
					b.append(entry.getValue());
					b.append(connector);
				}
			}
			if (b.length() > 0) {
				b.deleteCharAt(b.length() - 1);
			}
		}
		return b.toString();
	}

	public static String joinMapURLEncoderValue(Map<String, String> map, char connector, String encoding) {
		return joinMapURLEncoderValue(map, connector, true, encoding);
	}

	public static String joinMapURLEncoderValue(Map<String, String> map, char connector, boolean appendKey4EmptyValue,
			String encoding) {
		StringBuilder b = new StringBuilder();
		if (map != null && !map.isEmpty()) {
			for (Map.Entry<String, String> entry : map.entrySet()) {
				try {
					if (entry.getValue() == null) {
						if (appendKey4EmptyValue) {
							b.append(URLEncoder.encode(entry.getKey(), encoding));
							b.append('=');
							b.append(connector);
						}
					} else {
						b.append(URLEncoder.encode(entry.getKey(), encoding));
						b.append('=');
						b.append(URLEncoder.encode(entry.getValue(), encoding));
						b.append(connector);
					}
				} catch (UnsupportedEncodingException e) {
					log.warn("UnsupportedEncodingException", e);
				}
			}
			b.deleteCharAt(b.length() - 1);
		}
		return b.toString();
	}

	public static String joinMapValue(Map<String, String> map, char connector) {
		return joinMapValue(map, connector, true);
	}

	public static String joinMapValue(Map<String, String> map) {
		return joinMapValue(map, DEFAULT_JOINER);
	}

	/**
	 * Splits map string. E.g., mapString is "a=1;b=2", delimeter is ";" Result is: Map: {key=a, value=1, key=b,
	 * value=2}
	 * 
	 * @param mapString
	 * @return
	 */
	public static Map<String, String> splitMapString(String mapString, String delimeter, String encoding) {
		String[] nameValuePairs = mapString.split(Pattern.quote(delimeter));
		Map<String, String> orderInfo = new TreeMap<String, String>();
		for (String nameValuePair : nameValuePairs) {
			String[] elements = nameValuePair.split("=");
			if (elements.length < 2) {
				// no value, put empty
				orderInfo.put(elements[0], "");
			} else {
				try {
					orderInfo.put(elements[0], URLDecoder.decode(elements[1], encoding));
				} catch (UnsupportedEncodingException e) {
					log.error(e.getMessage(), e);
					orderInfo.put(elements[0], elements[1]);
				}
			}
		}
		return orderInfo;
	}

	public static Map<String, String> splitQueryString(String queryString) {
		return splitMapString(queryString, "&", DEFAULT_ENCODING);
	}

	public static String deleteLastOccurrence(String origString, String str4Delete) {
		if (isEmpty(origString)) {
			return "";
		}
		if (isEmpty(str4Delete)) {
			return origString;
		}
		StringBuilder result = new StringBuilder(origString);
		int lastIndex = origString.lastIndexOf(str4Delete);
		if (lastIndex != -1) {
			result.delete(lastIndex, lastIndex + str4Delete.length());
		}
		return result.toString();
	}

	public static String blur(String source) {
		return blur(source, DEFAULT_ENCODING);
	}

	public static String deblur(String bluredString) {
		return deblur(bluredString, DEFAULT_ENCODING);
	}

	public static String blur(String source, String encoding) {
		if (isBlank(source)) {
			return source;
		} else {
			String reversed = new StringBuilder(source).reverse().toString();
			String base64String = "";
			try {
				base64String = Base64.encodeBase64String(reversed.getBytes(encoding));
			} catch (UnsupportedEncodingException e) {
				log.error(formatMessage("Cannot blur string.  source={0}", source), e);
			}
			int indexOfEqualSign = base64String.indexOf("=");
			indexOfEqualSign = (indexOfEqualSign == -1) ? base64String.length() : indexOfEqualSign;
			return new StringBuilder(base64String.substring(0, indexOfEqualSign)).reverse()
					.append(base64String.substring(indexOfEqualSign)).toString();
		}
	}

	public static String deblur(String bluredString, String encoding) {
		if (isBlank(bluredString)) {
			return bluredString;
		} else {
			int indexOfEqualSign = bluredString.indexOf("=");
			indexOfEqualSign = (indexOfEqualSign == -1) ? bluredString.length() : indexOfEqualSign;
			String base64String = new StringBuilder(bluredString.substring(0, indexOfEqualSign)).reverse()
					.append(bluredString.substring(indexOfEqualSign)).toString();

			String reversed = "";
			try {
				reversed = new String(Base64.decodeBase64(base64String), encoding);
			} catch (UnsupportedEncodingException e) {
				log.error(formatMessage("Cannot deblur string.  base64String={0}", base64String), e);
			}

			String source = new StringBuilder(reversed).reverse().toString();
			return source;
		}
	}

	/**
	 * A compensation of Integer.toBinaryString(). E.g, toBinaryString(1, 3) = 001 toBinaryString(2, 4) = 0010
	 * toBinaryString(8, 2) = 100
	 * 
	 * @param value
	 * @param bits
	 * @return
	 */
	public static String toBinaryString(int value, int bits) {
		String binaryString = Integer.toBinaryString(value);
		return StringUtils.leftPad(binaryString, bits, "0");
	}

	/**
	 * 将二进制转换成16进制
	 * 
	 * @param buf
	 * @return
	 */
	public static String parseByte2HexStr(byte[] buf) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 将16进制转换为二进制
	 * 
	 * @param hexStr
	 * @return
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	/**
	 * 删除特殊字符
	 * 
	 * @param str
	 * @return
	 */
	public static String removeSpechars(String str) {
		str = StringEscapeUtils.unescapeJava(str);
		if (str == null)
			return null;
		String htmlStr = str;
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;
		java.util.regex.Pattern p_special;
		java.util.regex.Matcher m_special;
		java.util.regex.Pattern p_special_sequence;
		java.util.regex.Matcher m_special_sequence;
		java.util.regex.Pattern p_transferred;
		java.util.regex.Matcher m_transferred;
		java.util.regex.Pattern p_special2;
		java.util.regex.Matcher m_special2;
		try {
			// 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
			// 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
			// 定义HTML标签的正则表达式
			String regEx_html = "<[^>]+>";
			// 定义一些特殊字符的正则表达式 如：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			String regEx_special = "\\&[a-zA-Z]{1,10};";
			// 定义一些特殊字符的正则表达式 如：&#10;
			String regEx_special_sequence = "\\&#[a-zA-Z0-9]{1,10};";
			// 定义一些特殊字符的正则表达式 如：\r\n;
			String regEx_transferred = "[\t|\r|\n|\b|\f]*";
			// 特殊字符
			String regEx_special2 = "[`~@#$%^&*+=|{}\\\\[\\\\].<>/?~！@#￥%……&*——+|{}【】‘；”“’。，、？]";
			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签
			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签
			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签
			p_special = Pattern.compile(regEx_special, Pattern.CASE_INSENSITIVE);
			m_special = p_special.matcher(htmlStr);
			htmlStr = m_special.replaceAll(""); // 过滤特殊标签
			p_special_sequence = Pattern.compile(regEx_special_sequence, Pattern.CASE_INSENSITIVE);
			m_special_sequence = p_special_sequence.matcher(htmlStr);
			htmlStr = m_special_sequence.replaceAll(""); // 过滤转义序列
			p_transferred = Pattern.compile(regEx_transferred, Pattern.CASE_INSENSITIVE);
			m_transferred = p_transferred.matcher(htmlStr);
			htmlStr = m_transferred.replaceAll(""); // 过滤特空格、换行
			p_special2 = Pattern.compile(regEx_special2, Pattern.CASE_INSENSITIVE);
			m_special2 = p_special2.matcher(htmlStr);
			htmlStr = m_special2.replaceAll(""); // 特殊字符
			textStr = htmlStr;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return textStr.trim();
	}
}
