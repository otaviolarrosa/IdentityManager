package com.otaviolarrosa.identitymanager.infrastructure.encryption;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otaviolarrosa.identitymanager.infrastructure.config.ConfigUtility;

import javax.crypto.spec.PBEKeySpec;

@Service
public class Encryption {
	
	@Autowired
	private ConfigUtility config;
	
    public String encrypt(String strToEncrypt) 
    { 
        try {
            byte[] iv = createDefaultByteArray(); 
            IvParameterSpec ivspec = new IvParameterSpec(iv); 
  
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256"); 
            
    		String encryptionSecretKey = config.getProperty("secretKey");
    		String encryptionSaltKey = config.getProperty("saltKey");            
            PBEKeySpec spec = new PBEKeySpec(encryptionSecretKey.toCharArray(), encryptionSaltKey.getBytes(), 65536, 256); 
            SecretKey tmp = factory.generateSecret(spec); 
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES"); 
  
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec); 
            
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8))); 
        } 
        catch (Exception e) { 
            //TODO: replace with logger
            System.out.println("Error while encrypting: " + e.toString()); 
        } 
        return null; 
    }
    
    public String decrypt(String strToDecrypt) 
    { 
        try { 
            byte[] iv = createDefaultByteArray();
            IvParameterSpec ivspec = new IvParameterSpec(iv); 
  
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256"); 
  
    		String encryptionSecretKey = config.getProperty("secretKey");
    		String encryptionSaltKey = config.getProperty("saltKey");
            PBEKeySpec spec = new PBEKeySpec(encryptionSecretKey.toCharArray(), encryptionSaltKey.getBytes(), 65536, 256); 
            SecretKey tmp = factory.generateSecret(spec); 
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES"); 
  
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING"); 
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec); 
            
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt))); 
        } 
        catch (Exception e) { 
            //TODO: replace with logger
        	System.out.println("Error while decrypting: " + e.toString()); 
        } 
        return null; 
    }
    
    private byte[] createDefaultByteArray() {
    	byte[] defaultByteArray = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    	return defaultByteArray;
    }
}
