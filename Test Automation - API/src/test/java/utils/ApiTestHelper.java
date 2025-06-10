package utils;

import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.testng.Assert;

import java.util.Arrays;

public class ApiTestHelper {

    private static final Logger logger = Logger.getLogger(ApiTestHelper.class);

    // Tek bir beklenen durum kodu
    public static void assertStatusCode(Response response, int expectedStatus) {
        int actualStatus = response.getStatusCode();

        if (actualStatus != expectedStatus) {
            logger.warn("Beklenen " + expectedStatus + " yerine " + actualStatus + " döndü — API hatası olabilir.");
        }

        Assert.assertEquals(actualStatus, expectedStatus, "Beklenen " + expectedStatus + " durum kodu alınamadı.");
    }

    // Birden fazla beklenen durum kodu
    public static void assertStatusCode(Response response, int... expectedStatuses) {
        int actualStatus = response.getStatusCode();

        if (Arrays.stream(expectedStatuses).noneMatch(status -> status == actualStatus)) {
            logger.warn("Beklenenlerden biri " + Arrays.toString(expectedStatuses) + " yerine "
                    + actualStatus + " döndü — API hatası olabilir.");
        }

        Assert.assertTrue(Arrays.stream(expectedStatuses).anyMatch(status -> status == actualStatus),
                "Beklenen durum kodlarından biri alınmalı: " + Arrays.toString(expectedStatuses));
    }
}
