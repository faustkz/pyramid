package com.shirinov.utils;

/**
 * User: Rustam Shirinov
 * Date: 03/08/14
 * Time: 12:46 PM
 */
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHash {
    private static final byte[] SALT = {23, -115, 110, 57};
    private static final String HASH_ALGORITHM = "SHA-256";
    private static final String STRING_ENCODING = "UTF-8";
    private static MessageDigest digest;

    private static MessageDigest getDigest() throws NoSuchAlgorithmException {
        if (digest == null) {
            digest = MessageDigest.getInstance(HASH_ALGORITHM);
        }
        return digest;
    }

    public static byte[] hash(String val) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        if (val == null) {
            return null;
        }
        byte[] bytes = val.getBytes(STRING_ENCODING);
        byte[] res = hash(bytes);
        return res;
    }

    public static byte[] hash(byte[] val) throws NoSuchAlgorithmException {
        MessageDigest digest = getDigest();
        digest.update(SALT);
        digest.update(val);
        byte[] res = digest.digest();
        return res;
    }
}
