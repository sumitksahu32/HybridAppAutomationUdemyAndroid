package StepDefs;

import PageImpl.GSBaseClassImpl;
import PageImpl.GSShoesPageImpl;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.google.inject.Inject;

public class GSStepDef  {

    GSBaseClassImpl coreObj = new GSBaseClassImpl();
    GSShoesPageImpl sh = new GSShoesPageImpl();

    @Given("^User launches General store app$")
    public  void launchApp() throws Throwable
    {
        coreObj.setup();
    }

    @When("^User fills form$")
    public void fillForm() throws Throwable
    {
        sh.FillForm();
    }

    @Then("^User lands to Home Page$")
    public void reachesHomePage() throws Throwable
    {
        sh.reachedHomePage();
    }


    @When("User submits with blank User Name")
    public void userSubmitsWithBlankUserName() {

        sh.blankSubmit();
    }

    @Then("toast error message is displayed")
    public void toastErrorMessageIsDisplayed() {

        sh.verifyErrorMsg();

    }

    @And("User completes the task")
    public void userCompletesTheTask() {
        coreObj.tearDown();
    }

    @And("User selects {string} product and adds to cart")
    public void userSelectsProductAndAddsToCart(String arg0) throws InterruptedException {

        sh.searchAndAddToCart(arg0);
    }

    @And("User verifies total amount is correct")
    public void userVerifiesTotalAmountIsCorrect() throws InterruptedException {

        sh.verifyTotalAmount();
    }

    @And("User navigates to Web View")
    public void userNavigatesToWebView() throws InterruptedException {

        sh.doGestures();
        coreObj.switchContext();
    }
}
