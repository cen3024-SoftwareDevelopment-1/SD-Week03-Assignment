package com.musersoft.v1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Text_Analyzer_App {

	public static void main(String[] args) throws IOException {

        // File path is passed as parameter
        File file = new File("C:\\JavaProjects\\SD-Week03-Assignment\\tragedy_of_macbeth.txt");

        // Creating an object of BufferedReader class
        BufferedReader br = new BufferedReader(new FileReader(file));
 
        Map<String, Integer> tm = new TreeMap<>();

        // Declaring a string variable
        String sentence;
        
        // Condition holds true untill there is null character in string
        while ((sentence = br.readLine()) != null) {
 
            if (!(sentence == null || sentence.isEmpty())) {
            	
            	// User RegEx to split up sentence into words
                String[] words = sentence.split("[\\s\\n\\t\\r\\f,;:.!--&]+");
            	
            	// Print the sentence, one word at a time
                for(String word : words){
                	if (!word.isEmpty()) {
                		// System.out.println(word);
                		
                        // Build a map with word and word count as the key/value pairs
                        // Increment the count if word in already in list
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
                }
            }
        }

        // tm.forEach((k, v) -> System.out.println(k + "\t" + v));
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(tm.entrySet());
        Collections.sort(entries, (entry1, entry2) -> {
        	return entry2.getValue().compareTo(entry1.getValue());
        });
        for (Map.Entry<String, Integer> entry: entries) {
        	System.out.printf("%-11s %s%n", entry.getKey(), entry.getValue());
        }
        
	}

}
