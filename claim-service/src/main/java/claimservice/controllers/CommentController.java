package claimservice.controllers;

import claimservice.models.Comment;
import claimservice.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Comment")
@CrossOrigin("*")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Comment> getAll(){
        return commentService.getAll() ;
    }

    @PostMapping("/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Comment createComment (@RequestBody Comment comment , @PathVariable Long userId){
        return commentService.createComment(comment,userId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteComment (@PathVariable Long id) {
        commentService.deleteComment(id);

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Comment updateComment (@RequestBody Comment comment, @PathVariable Long id){
        return commentService.updateComment(comment,id);
    }

}
