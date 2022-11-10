import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.file.Files;
import java.nio.file.Paths;

/*
Write a program “verify_keyed_checksum” which works as follows:
it computes a keyed checksum of “sensitive.txt” using HMAC-SHA256 and compares it with
the contents of “sensitive_keyed_checksum.txt”. If the resulting values are the same, then
the program outputs “Accept!” and otherwise it outputs “Reject!”.
 */
public class Main {
    public static void main(String[] args) {
        String checksum = readCheckSum("sensitive.txt");
        String checksumFile = readCheckSum("sensitive_keyed_checksum.txt");
        if (checksum.equals(checksumFile)) {
            System.out.println("Accept!");
        } else {
            System.out.println("Reject!");
        }
    }
    private static String readCheckSum(String filename) {
        try {
            byte[] key = Files.readAllBytes(Paths.get("key.bin"));
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(key, "HmacSHA256"));
            String fileContent = new String(Files.readAllBytes(Paths.get(filename)));
            mac.update(fileContent.getBytes());
            byte[] hash = mac.doFinal();
            return new String(hash);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
