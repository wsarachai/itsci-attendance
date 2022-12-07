package org.itsci.attendance;

import org.itsci.attendance.crypt.GenerateKeyPair;
import org.itsci.attendance.qrcode.QRCode;
import org.json.JSONObject;

public class TestMain {

    public static void main(String[] args) {
        GenerateKeyPair g = new GenerateKeyPair();

        try {
//            GenerateKeyPair.generate();
            String txtToEncrypt = "I'll be back!!";
            System.out.println("Encrypting text " + txtToEncrypt);
            String encryptedText = g.encode(txtToEncrypt);
            System.out.print("Encrypted text: ");
            System.out.println(encryptedText);
            System.out.print("Decrypting result : ");
            String decryptedText = g.decode(encryptedText);
            System.out.println(decryptedText);

            QRCode.generate(creatingJsonString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String creatingJsonString() {
        GenerateKeyPair g = new GenerateKeyPair();

        JSONObject data = new JSONObject();
        data.put("sec_id", "sec-1001");
        data.put("attend_id", "attend-1001");
        data.put("key", g.loadPublicKey());
        return data.toString(2);
    }

}
