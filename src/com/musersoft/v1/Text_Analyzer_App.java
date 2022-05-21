package com.musersoft.v1;
/**
* Program Name: Lee_Text_Analyzer_App
* Purpose: Analyze text of Macbeth play. Display word frequency in descending order.
* @author James Lee
* @since 5/29/2022
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Text_Analyzer_App {

	public static void main(String[] args) throws IOException {

		// Declare the desired lines of output
		final int LINES_OF_OUTPUT = 20;
		
        // File path is passed as parameter
        File file = new File("C:\\JavaProjects\\SD-Week03-Assignment\\tragedy_of_macbeth.txt");

        // Creating an object of BufferedReader class
        BufferedReader br = new BufferedReader(new FileReader(file));
 
        // Declare TreeMap collection for words in play (key) and number of occurrences for each (value)
        Map<String, Integer> tm = new TreeMap<>();

        // Declare a string variable to hold one line from file
        String sentence;
        
        // Condition holds true until there is null character in string
        while ((sentence = br.readLine()) != null) {
 
            if (!(sentence == null || sentence.isEmpty())) {
            	
            	// User RegEx to split up sentence into words
                //String[] words = sentence.split("[\\s+\\p{P}]");
                String[] words = sentence.split("[\\s\\n\\t\\r\\f,;:.!--&|]+");
            	
            	// Print the sentence, one word at a time
                for(String word : words){
                	if (goodWord(word)) {
                		
                        // Add word to tree map with value initialized to 1 (key/value pairs)
                        // ... or Increment the word count if word is already in set
                		String key = word.toLowerCase();
                		if (key.length() > 0) {
                			if (!tm.containsKey(key)) {
                				tm.put(key, 1);
                			}
                			else {
                				int value = tm.get(key);
                				value++;
                				tm.put(key, value);
                			}
                		}
                	}
                } //end for loop
            }
        } //end While loop

        // Sort the tree map by word count in descending order
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(tm.entrySet());
        Collections.sort(entries, (entry1, entry2) -> {
        	return entry2.getValue().compareTo(entry1.getValue());
        });
        
        // Display the desired LINES_OF_OUTPUT
        int i = 0; 
        for (Map.Entry<String, Integer> entry: entries) {
        	if (i++ < LINES_OF_OUTPUT)
        		System.out.printf("%-11s %s%n", entry.getKey(), entry.getValue());
        }
        
        br.close();
        
	} //End main()
	
	
	// Utility function for flagging non-words
	public static boolean goodWord(String str) 
	{
		boolean goodWord = true;
		
		// Populate an array of non-words
        String[] nonWords = {
        	"s", "d", "o", "t", "n", "c", "i", "ii", "iii", "iv", "v", "vi", "vii", "viii"
        };
        
        // Check for empty string
        if (str.isEmpty()) 
    		goodWord = false;
        
        // Compare input against array of non-words
        for(int i = 0; i < nonWords.length; i++) {
        	if (str.equals(nonWords[i]))
        		goodWord = false;
        }
                
	    return goodWord; 
	    
	}

} //End class Text_Analyzer_App
