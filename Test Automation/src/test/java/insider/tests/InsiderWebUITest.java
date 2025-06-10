package insider.tests;

import insider.core.DriverManager;
import insider.page.CareerPage;
import insider.page.HomePage;
import insider.page.JobLeverPage;
import insider.page.SearchPage;
import insider.tests.utils.TestListener;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.apache.log4j.Logger;

public class InsiderWebUITest {
    @Rule
    public TestListener testListener = new TestListener();

    private static final Logger logger = Logger.getLogger(InsiderWebUITest.class);

    private HomePage homePage;
    private CareerPage careerPage;
    private SearchPage searchPage;
    private JobLeverPage jobLeverPage;

    @BeforeClass
    public static void beforeAll() {
        DriverManager.beforeClass();
        String browser = System.getProperty("browser", "chrome"); // default chrome
        DriverManager.setUp(browser);
    }

    @Test
    public void testCareerJobSearchFlow() {
        homePage = new HomePage();
        careerPage = new CareerPage();
        searchPage = new SearchPage();
        jobLeverPage = new JobLeverPage();

        logger.info("Test başladı: HomePage kontrol ediliyor.");
        homePage.checkHomePage();

        logger.info("Company menüsünden Career sayfasına gidiliyor.");
        homePage.selectCareerDropDownButton();

        logger.info("Career sayfası kontrol ediliyor.");
        careerPage.checkCareerPage();

        logger.info("Quality Assurance iş arama sayfasına gidiliyor.");
        careerPage.gotoSearchJobPage();

        logger.info("İş filtreleri uygulanıyor.");
        searchPage.filterJobDetails();

        logger.info("İş listesi varlığı kontrol ediliyor.");
        searchPage.checkPresenceOfTheJobsList();

        logger.info("İş detayları kontrol ediliyor.");
        searchPage.checkAllJobsDetailContains();

        logger.info("İşin detay sayfasına gidiliyor.");
        searchPage.clickViewRoleBtn();

        logger.info("İş detay sayfası kontrol ediliyor.");
        jobLeverPage.checkJobPage();

        logger.info("Test başarıyla tamamlandı.");
    }

    @AfterClass
    public static void afterAll() {
        DriverManager.tearDown();     // WebDriver kapatılır
    }
}
