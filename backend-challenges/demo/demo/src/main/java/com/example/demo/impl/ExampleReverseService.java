package com.example.demo.impl;

import com.example.demo.dto.ReverseData;
import com.example.demo.services.ReverseService;

public class ExampleReverseService implements ReverseService {
    
    @Override
    public ReverseData reverse(String word) {
        String reverse = "";
        String result;
        Boolean palindrome = true;
        
        for (int i = 0; i < word.length(); i++) {
            if(palindrome && word.charAt(i) != (word.charAt(word.length() - (i+1)))) {
                palindrome = false;
            }
            
            reverse += word.charAt(word.length() - (i+1));
        }

        result = reverse;
        return new ReverseData(word, result, palindrome);
    }
}
