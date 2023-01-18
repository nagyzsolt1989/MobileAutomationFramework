package utils.listeners;

import framework.core.BaseTest;
import org.testng.IExecutionListener;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import utils.slack.SlackUtil;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static utils.listeners.TestListener.getFailedTestsList;

public class ExecutionListener implements IExecutionListener {

    private static String duration;
    private static long startTime;
    private static long endTime;
    private static int total;
    private static int passed;
    private static int failed;
    private static int skipped;
    private static int retries;
    private static int ignored;

    @Override
    public void onExecutionFinish() {
        endTime = System.currentTimeMillis();
        System.out.println(getTestSummary());
        SlackUtil.sendSlackNotification(SlackUtil.webHook, getTestSummary());
    }

    @Override
    public void onExecutionStart() {
        startTime = System.currentTimeMillis();
    }

    public static String getTestSummary() {
        String path = System.getProperty("user.dir") + "/test-output/testng-results.xml";

        File fXmlFile = new File(path);

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            total = Integer.parseInt(doc.getDocumentElement().getAttribute("total"));
            passed = Integer.parseInt(doc.getDocumentElement().getAttribute("passed"));
            failed = Integer.parseInt(doc.getDocumentElement().getAttribute("failed"));
            skipped = Integer.parseInt(doc.getDocumentElement().getAttribute("skipped"));
            ignored = Integer.parseInt(doc.getDocumentElement().getAttribute("ignored"));
            retries = Integer.parseInt(doc.getDocumentElement().getAttribute("retried"));
            duration = getExecutionTime(endTime - startTime);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
        return SlackUtil.composeMessage(BaseTest.mobilePlatform, BaseTest.deviceName, BaseTest.platformVersion,
                passed, failed, skipped, total, ignored, retries, duration, getFailedTestsList());
    }

    private static String getExecutionTime(long millisecond) {

        long min = TimeUnit.MILLISECONDS.toMinutes(millisecond);
        long sec = TimeUnit.MILLISECONDS.toSeconds(millisecond) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisecond));

        String time = min + ":" + sec;
        return time;
    }
}