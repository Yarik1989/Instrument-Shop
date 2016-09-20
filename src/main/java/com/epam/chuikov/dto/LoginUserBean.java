package com.epam.chuikov.dto;

import com.epam.chuikov.validation.annotation.EmailValidation;
import com.epam.chuikov.validation.annotation.NotBlank;
import com.epam.chuikov.validation.annotation.PatternValidation;
import com.epam.chuikov.validation.api.Validateable;


public class LoginUserBean implements Validateable {

    @NotBlank
    @EmailValidation
    private String email;

    @NotBlank
    @PatternValidation(regex = "^(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{6,}$",
            errorMessage = "Should be greater that 6 symbols and contain al least one number")
    private String password;

    public LoginUserBean(String email, String password) {
        this.email = email;
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoginUserBean that = (LoginUserBean) o;

        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        return password != null ? password.equals(that.password) : that.password == null;

    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LoginUserBean{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
