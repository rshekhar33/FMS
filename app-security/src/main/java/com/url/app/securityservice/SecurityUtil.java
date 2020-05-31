package com.url.app.securityservice;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import com.url.app.utility.AppCommon;
import com.url.app.utility.AppConstant;

public class SecurityUtil {
	private static final String DL = "__bcdef567kop48__";

	private static final String A = "a";
	private static final String B = "b";
	private static final String C = "c";
	private static final String D = "d";
	private static final String E = "e";
	private static final String F = "f";

	private final int keySize;
	private final int iterationCount;
	private final Cipher cipher;

	public SecurityUtil(int keySize) {
		this(keySize, 1000);
	}

	public SecurityUtil(SecurityInfo securityInfo) {
		this(securityInfo.getKeySize(), securityInfo.getIterationCount());
	}

	public SecurityUtil(int keySize, int iterationCount) {
		this.keySize = keySize;
		this.iterationCount = iterationCount;
		try {
			cipher = Cipher.getInstance(AppConstant.CIPHER_TRANSFORMATION);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			throw fail(e);
		}
	}

	public String encrypt(String salt, String iv, String passphrase, String plaintext) {
		try {
			SecretKey key = generateKey(salt, passphrase);
			byte[] encrypted = doFinal(Cipher.ENCRYPT_MODE, key, iv, plaintext.getBytes(StandardCharsets.UTF_8.name()));
			return base64(encrypted);
		} catch (UnsupportedEncodingException e) {
			throw fail(e);
		}
	}

	public String decrypt(SecurityInfo securityInfo) {
		return decrypt(securityInfo.getSalt(), securityInfo.getIv(), securityInfo.getPassPhrase(), securityInfo.getCipherText());
	}

	public String decrypt(String salt, String iv, String passphrase, String ciphertext) {
		try {
			SecretKey key = generateKey(salt, passphrase);
			byte[] decrypted = doFinal(Cipher.DECRYPT_MODE, key, iv, base64(ciphertext));
			return new String(decrypted, StandardCharsets.UTF_8.name());
		} catch (UnsupportedEncodingException e) {
			throw fail(e);
		}
	}

	private byte[] doFinal(int encryptMode, SecretKey key, String iv, byte[] bytes) {
		try {
			cipher.init(encryptMode, key, new IvParameterSpec(hex(iv)));
			return cipher.doFinal(bytes);
		} catch (InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			throw fail(e);
		}
	}

	private SecretKey generateKey(String salt, String passphrase) {
		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance(AppConstant.CIPHER_FACTORY_ALGORITHM);
			KeySpec spec = new PBEKeySpec(passphrase.toCharArray(), hex(salt), iterationCount, keySize);
			return new SecretKeySpec(factory.generateSecret(spec).getEncoded(), AppConstant.CIPHER_KEY_ALGORITHM);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw fail(e);
		}
	}

	public static String random(int length) {
		byte[] salt = new byte[length];
		new SecureRandom().nextBytes(salt);

		return hex(salt);
	}

	public static String base64(byte[] bytes) {
		return Base64.encodeBase64String(bytes);
	}

	public static byte[] base64(String str) {
		return Base64.decodeBase64(str);
	}

	public static String hex(byte[] bytes) {
		return Hex.encodeHexString(bytes);
	}

	public static byte[] hex(String str) {
		try {
			return Hex.decodeHex(str.toCharArray());
		} catch (DecoderException e) {
			throw new IllegalStateException(e);
		}
	}

	private IllegalStateException fail(Exception e) {
		return new IllegalStateException(e);
	}

	/**
	 * Parameter input will be in format of string that will be prepared by
	 * concatenating parameters - cipherText, iv, salt, passPhrase, keySize,
	 * iterationCount in a random order. And at last indices will also be part
	 * of this string where 3 random indices will be converted to corresponding
	 * alphabet. e.g. 0-represents a , 1-represents b and so on..
	 *
	 * At 0th position there will always be CipherText At 1st position -> IV At
	 * 2nd position -> SALT At 3rd position -> PassPhrase At 4th position ->
	 * IterationCount At 5th position -> KeySize At 6th position -> Indices [ in
	 * which above six are randomized]
	 *
	 * @param input ciphered
	 * @return String
	 */
	public static String decrypt(String input) {
		final String[] values = input.split(DL);
		// last element indicates indices
		final String indices = values[values.length - 1];
		final int[] indexes = convert(indices);
		final SecurityInfo securityInfo = new SecurityInfo(values, indexes);
		final SecurityUtil securityUtil = new SecurityUtil(securityInfo);

		return securityUtil.decrypt(securityInfo);
	}

	private static int[] convert(String indices) {
		String[] indexes = indices.split(AppConstant.COMMA_STRING);

		int[] ints = new int[indexes.length];
		for (int i = 0; i < indexes.length; i++) {
			ints[i] = getNum(indexes[i]);
		}

		return ints;
	}

	private static int getNum(String str) {
		switch (str) {
		case A:
			return 0;
		case B:
			return 1;
		case C:
			return 2;
		case D:
			return 3;
		case E:
			return 4;
		case F:
			return 5;
		default:
			return AppCommon.toInteger(str);
		}
	}
}