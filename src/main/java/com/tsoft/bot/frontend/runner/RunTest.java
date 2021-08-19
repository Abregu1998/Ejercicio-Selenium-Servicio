package com.tsoft.bot.frontend.runner;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;
import org.testng.annotations.Test;

@RunWith(Cucumber.class)
@CucumberOptions(
		features={"src/main/resources/features"},
		glue={"seleniumgluecode",
				"com.tsoft.bot.frontend.steps",
				"com.tsoft.bot.frontend.helpers"
		},
		//plugin = {"pretty", "html:target/cucumber"},
		plugin = {"com.vimalselvam.cucumber.listener.ExtentCucumberFormatter:report/cucumber-report/report.html"},
		tags = {"@Servicios"},
		strict =  false,
		monochrome = true
)

@Test
public class RunTest extends AbstractTestNGCucumberTests{ }

