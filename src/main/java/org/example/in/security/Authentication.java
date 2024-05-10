package org.example.in.security;


import java.util.Objects;



public class Authentication {
    private String login;
    private boolean isAuth;
    private String message;
    public boolean isAuth() {
        return isAuth;
    }

    public Authentication(String login, boolean isAuth, String message) {
        this.login = login;
        this.isAuth = isAuth;
        this.message = message;
    }

    @Override
    public String toString() {
        return "Authentication{" +
                "login='" + login + '\'' +
                ", isAuth=" + isAuth +
                ", message='" + message + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authentication that = (Authentication) o;
        return isAuth == that.isAuth && Objects.equals(login, that.login) && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, isAuth, message);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setAuth(boolean auth) {
        isAuth = auth;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
