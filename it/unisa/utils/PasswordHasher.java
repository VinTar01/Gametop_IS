package it.unisa.utils;

import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

//conversione della stringa della password in hash
public class PasswordHasher
{
    public static String hash(final String text) {
        try {
            final MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(text.getBytes(StandardCharsets.UTF_8));
            final byte[] digest = md.digest();
            return String.format("%064x", new BigInteger(1, digest));
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return text;
        }
    }
}