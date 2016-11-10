package com.parkingwng.lang.kit;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 0.1.2
 */
final public class HashKit {

    private HashKit(){}

    public static byte[] hash(String algorithm, String content) throws NoSuchAlgorithmException {
        final MessageDigest md = MessageDigest.getInstance(algorithm);
        return md.digest(content.getBytes());
    }

    public static byte[] toMd5(String content) {
        try {
            return hash("MD5", content);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("MD5 Algorithm is not supported !");
        }
    }

    public static String toMd5Hex(String content) {
        return StringKit.hex(toMd5(content));
    }

    public static byte[] toSHA1(String content) {
        try {
            return hash("SHA-1", content);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("SHA-256 Algorithm is not supported !");
        }
    }

    public static String toSHA1Hex(String content) {
        return StringKit.hex(toSHA1(content));
    }

    public static byte[] toSHA256(String content) {
        try {
            return hash("SHA-256", content);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("SHA-256 Algorithm is not supported !");
        }
    }

    public static String toSHA256Hex(String content) {
        return StringKit.hex(toSHA256(content));
    }
}
