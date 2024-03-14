package com.bkool.infraestructure.config;

import io.swagger.v3.oas.annotations.media.Schema;

public class LoginRequest {
    @Schema(description = "Usuario",example = "user" )
    public String user;
    @Schema(description = "Contraseña",example = "1234" )
    public String pass;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
