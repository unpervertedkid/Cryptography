import java.io.FileOutputStream;
import java.security.*;

public class Main {
    /*
    Write a program “generate_keys”. This program will randomly generate a pair of 3072-bit RSA
    keys, and then it will save the public key into a file “pub_key” and the private key into a file
    “priv_key”.
     */
    public static void main(String[] args) throws Exception {
        generateAndSaveKeys();
    }
    //Generate and save keys
    private static void generateAndSaveKeys() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(3072);
        KeyPair keyPair = keyGen.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        savePrivateKeyToFile("priv_key", privateKey);
        savePublicKeyToFile("pub_key", publicKey);
    }

    private static void savePrivateKeyToFile(String key, PrivateKey privKey) {
        //Save private key to file
        try (FileOutputStream fos = new FileOutputStream(key)) {
            fos.write(privKey.getEncoded());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void savePublicKeyToFile(String key, PublicKey publicKey) {
        //Save public key to file
        try (FileOutputStream fos = new FileOutputStream(key)) {
            fos.write(publicKey.getEncoded());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}