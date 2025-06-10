package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Pet;

public class ApiUtils {

    static {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    public static Response createPet(Pet pet) {
        return RestAssured
                .given().log().uri()
                .given().log().body()
                .contentType("application/json")
                .body(pet)
                .when()
                .post("/pet")
                .then().log().body()
                .extract()
                .response();
    }

    public static Response getPetById(long id) {
        return RestAssured
                .given().log().uri()
                .given().log().body()
                .contentType("application/json")
                .when()
                .get("/pet/" + id)
                .then().log().body()
                .extract()
                .response();
    }

    public static Response deletePet(long id) {
        return RestAssured
                .given().log().uri()
                .given().log().body()
                .contentType(ContentType.JSON)
                .when()
                .delete("/pet/" + id)
                .then().log().body()
                .extract()
                .response();
    }

    public static Response updatePet(Pet pet) {
        return RestAssured
                .given().log().uri()
                .given().log().body()
                .contentType("application/json")
                .body(pet)
                .when()
                .put("/pet")
                .then().log().body()
                .extract()
                .response();
    }

    public static Response findPetByStatus(String status) {
        return RestAssured
                .given().log().uri()
                .given().log().body()
                .queryParam("status", status)
                .when()
                .get("/pet/findByStatus")
                .then().log().body()
                .extract()
                .response();
    }

    public static Response updatePetWithFormData(long petId, String name, String status) {
        return RestAssured
                .given().log().uri()
                .given().log().body()
                .contentType("application/x-www-form-urlencoded")
                .formParam("name", name)
                .formParam("status", status)
                .when()
                .post("/pet/" + petId)
                .then().log().body()
                .extract()
                .response();
    }
}