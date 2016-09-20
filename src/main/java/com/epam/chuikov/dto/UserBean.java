package com.epam.chuikov.dto;


import com.epam.chuikov.validation.annotation.NotBlank;
import com.epam.chuikov.validation.annotation.PatternValidation;


public class UserBean extends LoginUserBean {
    @NotBlank
    @PatternValidation(regex = "^[A-Z][a-z0-9_-]{3,15}$",
            errorMessage = "Must starts with uppercase letter and has length between 3 and 15 chars")
    private String firstName;

    @NotBlank
    @PatternValidation(regex = "^[A-Z][a-z0-9_-]{3,15}$",
            errorMessage = "Must starts with uppercase letter and has length between 3 and 15 chars")
    private String lastName;

    @NotBlank
    private boolean isSubscribed;

    private String photoPath;

    public UserBean(String firstName, String lastName, String email,
                    String password, boolean isSubscribed, String photoPath) {
        super(email, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.isSubscribed = isSubscribed;
        this.photoPath = photoPath;
    }

    public UserBean(String firstName, String lastName, String email,
                    String password, boolean isSubscribed) {
        super(email, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.isSubscribed = isSubscribed;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public boolean isSubscribed() {
        return isSubscribed;
    }

    public void setSubscribed(boolean subscribed) {
        isSubscribed = subscribed;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isSubscribed=" + isSubscribed +
                ", photoPath='" + photoPath + '\'' +
                '}';
    }
}
