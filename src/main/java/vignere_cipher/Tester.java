package vignere_cipher;

import edu.duke.FileResource;

public class Tester {
    public static void main(String[] args) {
        Tester obj = new Tester();
        // obj.testCaeser();
        obj.testCaesarCracker();
    }

    public void testCaeser() {
        FileResource fr = new FileResource("VigenereTestData\\titus-small.txt");
        String message = fr.asString();
        CaesarCipher obj = new CaesarCipher(5);
        String encrypt = obj.encrypt(message);
            System.out.println(obj.encrypt(encrypt));
            System.out.println(obj.decrypt(encrypt));
    }

    public void testCaesarCracker() {
        FileResource fr = new FileResource("VigenereTestData\\oslusiadas_key17.txt");
        String message = fr.asString();
        CaesarCracker obj = new CaesarCracker('a');
        System.out.println(obj.decrypt(message));

    }
}
