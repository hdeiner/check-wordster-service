package com.deinersoft.checkwordster.controller;

import com.deinersoft.checkwordster.model.CheckWordster;
import com.deinersoft.checkwordster.model.CheckWordsterException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CucumberStepDefs extends CucumberStepDefsSpringConcerns {
    private static Boolean isServerInUse = false;
    private static String serverTypeInUse = "";
    private static CucumberClientInterface checkWordsterCucumberClientInterface;

    private String stringToConvert;

    @Given("^I am not using a server$")
    public void i_am_not_using_a_server() throws Throwable {
        isServerInUse = false;
    }

    @Given("^I am using a fake server$")
    public void i_am_using_a_fake_server() throws Throwable {
        isServerInUse = true;
        serverTypeInUse = "fake";
        checkWordsterCucumberClientInterface = new CucumberClientImplFake();
    }

    @Given("^I am using a local server$")
    public void i_am_using_a_local_server() throws Throwable {
        isServerInUse = true;
        serverTypeInUse = "local";
        checkWordsterCucumberClientInterface = new CucumberClientImplLocal();
    }

    @Then("^I stop the server$")
    public void i_stop_the_server() throws Throwable {
        checkWordsterCucumberClientInterface.stopServer();
    }

    @When("^I convert \"([^\"]*)\" into words$")
    public void i_convert_into_words(String stringToConvert) throws Throwable {
        this.stringToConvert = stringToConvert;
    }

    @Then("^it should be \"([^\"]*)\"$")
    public void it_should_be(String numberInWords) throws Throwable {

        if (!isServerInUse) {
            CheckWordster checkWordster = new CheckWordster(stringToConvert);
            assertThat(checkWordster.getWords(), is(numberInWords));
        }
        else {
            assertThat(checkWordsterCucumberClientInterface.getWords(stringToConvert), is(numberInWords));
        }
    }

    @Then("^an exception \"([^\"]*)\" should be thrown$")
    public void an_exception_should_be_thrown(String exceptedExceptionMessage) {
        try {
            CheckWordster checkWordster = new CheckWordster(this.stringToConvert);
            assertThat("Supposed to throw a \"" + exceptedExceptionMessage + "\" exception", true, is(false));
        } catch (CheckWordsterException e) {
            assertThat(e.getMessage(), is(exceptedExceptionMessage));
        }
    }
}
