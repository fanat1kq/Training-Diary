package org.example.in.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.example.exception.AuthorizeException;
import org.example.in.dto.*;
import org.example.in.mappers.TrainingMapper;
import org.example.in.mappers.UserMapper;
import org.example.model.Users;
import org.example.service.TrainingService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.example.util.urlPath.*;

/**
 * controller for working with the player and his data
 */
@RestController
@RequestMapping("/training")
@Validated
@Api(value = "TrainingController" , tags = {"Training Controller"})
@SwaggerDefinition(tags = {
        @Tag(name = "Training Controller")
})
public class TrainingController {
    private final UserService userService;
    private final TrainingService trainingService;
    private final UserMapper userMapper;
    private final TrainingMapper trainingMapper;
    private SecurityContext securityContext;

@Autowired
    public TrainingController(UserService userService, TrainingService trainingService, UserMapper userMapper, TrainingMapper trainingMapper) {
        this.userService = userService;
        this.trainingService = trainingService;
        this.userMapper = userMapper;
        this.trainingMapper = trainingMapper;
        this.securityContext = SecurityContextHolder.getContext();
    }
    public TrainingController(UserService userService, TrainingService trainingService, UserMapper userMapper, TrainingMapper trainingMapper, SecurityContext securityContext) {
        this.userService = userService;
        this.trainingService = trainingService;
        this.userMapper = userMapper;
        this.trainingMapper = trainingMapper;
        this.securityContext = securityContext;
    }

    @ApiOperation(value = "Return the user's training history", response = TrainingMapper.class, tags = "history")
    @GetMapping(HISTORY)
    public ResponseEntity<?> getHistory(@RequestParam String login) {
        if (!isValidLogin(login)) return ResponseEntity.badRequest()
                .body(new ExceptionResponse("Incorrect login"));
        Users users = userService.getByLogin(login);
        List<TrainingDTO> list = trainingMapper.toDTOList(
                trainingService.getTraining(users.getId(), users.getRole()));
        return ResponseEntity.ok().body(new TrainingHistoryResponse(login, list));
    }

    /**
     * Get the player's transactions history
     *
     * @param login the player login
     * @return response entity
     */
    @ApiOperation(value = "Return all calorie for 3 month", response = CaloriesStaticResponse.class, tags = "getCalorieStatics")
    @GetMapping(CALORIE)
    public ResponseEntity<?> getStaticCalorie(@RequestParam String login) {
        if (!isValidLogin(login)) return ResponseEntity.badRequest()
                .body(new ExceptionResponse("Incorrect login"));
        int staticCalorie = trainingService.getStatistic();
        return ResponseEntity.ok().body(new CaloriesStaticResponse(login, staticCalorie));
    }

    @ApiOperation(value = "add new training", response = SuccessResponse.class, tags = "add")
    @PostMapping(ADD)
    public ResponseEntity<?> addTraining(@RequestBody @Valid AddTrainingRequest request, @RequestParam String login) {
        if (!isValidLogin(login)) return ResponseEntity.badRequest()
                .body(new ExceptionResponse("Incorrect login"));
        Users users = userService.getByLogin(login);
        trainingService.addTraining(request, users.getId());
        return ResponseEntity.ok(new SuccessResponse("Adding training completed successfully!"));
    }
    @ApiOperation(value = "update training", response = SuccessResponse.class, tags = "update")
    @PostMapping(UPDATE)
    public ResponseEntity<?> updateTraining(@RequestBody @Valid UpdateTrainingRequest request, @RequestParam String login) {
        if (!isValidLogin(login)) return ResponseEntity.badRequest()
                .body(new ExceptionResponse("Incorrect login"));
        Users users = userService.getByLogin(login);
        trainingService.updateTraining(users, request);
        return ResponseEntity.ok(new SuccessResponse("Updating training completed successfully!"));
    }
    @ApiOperation(value = "delete training", response = SuccessResponse.class, tags = "delete")
    @PostMapping(DELETE)
    public ResponseEntity<?> deleteTraining(@RequestBody @Valid DeleteTrainingRequest request, @RequestParam String login) {
        if (!isValidLogin(login)) return ResponseEntity.badRequest()
                .body(new ExceptionResponse("Incorrect login"));
        trainingService.deleteTraining(request.id());
        return ResponseEntity.ok(new SuccessResponse("Deleting training completed successfully!"));
    }
    private boolean isValidLogin(String login) {
        if (securityContext.getAuthentication() == null) securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication == null) throw new AuthorizeException("Unauthorized!");
        User principal = (User) authentication.getPrincipal();
        return principal.getUsername().equals(login);
    }
}
