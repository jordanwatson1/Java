/** 
 * Author: Jordan Watson
 * Date: 01/16/2018
 * Filename:VigenereCipher.java
 */

/** 
 * The VigenereCipher class is a Cipher that uses a simplified 
 * polyalphabetic substitution cipher. In this version, 
 * the following assumptions are true:
 *      plaintext is limited to lower-case ASCII-alphabet letters only 
 *          (i.e., no numbers, no whitespace, no punctuation).
 *      the key is also limited to the same restrictions as the plaintext.
 *      the number of characters in the key is at least 1 and has no maximum limit.
 */
import java.io.*;
public class VigenereCipher extends Object implements Cipher{
    private String key;

    /** 
     * Creates a CigenereCipher object.
     * Preconditions: The key must contain only lower-case ASCII alphabet letters.
     * No error-checking or handling is done to verify that the input is correct.
     * Postconditions: Takes in a String key and creates an object.
     * @param key The key to be used to encrypt and decrypt. 
     */
    public VigenereCipher(String key){
        this.key = key;
    }

    /** 
     * Prints out the specified text, followed immediately by the (comma-delimited) 
     * contents of the array.
     * @param array The array of integers that is printed.
     * @param text This is printed at the start of the line.
     */
    private void dumpArray(int[] array, String text){
        
        System.out.print(text);
        System.out.print(array[0]);
        for(int i = 1; i < array.length; i++){
            System.out.print("," + array[i]);
        }
    }

    /**
     * Converts a string into an int array where the values are within the range 0-25.
     * The values are matched, in order, to the characters in the string.
     * For example, the integer 0 is matched to the letter 'a' and 25 is matched to 'z'.
     * @param text A plain text string consisting of only lower-case ASCII letters.
     * @return An array that is the same length as the text string, and contains the 
     * associated value, in order, of each of the letters in the input string.
     */
    private int[] stringToIntArray(String text){
        
        int[] intArray = new int[text.length()];
        int letterToNum = 0;

        for(int i = 0; i < text.length(); i++){
            char charLetter = text.charAt(i);
            switch (charLetter){
                case 'a': letterToNum = 0;
                        break;
                case 'b': letterToNum = 1;
                        break;
                case 'c': letterToNum = 2;
                        break;
                case 'd': letterToNum = 3;
                        break;
                case 'e': letterToNum = 4;
                        break;
                case 'f': letterToNum = 5;
                        break;
                case 'g': letterToNum = 6;
                        break;
                case 'h': letterToNum = 7;
                        break;
                case 'i': letterToNum = 8;
                        break;
                case 'j': letterToNum = 9;
                        break;
                case 'k': letterToNum = 10;
                        break;
                case 'l': letterToNum = 11;
                        break;
                case 'm': letterToNum = 12;
                        break;
                case 'n': letterToNum = 13;
                        break;
                case 'o': letterToNum = 14;
                        break;
                case 'p': letterToNum = 15;
                        break;
                case 'q': letterToNum = 16;
                        break;
                case 'r': letterToNum = 17;
                        break;
                case 's': letterToNum = 18;
                        break;
                case 't': letterToNum = 19;
                        break;
                case 'u': letterToNum = 20;
                        break;
                case 'v': letterToNum = 21;
                        break;
                case 'w': letterToNum = 22;
                        break;
                case 'x': letterToNum = 23;
                        break;
                case 'y': letterToNum = 24;
                        break;
                case 'z': letterToNum = 25;
                        break;
            }
            intArray[i] = letterToNum;
        }

        return intArray;
    }

    /**
     * Converts an array of integers with values in the range 0-25 into a string with 
     * characters in the range a-z. The individual letters are ordered exactly as the 
     * corresponding integer values. For example, the value 0 in index position i of 
     * the array, matches an 'a' as the first letter in the string.
     * @param encodedText An array of integers with values between 0 and 25.
     * @return A simple string of lower-case letters (no spaces, no punctuation) where 
     * the letter in index position i of the string corresponds to the integer value
     * at index position i in the array.
     */
    private String intArrayToString(int[] encodedText){
        
        char arrToString = 0;
        char[] charToString = new char[encodedText.length];
        
        for(int i = 0; i < encodedText.length; i++){
            int intValue = encodedText[i];
            switch (intValue){
                case 0: arrToString = 'a';
                        break;
                case 1: arrToString = 'b';
                        break;
                case 2: arrToString = 'c';
                        break;
                case 3: arrToString = 'd';
                        break;
                case 4: arrToString = 'e';
                        break;
                case 5: arrToString = 'f';
                        break;
                case 6: arrToString = 'g';
                        break;
                case 7: arrToString = 'h';
                        break;
                case 8: arrToString = 'i';
                        break;
                case 9: arrToString = 'j';
                        break;
                case 10: arrToString = 'k';
                        break;
                case 11: arrToString = 'l';
                        break;
                case 12: arrToString = 'm';
                        break;
                case 13: arrToString = 'n';
                        break;
                case 14: arrToString = 'o';
                        break;
                case 15: arrToString = 'p';
                        break;
                case 16: arrToString = 'q';
                        break;
                case 17: arrToString = 'r';
                        break;
                case 18: arrToString = 's';
                        break;
                case 19: arrToString = 't';
                        break;
                case 20: arrToString = 'u';
                        break;
                case 21: arrToString = 'v';
                        break;
                case 22: arrToString = 'w';
                        break;
                case 23: arrToString = 'x';
                        break;
                case 24: arrToString = 'y';
                        break;
                case 25: arrToString = 'z';
                        break;
            }
            charToString[i] = arrToString;
        }

        String plainText = new String(charToString);
        return plainText;
    }

    /**
     * Encrypts a string using a simplified Vigenere cipher. All text is limited to 
     * lower-case ASCII letters a-z.
     * Specified by: encrypt in interface Cipher.
     * @param plaintext The text to be encrypted.
     * @return the ciphertext.
     */
     public String encrypt(String plaintext){
        
        String encryptedString;
        /********* do we have to create object within class? */
        VigenereCipher encrypter = new VigenereCipher(key);
        
        // Taking one copy of the key and putting it into an array of its
        // corresponding numbers.
        int[] keyNum = new int[key.length()];
        keyNum = encrypter.stringToIntArray(key);

        // Putting the plaintext into an array of corresponding numbers.
        int[] plaintextNum = new int[plaintext.length()];
        plaintextNum = encrypter.stringToIntArray(plaintext);

        // Creates a new array that will take the key and repeat its 
        // corresponding numbers to match the length of the plaintext.
        int[] newKeyLengthNum = new int[plaintextNum.length];
        int position = 0;
        while(position < plaintextNum.length){
                for(int i = 0; i < keyNum.length; i++){
                        if(position < plaintextNum.length){
                                newKeyLengthNum[position] = keyNum[i];
                                position++;
                        }
                }
        }

        // For loop take the newKeyLengthNum and the plaintextNum and 
        // does the vigenere cipher calculation to calculate numbers to 
        // corresponding encrypted letters.
        int[] encryptedNums = new int[plaintextNum.length];
        for(int i = 0; i < plaintextNum.length; i++){
                encryptedNums[i] = ( plaintextNum[i] + newKeyLengthNum[i] ) % 26;
        }

        return encryptedString = encrypter.intArrayToString(encryptedNums);
    }

    /**
     * Decrypts a string using a modified Vigenere cipher. All text is limited to 
     * lower-case ASCII letters a-z.
     * Specified by: decrypt in interface Cipher.
     * @param ciphertext The previously encrypted text.
     * @return the plain text, decrypted.
     */
    public String decrypt(String ciphertext){
        
        String decryptedString;

        VigenereCipher decrypter = new VigenereCipher(key);

        // Taking one copy of the key and putting it into an array of its
        // corresponding numbers.
        int[] keyNums = new int[key.length()];
        keyNums = decrypter.stringToIntArray(key);

        // Putting the ciphertext into an array of corresponding numbers.
        int[] ciphertextNums = new int[ciphertext.length()];
        ciphertextNums = decrypter.stringToIntArray(ciphertext);

        // Creates a new array that will take the key and repeat its 
        // corresponding numbers to match the length of the ciphertext.
        int[] newKeyLengthNums = new int[ciphertextNums.length];
        int position = 0;
        while(position < ciphertextNums.length){
                for(int i = 0; i < keyNums.length; i++){
                        if(position < ciphertextNums.length){
                                newKeyLengthNums[position] = keyNums[i];
                                position++;
                        }
                }
        }

        int[] decryptedNums = new int[ciphertextNums.length];
        for(int i = 0; i < ciphertextNums.length; i++){
                decryptedNums[i] = ( 26 + ciphertextNums[i] - newKeyLengthNums[i]) % 26;
        }

        return decryptedString = decrypter.intArrayToString(decryptedNums);
    }

    /**
     * Sets the key for a simplified Vigenere cipher. All text is limited to 
     * lower-case ASCII letters a-z.
     * Specified by: setKey in interface Cipher
     * @param key A plain text key.
     */
    public void setKey(String key){
        this.key = key;
    }

    /**
     * Used for internal testing purposes only.
     * @param args not used.
     */
    public static void main(String[] args){
        
        VigenereCipher vc = new VigenereCipher("dd");
        System.out.println("converting 'uvic' to an array of ints");
        int[] toNums = vc.stringToIntArray("uvic");
        vc.dumpArray(toNums, "result:");
        System.out.println();

        VigenereCipher vc1 = new VigenereCipher("doo");
        System.out.println("converting nums to the string 'blog'");
        int[] nums = {1,11,14,6};
        System.out.println(vc1.intArrayToString(nums));

        VigenereCipher vc2 = new VigenereCipher("bob");
        System.out.println("encrypting \"themessage\"");
        System.out.println(vc2.encrypt("themessage"));

        VigenereCipher vc3 = new VigenereCipher("bob");
        System.out.println("decrypting \"uvfnsttohf\"");
        System.out.println(vc3.decrypt("uvfnsttohf"));
    
    }
}

    

    
