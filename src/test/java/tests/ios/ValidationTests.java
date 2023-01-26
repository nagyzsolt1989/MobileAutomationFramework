package tests.ios;

import framework.core.BaseTest;
import framework.platform.IOSBaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ValidationPage;
import utils.testrail.TestRailID;

@Epic("Validation")
@Feature("Data Validation")
public class ValidationTests extends IOSBaseTest {

    @Test
    @TestRailID("C3")
    @Description("Verify the layout of the Validation page")
    public void verifyValidationPage() {
        WebDriver driver = BaseTest.getDriver();

        ValidationPage validationPage = new ValidationPage(driver);
        HomePage homePage = new HomePage(driver);

        homePage.clickOnValidationButton();

        Assert.assertTrue(validationPage.isStoryGeneratorLabelDisplayed(),
                "Story Generator label field was not visible");
        Assert.assertTrue(validationPage.isAdjectiveInputFieldDisplayed(),
                "Adjective input field was not visible");
        Assert.assertTrue(validationPage.isNounInputFieldDisplayed(),
                "Noun input field was not visible");
        Assert.assertTrue(validationPage.isTermsOfServiceCheckboxDisplayed(),
                "Terms of Service checkbox was not visible");
        Assert.assertTrue(validationPage.isSubmitButtonDisplayed(),
                "Submit button was not visible");
    }

    @Test
    @TestRailID("C4")
    @Description("Verify that the user could not add an empty Story")
    public void emptyStory() {
        WebDriver driver = BaseTest.getDriver();

        ValidationPage validationPage = new ValidationPage(driver);
        HomePage homePage = new HomePage(driver);

        homePage.clickOnValidationButton();
        validationPage.clickOnSubmitButton();

        Assert.assertTrue(validationPage.isAdjectiveWarningDisplayed(),
                "Please enter an adjective warning was not visible");
        Assert.assertTrue(validationPage.isNounWarningDisplayed(),
                "Please enter a noun was not visible");
        Assert.assertTrue(validationPage.isTermsOfServiceWarningDisplayed(),
                "You must agree to the terms of service warning was not visible");
    }

    @Test
    @TestRailID("C5")
    @Description("Verify that the user could add a Story")
    public void goodStory() {
        WebDriver driver = BaseTest.getDriver();

        ValidationPage validationPage = new ValidationPage(driver);
        HomePage homePage = new HomePage(driver);

        homePage.clickOnValidationButton();
        validationPage.fillAdjectiveField("new");
        validationPage.fillNounField("cat");
        validationPage.acceptTermsOfService();
        validationPage.clickOnSubmitButton();

        Assert.assertTrue(validationPage.isYourStoryLabelDisplayed(), "Your Story was not visible");
        Assert.assertTrue(validationPage.isYourStoryTextDisplayed(), "Your Story text was not visible");
        Assert.assertTrue(validationPage.isDoneButtonDisplayed(), "Done button was not visible");
    }
}
