package com.deinersoft.checkwordster.controller;

interface CucumberClientInterface {
    public void startServer();
    public void stopServer();
    public String getWords(String digits);
}
