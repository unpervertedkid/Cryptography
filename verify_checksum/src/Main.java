import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;

/*
Write a program “verify_checksum” which works as follows: it computes a checksum of
“sensitive.txt” and compares it with the contents of “sensitive_checksum.txt”.
If the resulting hash values are the same, then the program outputs “Accept!” and otherwise it
outputs “Reject!”.
 */
public class Main {
    public static void main(String[] args) {
        String checksum = readCheckSum("sensitive.txt");
        String checksumFile = readCheckSum("sensitive_checksum.txt");
        if (checksum.equals(checksumFile)) {
            System.out.println("Accept!");
        } else {
            System.out.println("Reject!");
        }
    }
    private static String readCheckSum(String filename) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String fileContent = new String(Files.readAllBytes(Paths.get(filename)));
            digest.update(fileContent.getBytes());
            byte[] hash = digest.digest();
            return new String(hash);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
