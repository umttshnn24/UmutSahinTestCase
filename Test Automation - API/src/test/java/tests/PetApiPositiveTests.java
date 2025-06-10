package tests;

import io.restassured.response.Response;
import models.Pet;
import org.testng.annotations.Test;
import utils.ApiTestHelper;
import utils.ApiUtils;

public class PetApiPositiveTests {

    @Test(description = "Yeni pet ekleme başarılı - 200 veya 201 dönmeli")
    public void addPet_Positive_Success() {
        Pet validPet = new Pet(123456, "Buddy", "available");
        Response response = ApiUtils.createPet(validPet);
        ApiTestHelper.assertStatusCode(response, 200, 201);
    }

    @Test(description = "ID ile pet sorgulama başarılı - 200 dönmeli")
    public void getPetById_Positive_Success() {
        Response response = ApiUtils.getPetById(123456);
        ApiTestHelper.assertStatusCode(response, 200);
    }

    @Test(description = "Pet güncelleme başarılı - 200 dönmeli")
    public void updatePet_Positive_Success() {
        Pet validPet = new Pet(123456, "BuddyUpdated", "sold");
        Response response = ApiUtils.updatePet(validPet);
        ApiTestHelper.assertStatusCode(response, 200);
    }

    @Test(description = "Duruma göre pet listeleme başarılı - 200 dönmeli")
    public void findPetByStatus_Positive_Success() {
        Response response = ApiUtils.findPetByStatus("available");
        ApiTestHelper.assertStatusCode(response, 200);
    }

    @Test(description = "Form verisi ile pet güncelleme başarılı - 200 dönmeli")
    public void updatePetFormData_Positive_Success() {
        Response response = ApiUtils.updatePetWithFormData(123456, "NewName", "pending");
        ApiTestHelper.assertStatusCode(response, 200);
    }

    @Test(description = "Pet silme başarılı - 200 veya 204 dönmeli")
    public void deletePet_Positive_Success() {
        Response response = ApiUtils.deletePet(123456);
        ApiTestHelper.assertStatusCode(response, 200, 204);
    }
}
