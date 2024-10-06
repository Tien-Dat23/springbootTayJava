package vn.tiendat.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.tiendat.configuration.Translator;
import vn.tiendat.dto.request.UserRequestDTO;
import vn.tiendat.dto.response.ResponseData;
import vn.tiendat.dto.response.ResponseError;
import vn.tiendat.exception.ResourceNotFoundException;
import vn.tiendat.service.UserSevice;

import java.util.List;

@RestController
@RequestMapping("/user")
@Validated
@Slf4j
@Tag(name = "User Controller")
public class UserController {

    @Autowired
    private UserSevice userSevice;

    @Operation(summary = "Add user", description = "Send a request via this API to create new user")
    @PostMapping(value = "/")
    public ResponseData<Integer> addUser(@Valid @RequestBody UserRequestDTO user){
        log.info("Request add user, {} {}", user.getFirstName(), user.getLastName());
        return new ResponseData<>(HttpStatus.CREATED.value(), Translator.toLocale("user.add.success"), 1);

    }

    @Operation(summary = "Update user", description = "Send a request via this API to update user")
    @PutMapping("/{userId}")
    public ResponseData<?> updateUser(@Min(1) @PathVariable int userId,@Valid @RequestBody UserRequestDTO userRequestDTO){
        log.info("Request update userId={}",userId);
        return new ResponseData<>(HttpStatus.ACCEPTED.value(), Translator.toLocale("user.update.success"));
    }

    @Operation(summary = "Change status of user", description = "Send a request via this API to change status of user")
    @PatchMapping("/{userId}")
    public ResponseData<?> changeUser(@PathVariable @Min(1) int userId, @Min(1) @RequestParam(required = false) int changeUser){
        log.info("Request change to userId: {}",userId);
        return new ResponseData<>(HttpStatus.ACCEPTED.value(), Translator.toLocale("user.patch.success"));
    }

    @Operation(summary = "Delete user permanently", description = "Send a request via this API to delete user permanently")
    @DeleteMapping("/{userId}")
    public ResponseData<?> deleteUser(@Min(1) @PathVariable int userId){
        log.info("Request get user detail, userId = {}", userId);
        return new ResponseData<>(HttpStatus.NO_CONTENT.value(), Translator.toLocale("user.delete.success"));
    }

    @Operation(summary = "Get list of users per pageNo", description = "Send a request via this API to get user list by pageNo and pageSize")
    @GetMapping("/list")
    public ResponseData<List<UserRequestDTO>> geAllUser(
            @RequestParam(required = false) String email,
            @RequestParam(defaultValue = "0") int pageNo,
            @Min(10) @RequestParam(defaultValue = "20") int pageSize){
        log.info("Request get all of users");
        return new ResponseData<>(HttpStatus.OK.value(), "user", List.of(new UserRequestDTO( "Alex", "Peter","dksafks@gmail.com","0123456789"),
                new UserRequestDTO( "", "","","")));
    }

    @Operation(summary = "Get user detail", description = "Send a request via this API to get user information")
    @GetMapping("/{userId}")
    public ResponseData<UserRequestDTO> getUser(@PathVariable int userId){
        log.info("Request get userId: {}", userId);
        return new ResponseData<>(HttpStatus.OK.value(), "user", new UserRequestDTO("Tay", "Java", "fdjks@gmail.com", "0123456789"));
    }
}
