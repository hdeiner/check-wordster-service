package com.deinersoft.checkwordster.controller;

import com.deinersoft.checkwordster.model.CheckWordster;
import com.deinersoft.checkwordster.model.CheckWordsterException;
import cucumber.api.java.en.When;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CucumberStepDefsCore {
    @When("^the core components convert \"([^\"]*)\" should become \"([^\"]*)\"$")
    public void the_core_components_convert_should_become(String digits, String words) throws Throwable {
        try {
            CheckWordster checkWordster = new CheckWordster(digits);
            assertThat(checkWordster.getWords(), is(words));
        } catch (CheckWordsterException e) {
            e.printStackTrace();
        }
    }

    @When("^the core components convert \"([^\"]*)\" then an excecption of \"([^\"]*)\" should be thrown$")
    public void the_core_components_convert_then_an_excecption_of_should_be_thrown(String digits, String exception) throws Throwable {
        try {
            CheckWordster checkWordster = new CheckWordster(digits);
            assertThat("Supposed to throw a \"" + exception + "\" exception", true, is(false));
        } catch (CheckWordsterException e) {
            assertThat(e.getMessage(), is(exception));
        }
    }
}
