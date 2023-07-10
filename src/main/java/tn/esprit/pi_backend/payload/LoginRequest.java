package tn.esprit.pi_backend.payload;

import tn.esprit.pi_backend.entities.User;


import javax.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank
    private String username;

    User user;

    @NotBlank
    private String password;



    public String getUsername() {


            return username;

    }


    public void setUsername(String username) {
            this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}