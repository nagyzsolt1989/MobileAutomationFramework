package utils.listeners;

import framework.core.BaseTest;
import org.testng.IExecutionListener;
import utils.PropertyReader;
import utils.slack.SlackUtil;
import java.util.concurrent.TimeUnit;

import static utils.listeners.TestListener.getFailedTestsList;

public class ExecutionListener implements IExecutionListener {

    private PropertyReader slack = new PropertyReader("slack.properties");

    private static long startTime;
    private static long endTime;

    @Override
    public void onExecutionFinish() {
        endTime = System.currentTimeMillis();
        if (Boolean.valueOf(slack.getProperty("slack.enabled"))) {
            SlackUtil.sendSlackNotification(SlackUtil.WEB_HOOK, getTestSummary());
        }
    }

    @Override
    public void onExecutionStart() {
        startTime = System.currentTimeMillis();
    }

    public static String getTestSummary() {
        int passed = TestListener.passedTests.size();
        int failed = TestListener.failedTests.size();
        int skipped = TestListener.skippedTests.size();
        int total = passed + failed + skipped;
        String duration = getExecutionTime(endTime - startTime);

        return SlackUtil.composeMessage(BaseTest.mobilePlatform, BaseTest.deviceName, BaseTest.platformVersion,
                passed, failed, skipped, total, duration, getFailedTestsList());
    }

    private static String getExecutionTime(long millisecond) {

        long min = TimeUnit.MILLISECONDS.toMinutes(millisecond);
        long sec = TimeUnit.MILLISECONDS.toSeconds(millisecond) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisecond));

        return min + " minute(s) " + sec + " seconds";
    }
}