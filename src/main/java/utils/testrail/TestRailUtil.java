package utils.testrail;

import com.codepine.api.testrail.TestRail;
import com.codepine.api.testrail.model.ResultField;
import com.codepine.api.testrail.model.Run;
import com.codepine.api.testrail.model.Result;
import org.testng.ITestResult;
import utils.PropertyReader;

import java.util.List;
import java.lang.reflect.Method;

public class TestRailUtil {

    private static PropertyReader config = new PropertyReader("testrail.properties");

    public static final int PROJECT_ID = Integer.parseInt(config.getProperty("testrail.project"));
    public static final String TESTRAIL_USERNAME = config.getProperty("testrail.username");
    public static final String TESTRAIL_PASSWORD = config.getProperty("testrail.password");
    public static final String TESTRAIL_URL = config.getProperty("testrail.url");
    public static final String TEST_RUN = config.getProperty("testrail.run");
    public static int RUN_ID;

    public static final TestRail testRailClient = TestRail
            .builder(TESTRAIL_URL, TESTRAIL_USERNAME, TESTRAIL_PASSWORD)
            .build();

    private static final List<ResultField> RESULT_FIELDS = testRailClient
            .resultFields()
            .list()
            .execute();

    private TestRailUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static Run addTestRun() {
        return testRailClient
                .runs()
                .add(PROJECT_ID, new Run().setName(TEST_RUN))
                .execute();
    }

    public static List<Run> getRunsForProject(int projectId) {
        return testRailClient
                .runs()
                .list(projectId)
                .execute();
    }

    public static int getTestRunID() {
        RUN_ID = getRunsForProject(PROJECT_ID)
                .stream()
                .filter(run -> run.getName().equals(TEST_RUN))
                .map(Run::getId)
                .findFirst()
                .orElseGet(() -> addTestRun().getId());

        return RUN_ID;
    }

    public static void setTestResult(ITestResult context, int testCaseID, int runID) {
        Run runWithAutomatedCasesOnly = testRailClient
                .runs()
                .get(runID)
                .execute();

        testRailClient
                .runs()
                .update(runWithAutomatedCasesOnly)
                .execute();

        testRailClient
                .results()
                .addForCase(testCaseID, getTestRailIDFromMethod(context), new Result()
                        .setStatusId(context.getStatus()), RESULT_FIELDS)
                .execute();
    }

    public static int getTestRailIDFromMethod(ITestResult context) {
        Method[] methods = context.getTestClass().getRealClass().getDeclaredMethods();
        int annotationId = 0;
        for (Method method : methods) {
            if (method.isAnnotationPresent(TestRailID.class)) {
                TestRailID annotation = method.getAnnotation(TestRailID.class);
                annotationId = Integer.parseInt(annotation.value().substring(1));
            }
        }
        return annotationId;
    }
}
