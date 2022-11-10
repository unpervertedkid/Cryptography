import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.file.Files;
import java.nio.file.Paths;

/*
Write a program “compute_keyed_checksum” which works similarly to the one in Step 4, but
instead of SHA256, you will use the keyed hash function HMAC-SHA256. A 256-bit key will
be read from the file “key.bin”. The checksum will be written into a file
“sensitive_keyed_checksum.txt”—this file should be in the text format.
 */
public class Main {
    public static void main(String[] args) {
        readCheckSum("sensitive.txt", "sensitive_keyed_checksum.txt");
    }

    private static void readCheckSum(String filename, String outPutFile) {
        try {
            byte[] key = Files.readAllBytes(Paths.get("key.bin"));
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(key, "HmacSHA256"));
            String fileContent = new String(Files.readAllBytes(Paths.get(filename)));
            mac.update(fileContent.getBytes());
            byte[] hash = mac.doFinal();
            String checksum = new String(hash);
            Files.write(Paths.get(outPutFile), checksum.getBytes());
            System.out.println("Checksum of sensitive.txt is: " + checksum);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
