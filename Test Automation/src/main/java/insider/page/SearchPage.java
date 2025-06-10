package insider.page;

import insider.base.BaseMethods;
import org.apache.log4j.Logger;

public class SearchPage extends BaseMethods {

    private static final Logger logger = Logger.getLogger(SearchPage.class);

    public void filterJobDetails() {
        waitForElementToBeVisible("checkSearchPageisOpen");
        clickElement("locationFilterBox");
        clickElement("selectIstanbulLocation");
        waitBySecond(1);
        clickElement("departmentFilterBox");
        clickElement("selectQualityAssuranceDepartment");

    }

    public void checkPresenceOfTheJobsList() {
        scrollToElementToBeVisible("sectionOfThePositionArea");
        checkForElement("sectionOfThePositionArea", "Browse Open Positions section not visible");
        checkForElement("sectionCarrerListTitle", "Browse Open Positions section not visible");
    }

    public void checkAllJobsDetailContains() {
        waitForElementToBeVisible("jobsPositionTitles");
        checkIsTextContainsList("jobsPositionTitles", "Quality Assurance");
        saveValueLastJob("jobsPositionTitles");
        checkIsTextContainsList("jobsPositionDepartment", "Quality Assurance");
        saveValueLastJob("jobsPositionDepartment");
        checkIsTextContainsList("jobsPositionLocation", "Istanbul, Turkiye");
        saveValueLastJob("jobsPositionLocation");
        waitBySecond(1);
    }

    public void clickViewRoleBtn() {
        hoverOverElementWithSelectedSize("viewRoleButton");
        clickElementWithSelectedSize("viewRoleButton");
        logger.info("Seçilen Job ekranına gidiliyor");
    }
}
