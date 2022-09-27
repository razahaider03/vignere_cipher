package vignere_cipher;

import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        // StringBuilder sb = new StringBuilder(message);
        String mesg = "";
        for(int i = whichSlice; i<message.length(); i = i+totalSlices)
        {
            mesg = mesg + message.charAt(i);
        }
        // System.out.println(mesg);        
        return mesg;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //WRITE YOUR CODE HERE
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for(int i = 0; i<klength; i++)
        {
            String message = sliceString(encrypted, i, klength);
            // System.out.println(cc.getKey(message));
            key[i]= cc.getKey(message);
        }
        // System.out.println(key);
        return key;
    }

    public void breakVigenere () {
        //WRITE YOUR CODE HERE
        FileResource encrypt = new FileResource();
        String message = encrypt.asString();
        int[] keys = tryKeyLength(message, 4, 'e');
        VigenereCipher vg = new VigenereCipher(keys);
        String decrypt = vg.decrypt(message);
        System.out.println(decrypt);
    }
    
}
