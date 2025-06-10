package tests;

import io.restassured.response.Response;
import models.Pet;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ApiTestHelper;
import utils.ApiUtils;

public class PetApiNegativeTests {

    @Test(description = "Geçersiz giriş ile pet ekleme - 405 döndürmeli")
    public void addPet_Negative_InvalidInput_405() {
        Pet invalidPet = new Pet(); // boş pet, geçersiz giriş
        Response response = ApiUtils.createPet(invalidPet);
        ApiTestHelper.assertStatusCode(response, 405);
    }

    @Test(description = "Geçersiz ID ile pet güncelleme - 400 döndürmeli")
    public void updatePet_Negative_InvalidId_400() {
        Pet invalidPet = new Pet(-1, "testPet", "available");
        Response response = ApiUtils.updatePet(invalidPet);
        ApiTestHelper.assertStatusCode(response, 400);
    }

    @Test(description = "Mevcut olmayan ID ile pet güncelleme - 404 döndürmeli")
    public void updatePet_Negative_PetNotFound_404() {
        Pet nonExistingPet = new Pet(99999999, "testPet", "available");
        Response response = ApiUtils.updatePet(nonExistingPet);
        ApiTestHelper.assertStatusCode(response, 404);
    }

    @Test(description = "Geçersiz veri ile pet güncelleme - 405 döndürmeli")
    public void updatePet_Negative_ValidationException_405() {
        Pet invalidPet = new Pet(1, "", "available");
        Response response = ApiUtils.updatePet(invalidPet);
        ApiTestHelper.assertStatusCode(response, 405);
    }

    @Test(description = "Geçersiz durum ile pet arama - 400 döndürmeli")
    public void findPetByStatus_Negative_InvalidStatus_400() {
        Response response = ApiUtils.findPetByStatus("invalidStatus");
        ApiTestHelper.assertStatusCode(response, 400);
    }

    @Test(description = "Geçersiz ID ile pet bulma - 400 döndürmeli")
    public void findPetById_Negative_InvalidId_400() {
        Response response = ApiUtils.getPetById(-1);
        ApiTestHelper.assertStatusCode(response, 400);
    }

    @Test(description = "Mevcut olmayan ID ile pet bulma - 404 döndürmeli")
    public void findPetById_Negative_PetNotFound_404() {
        Response response = ApiUtils.getPetById(999999999L);
        int actualStatus = response.getStatusCode();
        Assert.assertEquals(actualStatus, 404, "Beklenen 404 döndü");
    }


    @Test(description = "Form verisi ile geçersiz pet güncelleme - 405 döndürmeli")
    public void updatePetFormData_Negative_InvalidInput_405() {
        Response response = ApiUtils.updatePetWithFormData(1, "", "");
        ApiTestHelper.assertStatusCode(response, 405);
    }

    @Test(description = "Geçersiz ID ile pet silme - 400 döndürmeli")
    public void deletePet_Negative_InvalidId_400() {
        Response response = ApiUtils.deletePet(-1);
        ApiTestHelper.assertStatusCode(response, 400);
    }

    @Test(description = "Mevcut olmayan ID ile pet silme - 404 döndürmeli")
    public void deletePet_Negative_PetNotFound_404() {
        Response response = ApiUtils.deletePet(999999999L);
        ApiTestHelper.assertStatusCode(response, 404);
    }
}
