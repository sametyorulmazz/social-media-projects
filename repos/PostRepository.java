package com.example.questapp_project.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.questapp_project.entities.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>
{
    List<Post> findByUserId(Long userId);
}
