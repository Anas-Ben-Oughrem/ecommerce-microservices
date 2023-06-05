package claimservice.services;

import claimservice.models.Comment;
import claimservice.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import utilities.dtos.UserResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final WebClient.Builder webClientBuilder;

    public List<Comment> getAll(){
        return commentRepository.findAll() ;
    }

    public Comment createComment ( Comment comment ,  Long userId){
        UserResponse userResponse = webClientBuilder.build()
                .get()
                .uri("http://localhost:8081/users/"+userId)
                .retrieve()
                .bodyToMono(UserResponse.class)
                .block();
        if (userResponse == null) {
            throw new RuntimeException("User not found");
        }
        comment.setUserId(userId);
        return commentRepository.save(comment);
    }
    public void deleteComment (Long id) {
        commentRepository.deleteById(id);
        ResponseEntity.ok().build();
    }

    public Comment updateComment ( Comment comment,  Long id){
        Comment comment1 = commentRepository.findById(id).orElse(null);
        if (comment1 !=null){
            comment.setId(id);
            return commentRepository.saveAndFlush(comment);
        }
        else{
            throw new RuntimeException("fail");
        }
    }
}
