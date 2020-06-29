package ru.opening.rest.businessObjects;

public class NewUserAnswer extends NewUser {
    private String id;
    private String createdTime;

    public NewUserAnswer (String name, String job, String id, String createdTime){
        super(name, job);
        this.setId(id);
        this.setCreatedTime(createdTime);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }
}
