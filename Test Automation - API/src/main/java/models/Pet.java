package models;

import java.util.List;

public class Pet {
    private long id;
    private String name;
    private String status;
    private List<String> photoUrls;

    // Boş constructor
    public Pet() {
    }
    public Pet(long id, String name, String status, List<String> photoUrls) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.photoUrls = photoUrls;
    }

    public Pet(long id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }
}
