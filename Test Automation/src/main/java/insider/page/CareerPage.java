package insider.page;

import insider.base.BaseMethods;
import org.apache.log4j.Logger;

public class CareerPage extends BaseMethods {

    private static final Logger logger = Logger.getLogger(CareerPage.class);


    public void checkCareerPage() {
        scrollToElementToBeVisible("loadMoreBtn");
        javascriptClick("loadMoreBtn");
        waitBySecond(2);
        checkForElement("teamsSection", "teams section not visible");
        scrollToElementToBeVisible("locationsTitle");
        checkForElement("locationsSection", "location section not visible");
        scrollToElementToBeVisible("lifeatInsiderTitle");
        checkForElement("lifeatInsiderSection", "life at Insider section not visible");
    }
    public void gotoSearchJobPage() {
        gotoURL("https://useinsider.com/careers/quality-assurance/");
        waitForElementToBeVisible("seeAllJobsBtn");
        scrollToElementToBeVisible("seeAllJobsBtn");
        clickElement("seeAllJobsBtn");
        logger.info("Going to Career Open Position screen");
    }

}
