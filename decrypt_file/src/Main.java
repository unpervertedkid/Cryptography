import javax.crypto.Cipher;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

/*
Write a program “decrypt_file”. This program will decrypt the file “sensitive.txt” in
the directory “hw3” using the key “priv_key”. The key will be read from the file “priv_key”.
The decrypted file will overwrite the encrypted file “sensitive.txt”.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        decryptFile("sensitive.txt", "priv_key");
    }
    private static void decryptFile(String keyFile, String encryptedFile) throws Exception {
        // Read the key from the file
        File file = new File(keyFile);
        FileInputStream fis = new FileInputStream(file);
        byte[] keyBytes = new byte[(int) file.length()];
        fis.read(keyBytes);
        fis.close();

        // Generate the private key
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PrivateKey privKey = kf.generatePrivate(spec);

        // Read the encrypted file
        file = new File(encryptedFile);
        fis = new FileInputStream(file);
        byte[] encryptedBytes = new byte[(int) file.length()];
        fis.read(encryptedBytes);
        fis.close();

        // Decrypt the file
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privKey);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        // Write the decrypted file
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(decryptedBytes);
        fos.close();
    }
}
