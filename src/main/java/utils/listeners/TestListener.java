package utils.listeners;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;

import java.io.ByteArrayInputStream;

import org.testng.ITestListener;
import org.slf4j.LoggerFactory;
import framework.core.BaseTest;
import io.qameta.allure.Allure;
import org.testng.ITestResult;
import org.slf4j.Logger;
import utils.PropertyReader;
import utils.testrail.TestRailUtil;

public class TestListener implements ITestListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestListener.class);
    private PropertyReader testrail = new PropertyReader("testrail.properties");

    @Override
    public void onTestStart(ITestResult result) {
        LOGGER.info("Test case started: {}", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LOGGER.info("Test case passed: {}", result.getName());
        if (Boolean.valueOf(testrail.getProperty("testrail.enabled"))) {
            TestRailUtil.setTestResult(result, TestRailUtil.getTestRailIDFromMethod(result),
                    TestRailUtil.getTestRunID());
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LOGGER.info("Test case failed: {}", result.getName());
        Allure.attachment("Test failure!",
                new ByteArrayInputStream(((TakesScreenshot) BaseTest.getDriver()).getScreenshotAs(OutputType.BYTES)));
        if (Boolean.valueOf(testrail.getProperty("testrail.enabled"))) {
            TestRailUtil.setTestResult(result, TestRailUtil.getTestRailIDFromMethod(result),
                    TestRailUtil.getTestRunID());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LOGGER.info("Test case skipped: {}", result.getName());
        if (Boolean.valueOf(testrail.getProperty("testrail.enabled"))) {
            TestRailUtil.setTestResult(result, TestRailUtil.getTestRailIDFromMethod(result),
                    TestRailUtil.getTestRunID());
        }
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
