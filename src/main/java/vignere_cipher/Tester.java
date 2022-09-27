package vignere_cipher;

import edu.duke.FileResource;

public class Tester {
    public static void main(String[] args) {
        Tester obj = new Tester();
        // obj.testCaeser();
        // obj.testCaesarCracker();
        // obj.testVignereCipher();
        // VigenereBreaker obj1 = new VigenereBreaker();
        // obj1.sliceString("abcdefghijklm", 2, 5);
        // obj.testTryKeyLength();
        VigenereBreaker obj1 = new VigenereBreaker();
        obj1.breakVigenere();
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

    public void testVignereCipher() {
        FileResource fr = new FileResource("VigenereTestData\\titus-small.txt");
        String message = fr.asString();
        int[] key = {17, 14, 12, 4};
        VigenereCipher obj = new VigenereCipher(key);
        System.out.println(obj.encrypt(message));

    }

    public void testTryKeyLength() {
        FileResource fr = new FileResource("VigenereTestData\\secretmessage1.txt");
        String message = fr.asString();
        // int[] key = {17, 14, 12, 4};
        VigenereBreaker obj = new VigenereBreaker();
        int[] key = obj.tryKeyLength(message,4,'e');
        for (int i : key) {
            System.out.println(i);
        }        
    }

}
