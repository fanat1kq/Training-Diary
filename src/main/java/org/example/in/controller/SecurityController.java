package org.example.in.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import lombok.RequiredArgsConstructor;
import org.example.in.dto.JwtResponse;
import org.example.in.dto.SecurityRequest;
import org.example.in.dto.SecurityRequestAuth;
import org.example.in.dto.UserDTO;
import org.example.in.mappers.UserMapper;
import org.example.model.Users;
import org.example.service.SecurityService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.example.util.urlPath.LOGIN;
import static org.example.util.urlPath.REGISTRATION;

/**
 * The security controller
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(LOGIN)
@Validated
@Api(value = "SecurityController" , tags = {"Security Controller"})
@SwaggerDefinition(tags = {
        @Tag(name = "Security Controller")
})
public class SecurityController {

    private final SecurityService securityService;
    private final UserMapper userMapper;

    /**
     * Authorization player in application
     *
     * @param dto the security request
     * @return response entity
     */
    @ApiOperation(value = "Return the JWT", response = JwtResponse.class, tags = "login")
    @PostMapping(LOGIN)
    public ResponseEntity<?> login(@Valid @RequestBody SecurityRequestAuth dto) {
        Users users = Users.builder().login(dto.login()).password(dto.password()).build();
        JwtResponse response = securityService.authorization(users);
        return ResponseEntity.ok(response);
    }

    /**
     * Register the player in application
     *
     * @param dto the security request
     * @return response entity
     */
    @ApiOperation(value = "Return the player dto", response = UserDTO.class, tags = "registration")
    @PostMapping(REGISTRATION)
    public ResponseEntity<?> registration(@Valid @RequestBody SecurityRequest dto) {
        Users users = Users.builder().login(dto.login()).password(dto.password()).role(dto.role()).build();
        Users register = securityService.register(users);
        return ResponseEntity.ok(userMapper.toDto(register));
    }
}
