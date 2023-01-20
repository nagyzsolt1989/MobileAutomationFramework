package utils.listeners;

import framework.core.BaseTest;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import utils.PropertyReader;
import utils.testrail.TestRailUtil;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

public class TestListener implements ITestListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestListener.class);
    public static final String TESTRAIL_ENABLED = "testrail.enabled";

    private PropertyReader testrail = new PropertyReader("testrail.properties");

    public static List<ITestNGMethod> passedTests = new ArrayList<>();
    public static List<ITestNGMethod> failedTests = new ArrayList<>();
    public static List<ITestNGMethod> skippedTests = new ArrayList<>();

    @Override
    public void onTestStart(ITestResult result) {
        LOGGER.info("Test case started: {}", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        passedTests.add(result.getMethod());
        LOGGER.info("Test case passed: {}", result.getName());
        if (Boolean.valueOf(testrail.getProperty(TESTRAIL_ENABLED))) {
            TestRailUtil.setTestResult(result, TestRailUtil.getTestRailIDFromMethod(result),
                    TestRailUtil.getTestRunID());
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        failedTests.add(result.getMethod());
        LOGGER.info("Test case failed: {}", result.getName());
        Allure.attachment("Test failure!",
                new ByteArrayInputStream(((TakesScreenshot) BaseTest.getDriver()).getScreenshotAs(OutputType.BYTES)));
        if (Boolean.valueOf(testrail.getProperty(TESTRAIL_ENABLED))) {
            TestRailUtil.setTestResult(result, TestRailUtil.getTestRailIDFromMethod(result),
                    TestRailUtil.getTestRunID());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        skippedTests.add(result.getMethod());
        LOGGER.info("Test case skipped: {}", result.getName());
        if (Boolean.valueOf(testrail.getProperty(TESTRAIL_ENABLED))) {
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

    @Override
    public void onFinish(ITestContext context) {
    }

    public static String getFailedTestsList() {
        StringBuilder builder = new StringBuilder();
        if (failedTests.size() > 0) {
            for (ITestNGMethod test : failedTests) {
                builder.append("\\n\\u2022 " + test.getMethodName());
            }
        } else {
            builder.append("\\n\\u2022 None");
        }
        return builder.toString();
    }
}
