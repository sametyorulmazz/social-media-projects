package com.example.questapp_project.services;
import com.example.questapp_project.entities.Comment;
import com.example.questapp_project.entities.Post;
import com.example.questapp_project.entities.User;
import com.example.questapp_project.repos.CommentRepository;
import com.example.questapp_project.request.CommentCreateRequest;
import com.example.questapp_project.request.CommentUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService
{
    private CommentRepository commentRepository;
    private UserService userService;
    private PostService postService;

    public CommentService(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    public List<Comment> getAllCommentsWithParam(Optional<Long> userId,
                                                 Optional<Long> postId) {
        if(userId.isPresent() && postId.isPresent()){
            return commentRepository.findByUserIdAndPostId(userId.get(),postId.get());
        }
        else if(userId.isPresent()){
            return commentRepository.findByUserId(userId.get());
        }
        else if(postId.isPresent()){
            return commentRepository.findByPostId(postId.get());
        }
        else{
            return commentRepository.findAll();
        }
    }

    public Comment getOneCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public Comment createOneComment(CommentCreateRequest request) {
        User user = userService.getOneUserById(request.getUserId());
        Post post = postService.getOnePostById(request.getPostId());
        if(user!=null && post!=null){
            Comment commentToSave = new Comment();
            commentToSave.setId(request.getId());
            commentToSave.setUser(user);
            commentToSave.setPost(post);
            commentToSave.setText(request.getText());
            return commentRepository.save(commentToSave);
        }
        else
            return null;
    }

    public Comment updateOneCommentById(Long commentId, CommentUpdateRequest request) {
        Optional<Comment> comment = commentRepository.findById(commentId); //Öyle bir comment var mı diye bakıyoruz?
        if(comment.isPresent()){ //bu comment ispresent ise yani gerçekten varsa o zaman onu update edebiliriz
            Comment commentToUpdate = comment.get(); //comment varsa onu update edelim
            commentToUpdate.setText(request.getText());
            //update edeceğimiz commentToUpdate'i bu gelen request'e göre update edebiliriz
            //text'ini de request'ten gelen text ile değiştirelim
            return commentRepository.save(commentToUpdate); //değiştirilmiş halini de repo'ya kaydedelim
        }
        else
            return null;
    }

    public void deleteOneCommentById(Long commentId) {
        commentRepository.deleteById(commentId);
    }

}
