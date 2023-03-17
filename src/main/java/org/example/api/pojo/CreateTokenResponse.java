package org.example.api.pojo;

public class CreateTokenResponse {

    private String token;

    public CreateTokenResponse(String token) {
        this.token = token;
    }

    public CreateTokenResponse() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
