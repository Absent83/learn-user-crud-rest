package com.myhome.springCrudRest.form;

public class UserForm {

    private String name;
    private String email;

    public UserForm() {
    }

    public UserForm(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
