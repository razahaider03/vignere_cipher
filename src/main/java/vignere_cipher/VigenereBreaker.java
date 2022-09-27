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
        CaesarCracker cc = new CaesarCracker();
        
        for (int i = 0; i<klength; i++) {
            key[i]= cc.getKey(encrypted);
        }
        int key1 = cc.getKey(encrypted);
        return key;
    }

    public void breakVigenere () {
        //WRITE YOUR CODE HERE
    }
    
}
