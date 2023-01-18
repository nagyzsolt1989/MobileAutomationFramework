package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RetryAnalyzer.class);

    private int counter = 0;
    private int retryLimit = 3;

    @Override
    public boolean retry(ITestResult result) {
        if (!result.isSuccess()) {
            if (counter < retryLimit) {
                counter++;
                result.setStatus(ITestResult.FAILURE);
                LOGGER.info("Retrying {} test with status {}. Count of retires: {}",
                        result.getName(), getResultStatusName(result.getStatus()), counter);
                return true;
            } else {
                result.setStatus(ITestResult.FAILURE);
            }
        } else {
            result.setStatus(ITestResult.SUCCESS);
        }
        return false;
    }

    public String getResultStatusName(int status) {
        String resultName = null;
        if(status==1)
            resultName = "SUCCESS";
        if(status==2)
            resultName = "FAILURE";
        if(status==3)
            resultName = "SKIP";
        return resultName;
    }
}
