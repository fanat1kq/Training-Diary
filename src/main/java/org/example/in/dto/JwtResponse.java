package org.example.in.dto;
//для получения токена после входа (после сервиса)
public record JwtResponse(String login, String accessToken, String refreshToken) {
}
