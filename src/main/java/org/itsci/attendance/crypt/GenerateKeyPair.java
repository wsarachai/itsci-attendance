package org.itsci.attendance.crypt;

import com.google.zxing.WriterException;

import javax.crypto.Cipher;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class GenerateKeyPair {
    public static void generate() throws NoSuchAlgorithmException, IOException, WriterException {
        KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
        gen.initialize(2048);
        KeyPair keyPair = gen.generateKeyPair();
        RSAPublicKey pkey = (RSAPublicKey) keyPair.getPublic();
        PrivateKey prvKey = keyPair.getPrivate();
        PublicKey pubKey = keyPair.getPublic();

        String pubkey = Base64.getEncoder().encodeToString(pubKey.getEncoded());
        String prvkey = Base64.getEncoder().encodeToString(prvKey.getEncoded());

        String outFile = "public";
        FileWriter out = new FileWriter(outFile + ".key");
        out.write(prvkey);
        out.close();

        out = new FileWriter(outFile + ".pub");
        out.write(pubkey);
        out.close();
    }

    private String readFileText(String filename) {
        String txt = null;
        try {
            File file = new File(filename);
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();
            txt = new String(data, "UTF-8");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return txt;
    }

    public String loadPublicKey() {
        return readFileText("public.pub");
    }

    public String loadPrivateKey() {
        return readFileText("public.key");
    }

    public PublicKey getPublicKey() {
        PublicKey pubKey = null;
        try {
            String publicK = loadPublicKey();
            byte[] publicBytes = Base64.getDecoder().decode(publicK);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            pubKey = keyFactory.generatePublic(keySpec);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return pubKey;
    }

    public PrivateKey getPrivateKey() {
        PrivateKey prvKey = null;
        try {
            String privateK = loadPrivateKey();
            byte[] privateBytes = Base64.getDecoder().decode(privateK);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            prvKey = keyFactory.generatePrivate(keySpec);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return prvKey;
    }

    public String encode(String toEncode) throws Exception {
        PublicKey publicKey = getPublicKey();
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] bytes = cipher.doFinal(toEncode.getBytes(StandardCharsets.UTF_8));
        return new String(Base64.getEncoder().encode(bytes));
    }

    public String decode(String toDecode) throws Exception {
        PrivateKey privateKey = getPrivateKey();
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] bytes = cipher.doFinal(Base64.getDecoder().decode(toDecode));
        return new String(bytes);

    }

}
