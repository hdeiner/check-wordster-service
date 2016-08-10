package com.deinersoft.checkwordster.controller;

import com.deinersoft.checkwordster.model.CheckWordster;
import com.deinersoft.checkwordster.model.CheckWordsterException;

public class CucumberClientImplNone implements CucumberClientInterface {

    public void startServer(){
    };

    public void stopServer(){
    };

    public String getWords(String digits){
        CheckWordster checkWordster = null;
        try {
            checkWordster = new CheckWordster(digits);
        } catch (CheckWordsterException e) {
            e.printStackTrace();
        }
        return checkWordster.getWords();
    };
}

