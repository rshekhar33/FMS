package com.url.app.security.util;

import com.url.app.utility.AppCommon;

/**
 * At 0th position there will always be CipherText At 1st position -> IV At 2nd
 * position -> SALT At 3rd position -> PassPhrase At 4th position ->
 * IterationCount At 5th position -> KeySize
 */
public class SecurityInfo {
	private String iv;
	private String salt;
	private String passPhrase;
	private String cipherText;
	private int iterationCount;
	private int keySize;

	public SecurityInfo(String[] values, int[] indexes) {
		for (int i = 0; i < indexes.length; i++) {
			final int j = indexes[i];
			final String val = values[i];
			switch (j) {
			case 0:
				setCipherText(val);
				break;
			case 1:
				setIv(val);
				break;
			case 2:
				setSalt(val);
				break;
			case 3:
				setPassPhrase(val);
				break;
			case 4:
				setIterationCount(AppCommon.toInteger(val));
				break;
			case 5:
				setKeySize(AppCommon.toInteger(val));
				break;
			default:
				break;
			}
		}
	}

	public String getIv() {
		return iv;
	}

	public void setIv(String iv) {
		this.iv = iv;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getPassPhrase() {
		return passPhrase;
	}

	public void setPassPhrase(String passPhrase) {
		this.passPhrase = passPhrase;
	}

	public String getCipherText() {
		return cipherText;
	}

	public void setCipherText(String cipherText) {
		this.cipherText = cipherText;
	}

	public int getIterationCount() {
		return iterationCount;
	}

	public void setIterationCount(int iterationCount) {
		this.iterationCount = iterationCount;
	}

	public int getKeySize() {
		return keySize;
	}

	public void setKeySize(int keySize) {
		this.keySize = keySize;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SecurityInfo [iv=").append(iv)
			.append(", salt=").append(salt)
			.append(", passPhrase=").append(passPhrase)
			.append(", cipherText=").append(cipherText)
			.append(", iterationCount=").append(iterationCount)
			.append(", keySize=").append(keySize)
			.append("]");

		return builder.toString();
	}
}