import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

/*
Write a program “encrypt_file”. This program will encrypt a file “sensitive.txt” in the directory
“hw3” on the key “pub_key”. The key will be read from the file “pub_key”. The encrypted file
will overwrite the original file “sensitive.txt”.
Make sure that the original (unencrypted) file “sensitive.txt” is placed into the directory “hw3”.
In the terminal window, display the first 100 bytes of the original (unencrypted) file
“sensitive.txt” using the “head” command. Run the program “encrypt_file”. Display the first
100 bytes of the encrypted file “sensitive.txt” using the “hexdump” command.
 */
public class Main {
    public static void main(String[] args) throws Exception {
       encrptyFile("sensitive.txt", "pub_key");
    }
    private static void  encrptyFile(String fileName, String encryptionKey) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        //Read the file
        File file = new File(fileName);
        byte[] fileBytes = Files.readAllBytes(file.toPath());
        //Read the public key
        File pubKeyFile = new File(encryptionKey);
        byte[] pubKeyBytes = Files.readAllBytes(pubKeyFile.toPath());
        //Generate the public key
        X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(pubKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey pubKey = keyFactory.generatePublic(pubKeySpec);
        //Encrypt the file
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        byte[] encryptedFileBytes = cipher.doFinal(fileBytes);
        //Write the encrypted file
        Files.write(file.toPath(), encryptedFileBytes);
    }
}
