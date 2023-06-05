package com.ecommerce.userservice.services;

import com.ecommerce.userservice.models.User;
import com.ecommerce.userservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import utilities.models.ResponseMessage;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final StorageService storage;
    private final Path rootLocation = Paths.get("upload");

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getOneUserService(Long id) {
        return userRepository.findById(id).orElse(null);
    }


    public User createUser(User u) {
        return userRepository.save(u);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User updateUser(User u, Long id) {
        User u1 = userRepository.findById(id).orElse(null);
        if (u1 != null) {
            u.setId(id);
            return userRepository.saveAndFlush(u);
        } else {
            throw new RuntimeException("fail");
        }
    }

    public ResponseEntity<Resource> getFile(String filename) {
        Resource file = storage.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachement;filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    public ResponseEntity<ResponseMessage> uploadFiles (MultipartFile[]files, User u){
        String message = "";
        try {
            ArrayList<String> fileNames = new ArrayList<>();
            Arrays.asList(files).stream().forEach(file -> {
                try {
                    String fileName = Integer.toString(new Random().nextInt(1000000));
                    String ext = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf('.'),
                            file.getOriginalFilename().length());
                    String name = file.getOriginalFilename().substring(0, file.getOriginalFilename().indexOf('.'));
                    String original = name + fileName + ext;
                    Files.copy(file.getInputStream(), this.rootLocation.resolve(original));
                    fileNames.add(original);
                    u.setImage(String.valueOf(fileNames));
                } catch (Exception e) {
                    throw new RuntimeException("fail file problem backend");
                }
            });


            userRepository.save(u);
            message = "Uploaded the file successufully" + fileNames;
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

        } catch (Exception e) {
            message = "fail to upload";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

}
