package insider.page;

import insider.base.BaseMethods;
import org.apache.log4j.Logger;

public class HomePage extends BaseMethods {

    private static final Logger logger = Logger.getLogger(HomePage.class);

    public void checkHomePage() {
        waitForElementToBeVisible("insiderLogo");
        clickElement("AcceptCookies");
        checkForElement("insiderLogo", "insider logo not visible, Homepage not open");
        checkForElement("exploreInsiderTitle", "explore Insider Title not visible, Homepage not open");
        checkForElement("companyDropdownBtn", "company menu button not visible.");
        logger.info("Homepage successfully loaded and checked.");
    }

    public void selectCareerDropDownButton() {
        clickElement("companyDropdownBtn");
        checkForElement("careerDropdownBtn", "Career dropdown button not visible.");
        clickElement("careerDropdownBtn");
        logger.info("Navigated to Career page from dropdown.");
    }
}
