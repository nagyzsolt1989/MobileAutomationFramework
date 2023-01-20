package utils.listeners;

import framework.core.BaseTest;
import org.testng.IExecutionListener;
import utils.PropertyReader;
import utils.slack.SlackUtil;
import java.util.concurrent.TimeUnit;

import static utils.listeners.TestListener.getFailedTestsList;

public class ExecutionListener implements IExecutionListener {

    private PropertyReader slack = new PropertyReader("slack.properties");

    private static String duration;
    private static long startTime;
    private static long endTime;
    private static int total;
    private static int passed;
    private static int failed;
    private static int skipped;

    @Override
    public void onExecutionFinish() {
        endTime = System.currentTimeMillis();
        if (Boolean.valueOf(slack.getProperty("slack.enabled"))) {
            System.out.println(getTestSummary());
            SlackUtil.sendSlackNotification(SlackUtil.webHook, getTestSummary());
        }
    }

    @Override
    public void onExecutionStart() {
        startTime = System.currentTimeMillis();
    }

    public static String getTestSummary() {
        passed = TestListener.passedTests.size();
        failed = TestListener.failedTests.size();
        skipped = TestListener.skippedTests.size();
        total = passed + failed + skipped;
        duration = getExecutionTime(endTime - startTime);

        return SlackUtil.composeMessage(BaseTest.mobilePlatform, BaseTest.deviceName, BaseTest.platformVersion,
                passed, failed, skipped, total, duration, getFailedTestsList());
    }

    private static String getExecutionTime(long millisecond) {

        long min = TimeUnit.MILLISECONDS.toMinutes(millisecond);
        long sec = TimeUnit.MILLISECONDS.toSeconds(millisecond) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisecond));

        String time = min + " minute(s) " + sec + " seconds";
        return time;
    }
}