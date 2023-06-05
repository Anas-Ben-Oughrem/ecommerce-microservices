package com.ecommerce.userservice.controllers;

import com.ecommerce.userservice.models.User;
import com.ecommerce.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import utilities.models.ResponseMessage;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping
    public List<User> getAll(){
        return userService.getAll() ;
    }

    @GetMapping("/{id}")
    public User getOneUser(@PathVariable Long id){
        return userService.getOneUserService(id);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser (@PathVariable Long id) {
        userService.deleteUser(id);
    }
    @PutMapping("/{id}")
    public User updateUser (@RequestBody User u, @PathVariable Long id){
        return userService.updateUser(u,id);
    }

    @PostMapping
    public User saveuser(User user ) {
        return userService.createUser(user);
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFiles(@RequestParam("files") MultipartFile[] files, User u) {
        return userService.uploadFiles(files,u);
    }

    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource>getFileService(@PathVariable String filename){
        return userService.getFile(filename);}

}
