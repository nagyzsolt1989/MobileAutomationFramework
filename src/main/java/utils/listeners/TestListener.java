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
import java.util.Set;

public class TestListener implements ITestListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestListener.class);

    private PropertyReader testrail = new PropertyReader("testrail.properties");

    private static List<ITestNGMethod> failedtests = new ArrayList<ITestNGMethod>();

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
        failedtests.add(result.getMethod());
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

    @Override
    public void onFinish(ITestContext context) {
        Set<ITestResult> failedTests = context.getFailedTests().getAllResults();
        for (ITestResult temp : failedTests) {
            ITestNGMethod method = temp.getMethod();
            if (context.getFailedTests().getResults(method).size() > 1) {
                failedTests.remove(temp);
            } else {
                if (context.getPassedTests().getResults(method).size() > 0) {
                    failedTests.remove(temp);
                }
            }
        }
    }

    public static String getFailedTestsList() {
        String listFailedTests = "";
        if (failedtests.size() > 0) {
            for (ITestNGMethod test : failedtests) {
                listFailedTests = listFailedTests + "\\n\\u2022 " + test.getMethodName();
            }
        } else {
            listFailedTests = "\\n\\u2022 None";
        }
        return listFailedTests;
    }
}
