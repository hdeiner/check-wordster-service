package com.deinersoft.checkwordster.controller;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(
//      dryRun   = false,
//      strict = true,
//      tags     = "",
//      monochrome = false,
        features = { "src/test/java/com/com/deinersoft/checkwordster/controller/features" },
        glue     = { "test.java.com.capitalone.checkwordster" },
        plugin   = { "pretty", "html:target/cucumber-reports/cucumber-html-report", "json:target/cucumber-reports/cucumber-json-report.json" }
)

public class CucumberTests {

}
