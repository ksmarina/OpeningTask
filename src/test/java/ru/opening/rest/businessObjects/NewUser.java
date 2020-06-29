package ru.opening.rest.businessObjects;

public class NewUser {
    private String name;
    private String job;

    public NewUser(String name, String job) {
        this.setName(name);
        this.setJob(job);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
