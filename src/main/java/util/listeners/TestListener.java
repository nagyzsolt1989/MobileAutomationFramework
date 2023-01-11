package util.listeners;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;

import java.io.ByteArrayInputStream;

import org.testng.ITestListener;
import org.slf4j.LoggerFactory;
import framework.core.BaseTest;
import io.qameta.allure.Allure;
import org.testng.ITestResult;
import org.slf4j.Logger;

public class TestListener implements ITestListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        LOGGER.info("Test case started: {}", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LOGGER.info("Test case passed: {}", result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LOGGER.info("Test case failed: {}", result.getName());
        Allure.attachment("Test failure!",
                new ByteArrayInputStream(((TakesScreenshot) BaseTest.getDriver()).getScreenshotAs(OutputType.BYTES)));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LOGGER.info("Test case skipped: {}", result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }
}
