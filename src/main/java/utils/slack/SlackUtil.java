package utils.slack;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.PropertyReader;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class SlackUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(SlackUtil.class);

    private static PropertyReader config = new PropertyReader("slack.properties");
    public static String webHook = config.getProperty("slack.webhook");

    public static void sendSlackNotification(String webHook, String message) {

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(webHook);

        try {
            StringEntity entity = new StringEntity(message);
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            client.execute(httpPost);
            client.close();
            LOGGER.info("Slack notification sent");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (ClientProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static String composeMessage(String platform, String device, String os, int passed, int failed, int skipped,
                                        int total, int ignored, int retries, String duration, String failedTests) {
        return new String(
                "{" +
                        "  \"blocks\": [" +
                        "    {" +
                        "      \"type\": \"section\"," +
                        "      \"text\": {" +
                        "        \"type\": \"mrkdwn\"," +
                        "        \"text\": \"*" + platform + " Test Result*\"" +
                        "      }" +
                        "    }," +
                        "    {" +
                        "      \"type\": \"divider\"" +
                        "    }," +
                        "    {" +
                        "      \"type\": \"section\"," +
                        "      \"fields\": [" +
                        "        {" +
                        "          \"type\": \"mrkdwn\"," +
                        "          \"text\": \"*Device:*\\n " + device + "\"" +
                        "        }," +
                        "        {" +
                        "          \"type\": \"mrkdwn\"," +
                        "          \"text\": \"*App version:*\\n 1.12.0\"" +
                        "        }," +
                        "        {" +
                        "          \"type\": \"mrkdwn\"," +
                        "          \"text\": \"*Operating System:*\\n " + os + "\"" +
                        "        }," +
                        "        {" +
                        "          \"type\": \"mrkdwn\"," +
                        "          \"text\": \"*Environment:*\\n Stage\"" +
                        "        }" +
                        "      ]" +
                        "    }," +
                        "    {" +
                        "      \"type\": \"divider\"" +
                        "    }," +
                        "    {" +
                        "      \"type\": \"section\"," +
                        "      \"fields\": [" +
                        "        {" +
                        "          \"type\": \"mrkdwn\"," +
                        "          \"text\": \"*Branch:*\\n Master\"" +
                        "        }," +
                        "        {" +
                        "          \"type\": \"mrkdwn\"," +
                        "          \"text\": \"*Commit:*\\n Fix some things\"" +
                        "        }" +
                        "      ]" +
                        "    }," +
                        "    {" +
                        "      \"type\": \"divider\"" +
                        "    }," +
                        "    {" +
                        "      \"type\": \"section\"," +
                        "      \"fields\": [" +
                        "        {" +
                        "          \"type\": \"mrkdwn\"," +
                        "          \"text\": \"*Execution time:* " + duration + "\"" +
                        "        }" +
                        "      ]" +
                        "    }," +
                        "    {" +
                        "      \"type\": \"divider\"" +
                        "    }," +
                        "    {" +
                        "      \"type\": \"section\"," +
                        "      \"fields\": [" +
                        "        {" +
                        "          \"type\": \"mrkdwn\"," +
                        "          \"text\": \"*Passed:* " + passed + "\"" +
                        "        }," +
                        "        {" +
                        "          \"type\": \"mrkdwn\"," +
                        "          \"text\": \"*Total:* " + total + "\"" +
                        "        }," +
                        "        {" +
                        "          \"type\": \"mrkdwn\"," +
                        "          \"text\": \"*Failed:* " + failed + "\"" +
                        "        }," +
                        "        {" +
                        "          \"type\": \"mrkdwn\"," +
                        "          \"text\": \"*Ignored:* " + ignored + "\"" +
                        "        }," +
                        "        {" +
                        "          \"type\": \"mrkdwn\"," +
                        "          \"text\": \"*Skipped:* " + skipped + "\"" +
                        "        }," +
                        "        {" +
                        "          \"type\": \"mrkdwn\"," +
                        "          \"text\": \"*Retries:* " + retries + "\"" +
                        "        }," +
                        "      ]" +
                        "    }," +
                        "    {" +
                        "      \"type\": \"divider\"" +
                        "    }," +
                        "    {" +
                        "      \"type\": \"section\"," +
                        "      \"text\": {" +
                        "        \"type\": \"mrkdwn\"," +
                        "        \"text\": \"*Failed test cases:*\\n" + failedTests + "\"" +
                        "      }" +
                        "    }" +
                        "  ]" +
                        "}");
    }
}