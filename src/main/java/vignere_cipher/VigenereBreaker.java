package vignere_cipher;

import java.io.File;
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
        HashMap<String, HashSet<String>> langDict = new HashMap<>();

        DirectoryResource dr = new DirectoryResource();
        for (File file : dr.selectedFiles()) {
            FileResource readDict = new FileResource(file);
            String dictName = file.getName();
            HashSet<String> dictSet =  readDictionary(readDict);
            langDict.put(dictName, dictSet);
        }

        breakForAllLangs(message, langDict);
        
        
        
    }

    /**
     * write the public method readDictionary, which has one parameter—a FileResource fr.
     * This method should first make a new HashSet of Strings, then read each line in fr (which should
     * contain exactly one word per line), convert that line to lowercase, and put that line into the
     * HashSet that you created. The method should then return the HashSet representing
     * the words in a dictionary. All the dictionary files, including the English dictionary file,
     * are included in the starter program you download. They are inside the folder called ‘dictionaries’.
     * @param fr
     * @return
     */

    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> dict = new HashSet<>();
        for (String line : fr.lines()) {
            dict.add(line.toLowerCase());
        }
        return dict;
    }

    /**
     * write the public method countWords, which has two parameters—a String message, and a 
     * HashSet of Strings dictionary. This method should split the message into words (use .split(“\\W+”),
     * which returns a String array), iterate over those words, and see how many of them are “real
     * words”—that is, how many appear in the dictionary. Recall that the words in dictionary
     * are lowercase. This method should return the integer count of how many valid words it found.
     * @param message
     * @param dictionary
     * @return
     */

    public int countWords(String message, HashSet<String> dictionary) {
        int maxCount = 0;
        for (String string : message.split("\\W")) {
            if(dictionary.contains(string.toLowerCase()))
            {
                maxCount++;
            }
        }
        
        return maxCount;
    }

    /**
     * write the public method breakForLanguage, which has two parameters—a String
     * encrypted, and a HashSet of Strings dictionary. This method should try
     * all key lengths from 1 to 100 (use your tryKeyLength method to try one particular key
     * length) to obtain the best decryption for each key length in that range.
     * For each key length, your method should decrypt the message (using VigenereCipher’s decrypt 
     * method as before), and count how many of the “words” in it are real words
     * in English, based on the dictionary passed in (use the countWords method you just wrote). 
     * This method should figure out which decryption gives the largest count of real words,
     * and return that String decryption.
     * @param encrypted
     * @param dictionary
     */

    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int maxValid = 0;
        String finaldecrypted = "";
        int countWords = 0;
        int [] finalKey = null;
        for(int i = 1; i<=100;i++)
        {
            int [] key = tryKeyLength(encrypted, i, mostCommonCharIn(dictionary));
            VigenereCipher vg = new VigenereCipher(key);
            String decrypt = vg.decrypt(encrypted);
            int validWords = countWords(decrypt, dictionary);
            if(validWords>maxValid)
            {
                maxValid = validWords;
                finaldecrypted = decrypt;
                countWords = countWords(decrypt, dictionary);
                finalKey = tryKeyLength(encrypted, i, 'e');
            }
        }

            // int [] key = tryKeyLength(encrypted, 38, 'e');
            // VigenereCipher vg = new VigenereCipher(key);
            // String decrypt = vg.decrypt(encrypted);
            // int validWords = countWords(decrypt, dictionary);

        // System.out.println(finaldecrypted);
        // System.out.println(Arrays.toString(finalKey));
        // System.out.println(finalKey.length);
        // System.out.println(countWords);
        return finaldecrypted;
        
    }

    /**
     * write the public method mostCommonCharIn, which has one parameter—a HashSet of Strings dictionary.
     * This method should find out which character, of the letters in the English alphabet,
     * appears most often in the words in dictionary. It should return this most commonly
     * occurring character. Remember that you can iterate over a HashSet of Strings with a for-each style for loop.
     * @param dictionary
     * @return
     */

    public char mostCommonCharIn(HashSet<String> dictionary) {
        char ch = 0;
        int maxCount = 0;
        HashMap<Character,Integer> mostCommonChar = new HashMap<>();
        for (String line : dictionary) {
            for(int i = 0; i<line.length(); i++)
            {
                char chara = Character.toLowerCase(line.charAt(i));
                
                if (!mostCommonChar.containsKey(chara)) {
                    mostCommonChar.put(chara, 1);
                }
                else{
                    int count = mostCommonChar.get(chara);
                    mostCommonChar.put(chara, count+1);
                }
            }
            
        }

        for (char x : mostCommonChar.keySet()) {
            if (mostCommonChar.get(x)>maxCount) {
                ch = x;
                maxCount = mostCommonChar.get(x);
            }
        }

        // System.out.println(ch);
        // System.out.println(mostCommonChar);
        return ch;
    }

    public void breakForAllLangs(String encrypted , HashMap<String, HashSet<String>> languages) {
        HashSet<String> realDict = new HashSet<>();
        int maxValidWord = 0;
        for (String dict   : languages.keySet()) {
            String decrypted = breakForLanguage(encrypted, languages.get(dict));
            int validWord = countWords(decrypted, languages.get(dict));
            if (validWord>maxValidWord) {
                maxValidWord = validWord;
                realDict = languages.get(dict);
            }
        }

        System.out.println(breakForLanguage(encrypted, realDict));
    }



    
}
