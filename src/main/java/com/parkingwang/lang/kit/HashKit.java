package com.parkingwang.lang.kit;

import com.parkingwang.lang.ArgumentedSupplier;
import com.parkingwang.lang.data.ArgumentedThreadLocal;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Yoojia Chen (yoojiachen@gmail.com)
 * @since 1.0
 */
final public class HashKit {

    private HashKit(){}

    private static ArgumentedThreadLocal<MessageDigest, String> SHA1 = new ArgumentedThreadLocal<>(new ArgumentedSupplier<MessageDigest, String>() {
        @Override
        public MessageDigest call(String algorithm) {
            try {
                return MessageDigest.getInstance(algorithm);
            } catch (NoSuchAlgorithmException e) {
                throw new IllegalArgumentException(e);
            }
        }
    });

    public static byte[] hash(String algorithm, String content) throws NoSuchAlgorithmException {
        final MessageDigest md = MessageDigest.getInstance(algorithm);
        return md.digest(content.getBytes());
    }

    public static byte[] toMd5(String content) {
        try {
            return hash("md5", content);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("MD5 Algorithm is not supported !");
        }
    }

    public static String toMd5Hex(String content) {
        return StringKit.hex(toMd5(content));
    }

    public static byte[] toSHA1(String content) {
        final MessageDigest sha1 = SHA1.get("sha-1");
        sha1.reset();
        sha1.update(content.getBytes());
        return sha1.digest();
    }

    public static String toSHA1Hex(String content) {
        return StringKit.hex(toSHA1(content));
    }

    public static byte[] toSHA256(String content) {
        try {
            return hash("sha-256", content);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("SHA-256 Algorithm is not supported !");
        }
    }

    public static String toSHA256Hex(String content) {
        return StringKit.hex(toSHA256(content));
    }
}
