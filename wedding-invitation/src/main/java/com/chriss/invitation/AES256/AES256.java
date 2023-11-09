package com.chriss.invitation.AES256;

import java.io.ByteArrayOutputStream;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import jakarta.xml.bind.DatatypeConverter;

public class AES256 {
	private byte[] masterKey;
    private String addKey;

    public AES256(String masterKey, String addKey) {
        this.masterKey = strToByte(masterKey);
        this.addKey = addKey;
    }

    public AES256(byte[] masterKey, String addKey) {
        this.masterKey = masterKey;
        this.addKey = addKey;
    }

    public String encrypt(String plainText) {
        try {
            byte[] textBytes = plainText.getBytes();
            byte[] masterBytes = masterKey;
            byte[] addBytes = addKey.getBytes();

            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            
            SecretKeySpec secureKey = new 
                SecretKeySpec(masterBytes, "AES");

            byte[] IV = new byte[12];
            SecureRandom random = new SecureRandom();
            random.nextBytes(IV);

            AlgorithmParameterSpec gcmIv = new
                GCMParameterSpec(128, IV);

            cipher.init(Cipher.ENCRYPT_MODE, secureKey, gcmIv);

            cipher.updateAAD(addBytes);

            byte[] cipherText = cipher.doFinal(textBytes);

            ByteArrayOutputStream b = new ByteArrayOutputStream();
            b.write(IV);
            b.write(cipherText);

            return byteToHexString(b.toByteArray());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public String decrypt(String cipherText) {
        try {

            byte[] masterBytes = masterKey; // master key
            // nonce(IV) + ciphertext
            // nonce size = 12 bytes = 96 bits (GCM default)
            byte[] cipherBytes = strToByte(cipherText); 
            byte[] addBytes = addKey.getBytes(); // additional key

            // get AES GCM instance
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

            // get IV from cipherBytes (first 12 bytes)
            AlgorithmParameterSpec gcmIv = new 
                GCMParameterSpec(128, cipherBytes, 0, 12);
            // change type of masterkey to key. 
            // this is aes-256 since masterkey is 32 bytes.
            SecretKeySpec secureKey = new 
                SecretKeySpec(masterBytes, "AES");

            // initialize cipher. 
            // operation mode : decrypt with master key and IV
            cipher.init(Cipher.DECRYPT_MODE, secureKey, gcmIv);

            // add aditional data = additional key
            cipher.updateAAD(addBytes);

            // do operation(decrypt) with cipherBytes[12:](ciphertext)
            byte[] bytes = cipher.doFinal(cipherBytes, 12, 
                                          cipherBytes.length - 12);

            // return plain text type string
            return byteToString(bytes);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private byte[] strToByte(String target) {
        return DatatypeConverter.parseHexBinary(target);
    }

    private String byteToString(byte[] target) {
        return new String(target);
    }

    private String byteToHexString(byte[] target) {
        return DatatypeConverter.printHexBinary(target);
    }
}
