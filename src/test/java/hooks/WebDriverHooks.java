package hooks;

import com.zitro.common.GeneralUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.BasePage;
import utils.SessionDriverManager;

public class WebDriverHooks extends BasePage {

    private final BasePage basePage;
    GeneralUtils generalUtils;

    public WebDriverHooks(BasePage basePage) {
        this.basePage = basePage;
        this.generalUtils = new GeneralUtils();
    }

    @Before
    public void setUp(Scenario scn) {
        basePage.driver = new SessionDriverManager().getDriver();
        basePage.scn = scn;
        System.out.println("Scenario object : " + scn);
        System.out.println("driver object initialized : " + basePage.driver);
    }

    @After
    public void tearDown() {
        try {
            generalUtils.takeScreenSnap("tearDown", basePage.scn, basePage.driver);
            basePage.driver.quit();
        } catch (NullPointerException e) {
            System.out.println("driver is already null...!");
            //do nothing
        }
    }
}