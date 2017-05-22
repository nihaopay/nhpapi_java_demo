package com.aurfy.haze.utils;

import static com.aurfy.haze.utils.StringUtils.DEFAULT_ENCODING;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jcajce.provider.digest.SHA3.DigestSHA3;
import org.bouncycastle.util.encoders.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author gqi
 *
 */
public final class SecurityUtils {

	private static final Logger logger = LoggerFactory.getLogger(SecurityUtils.class);

	/**
	 * String to hold name of the encryption algorithm.
	 */
	public static final int BUFFER_SIZE = 2048;

	public static final String ALGORITHM_RSA = "RSA";

	public static final String ALGORITHM_AES = "AES";

	public static final String MD5_WITH_RSA = "MD5withRSA";

	public static final String TRANSFORMER1_AES = "AES/ECB/NoPadding";

	public static final String TRANSFORMER2_AES = "AES/CBC/PKCS5Padding";

	public static final String PRIVATEKEY = "PRIVATEKEY";
	public static final String PUBLICKEY = "PUBLICKEY";

	private SecurityUtils() {
	}

	/**
	 * generate a random UUID (36 characters with dash)
	 * 
	 */
	public static String UUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid.replaceAll("-", "");
	}

	/**
	 * calculate the string hash by SHA3 algorithm (size=512 and encoding=UTF-8)
	 * 
	 * @param input
	 * @return
	 */
	public static String SHA3(String input) {
		return SHA3(input, DEFAULT_ENCODING);
	}

	/**
	 * calculate the string hash by SHA3 algorithm (size=512) default use the longest length of 512
	 * 
	 * @param input
	 * @param encoding
	 * @return
	 */
	public static String SHA3(String input, String encoding) {
		if (input == null) {
			throw new NullPointerException("input can not be null for SHA3");
		}
		try {
			DigestSHA3 md = new DigestSHA3(512); // same as DigestSHA3 md = new SHA3.Digest512();
			md.update(input.getBytes(encoding));
			byte[] digest = md.digest();
			return Hex.toHexString(digest);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * calculate the string with SHA1
	 * 
	 * @param str
	 * @param encoding
	 * @return
	 */
	public static String SHA1(String str, String encoding) {

		if (str == null) {
			return null;
		}

		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("SHA-1");
			messageDigest.reset();
			messageDigest.update(str.getBytes(encoding));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		byte[] byteArray = messageDigest.digest();

		StringBuffer strBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				strBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
			else
				strBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}

		return strBuff.toString();
	}

	/**
	 * get the md5 hash of a string
	 * 
	 * @param str
	 * @return
	 */
	public static String MD5(String str) {
		return MD5(str, DEFAULT_ENCODING);
	}

	// TODO: move to bouncycastle md5
	public static String MD5(String str, String encoding) {

		if (str == null) {
			return null;
		}

		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes(encoding));
		} catch (NoSuchAlgorithmException e) {
			if (logger.isErrorEnabled()) {
				logger.error("MD5 algorithm not supported", e);
			}
			return str;
		} catch (UnsupportedEncodingException e) {
			if (logger.isErrorEnabled()) {
				logger.error("Encoding \"{}\" not supported for MD5 algorithm.", encoding, e);
			}
			return str;
		}

		byte[] byteArray = messageDigest.digest();

		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}

		return md5StrBuff.toString();
	}

	/**
	 * Convert string to RSAKey, including privateKey and publicKey.
	 * 
	 * @param keyAsString
	 * @param keyType
	 * @return
	 */
	public static Key convertRSAKey(byte[] keyArray, String keyType) {
		try {
			KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);

			// 私钥需要使用pkcs8格式，公钥使用x509格式; 不能将String强制转换成Key，防止出错
			if (PRIVATEKEY.equals(keyType)) {
				PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyArray);
				PrivateKey key = keyFactory.generatePrivate(pkcs8KeySpec);
				return key;
			} else if (PUBLICKEY.equals(keyType)) {
				X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyArray);
				PublicKey key = keyFactory.generatePublic(x509KeySpec);
				return key;
			}
		} catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
			logger.error("convert RSA key error", e);
			throw new RuntimeException("convert RSA key error", e);
		}
		return null;
	}

	/**
	 * 用私钥对信息生成数字签名，这里不支持用公钥对信息生成数字签名
	 * 
	 * @param content
	 *            要签名的信息
	 * @param priKey
	 *            私钥
	 * @param chartset
	 *            字符集名称
	 * @return
	 */
	public static byte[] calculateSignature(String content, PrivateKey priKey, Charset charset) {
		Signature signet = null;
		try {
			signet = Signature.getInstance(MD5_WITH_RSA);
			signet.initSign(priKey);
			signet.update(content.getBytes(charset));
			byte[] signed = signet.sign();// 对信息的数字签名
			return signed;
		} catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
			logger.error("calculate signature error", e);
			throw new RuntimeException("calculate signature error", e);
		}
	}

	/**
	 * 用公钥对信息生成数字签名，这里不支持用私钥对信息生成数字签名
	 * 
	 * @param pubKey
	 * @param content
	 * @param signed
	 * @param charset
	 * @return
	 */
	public static boolean validateSignature(PublicKey pubKey, String content, byte[] signed, Charset charset) {
		try {
			Signature signedcheck = Signature.getInstance(MD5_WITH_RSA);
			signedcheck.initVerify(pubKey);
			signedcheck.update(content.getBytes(charset));
			if (signedcheck.verify(signed)) {
				return true;
			} else {
				return false;
			}
		} catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
			logger.error("validate signature error", e);
			throw new RuntimeException("validate signature error", e);
		}
	}

	/**
	 * Encryption by AES algorithm - the name of the RNG algorithm. See the SecureRandom section in the Java
	 * Cryptography Architecture Standard Algorithm Name Documentation for information about standard RNG algorithm
	 * names. https://docs.oracle.com/javase/8/docs/technotes/guides/security/StandardNames.html#SecureRandom
	 * 
	 * @param content
	 *            需要加密的内容
	 * @param password
	 *            加密密码
	 * @param charset
	 *            字符类型
	 * @return
	 */
	public static byte[] encryptByAES(byte[] content, String password, Charset charset) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance(ALGORITHM_AES);
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(password.getBytes(charset));
			kgen.init(128, secureRandom);// ??? TODO: not understand 128位
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, ALGORITHM_AES);
			Cipher cipher = Cipher.getInstance(ALGORITHM_AES);// 创建密码器
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(content);
			return result; // 加密
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException e) {
			logger.error("encrypt by AES error", e);
			throw new RuntimeException("encrypt by AES error", e);
		}
	}

	/**
	 * Decryption by AES
	 * 
	 * algorithm - the name of the RNG algorithm. See the SecureRandom section in the Java Cryptography Architecture
	 * Standard Algorithm Name Documentation for information about standard RNG algorithm names.
	 * https://docs.oracle.com/javase/8/docs/technotes/guides/security/StandardNames.html#SecureRandom
	 * 
	 * @param content
	 *            待解密内容
	 * @param password
	 *            解密密钥
	 * @param charsetName
	 *            字符类型
	 * @return
	 */
	public static byte[] decryptByAES(byte[] content, String password, Charset charset) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance(ALGORITHM_AES);
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(password.getBytes(charset));
			kgen.init(128, secureRandom);
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, ALGORITHM_AES);
			Cipher cipher = Cipher.getInstance(ALGORITHM_AES);// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(content);
			return result; // 加密
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException e) {
			logger.error("decrypt by AES error", e);
			throw new RuntimeException("decrypt by AES error", e);
		}
	}

	/**
	 * encrypt data by public key or private key, see the Cipher.ENCRYPT_MODE. <br />
	 * 私钥加密--公钥解密 或者 公钥加密--私钥解密
	 * 
	 * @param content
	 * @param key
	 * @return
	 */
	public static byte[] encryptByRSA(byte[] content, Key key) {
		try {
			KeyFactory keyFactory = KeyFactory.getInstance(SecurityUtils.ALGORITHM_RSA);
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.ENCRYPT_MODE, key);// public Key
			byte[] encryptData = cipher.doFinal(content);
			return encryptData;
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException e) {
			logger.error("encrypt by RSA error", e);
			throw new RuntimeException("encrypt by RSA error", e);
		}
	}

	/**
	 * decrypt data by private key or public key, see the Cipher.DECRYPT_MODE. <br />
	 * 私钥加密--公钥解密 或者 公钥加密--私钥解密
	 *
	 * @param content
	 * @param key
	 */
	public static byte[] decryptByRSA(byte[] content, Key key) {
		try {
			KeyFactory keyFactory = KeyFactory.getInstance(SecurityUtils.ALGORITHM_RSA);
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] decryptData = cipher.doFinal(content);
			return decryptData;
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException e) {
			logger.error("decrypt by RSA error", e);
			throw new RuntimeException("decrypt by RSA error", e);
		}

	}

	/**
	 * base64 and ASE
	 */
	public String encryptKey(String plainKey, String asePwd) {
		byte[] encryptKeyArray = encryptByAES(plainKey.getBytes(), asePwd, EncodingUtils.UTF_16);
		String encryptKey = EncodingUtils.encodeBase64(encryptKeyArray, EncodingUtils.UTF_8);
		return encryptKey;
	}

	/**
	 * base64 and ASE
	 */
	public String decriptKey(String str, String asePwd) {
		byte[] encryptKey = EncodingUtils.decodeBase64(str, EncodingUtils.UTF_8);
		byte[] encryptKeyArray = decryptByAES(encryptKey, asePwd, EncodingUtils.UTF_16);

		return new String(encryptKeyArray);
	}

	public static String encryptRSAKey(Key priKey) {
		byte[] encryptKeyArray = encryptByAES(priKey.getEncoded(), "123456", EncodingUtils.UTF_16);
		String encryptKey = EncodingUtils.encodeBase64(encryptKeyArray, EncodingUtils.UTF_8);
		return encryptKey;
	}

	public static Key decriptRSAKey(String priKey, String keyType) {
		byte[] encryptedPriKey2 = EncodingUtils.decodeBase64(priKey, EncodingUtils.UTF_8);
		byte[] decryptPriKeyArray = SecurityUtils.decryptByAES(encryptedPriKey2, "123456", EncodingUtils.UTF_16);
		Key priKey2 = convertRSAKey(decryptPriKeyArray, keyType);
		return priKey2;
	}

}
