/*
Write a program “compute_checksum” which computes a keyless cryptographic checksum of
a file “sensitive.txt”. You will use a hash function SHA256 compute it.
The checksum will be written into a file “sensitive_checksum.txt”—this file should be in
the text format.
Copy the original (unencrypted) file “sensitive.txt” into the directory “hw3”.
In the terminal window, run the program “compute_checksum”. Display the checksum:
cat sensitive_checksum.txt
 */

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;

public class Main {
    public static void main(String[] args) {
        readCheckSum("sensitive.txt","sensitive_checksum.txt");
    }
    private static void readCheckSum(String filename, String outPutFile) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String fileContent = new String(Files.readAllBytes(Paths.get(filename)));
            digest.update(fileContent.getBytes());
            byte[] hash = digest.digest();
            String checksum = new String(hash);
            Files.write(Paths.get(outPutFile), checksum.getBytes());
            System.out.println("Checksum of sensitive.txt is: " + checksum);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
