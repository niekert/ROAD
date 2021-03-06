/*
 * Copyright by AIDaS.
 */

package road.security;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * This class provides the functions which can be used to secure the application.
 * 
 * @author Geert
 */
public class Security {
    
    /**
     * The number of bytes used for the salt size.
     */
    public static final int SALT_BYTE_SIZE = 256;
    
    /**
     * The number of iterations done when hashing the password. (creates a slower hash)
     */
    private static final int HASH_INTERATION_COUNT = 1000;
    
    /**
     * Generate a new random salt.
     * @return the randomly generated salt.
     */
    public static byte[] generateSalt() {
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[SALT_BYTE_SIZE];
        sr.nextBytes(salt);
        
        return salt;
    }
    
    /**
     * Process the provided password by encrypting and hashing it.
     * @param password the password to process.
     * @param username the username used to encrypt the password.
     * @param salt the salt used to hash the password.
     * @return the processed password.
     */
    public static String processPassword(String password, String username, byte[] salt)
            throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException,
            InvalidKeySpecException {
        
        String encryptedPassword = Security.encrypt(password, username + password);
        
        return Security.hash(encryptedPassword, salt);
    }
    
    /**
     * Encrypt the provided string.
     * @param toEncrypt the string which needs to be encrypted.
     * @param key the key to encrypt the provided string.
     * @return the encryped string.
     */
    public static String encrypt(String toEncrypt, String key) 
            throws NoSuchAlgorithmException, NoSuchPaddingException, 
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "Blowfish");
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        
        byte[] encryped = cipher.doFinal(toEncrypt.getBytes());
        return road.utils.Byte.arrayToString(encryped);
    }
    
    /**
     * Hash the string with the provided salt.
     * @param toHash the string to hash.
     * @param salt the salt to use to hash the provided string.
     * @return the hashed string.
     */
    public static String hash(String toHash, byte[] salt) 
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        
        PBEKeySpec spec = new PBEKeySpec(toHash.toCharArray(), salt, HASH_INTERATION_COUNT, 1026);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] encoded = skf.generateSecret(spec).getEncoded();
        return road.utils.Byte.arrayToString(encoded);
    }
}
