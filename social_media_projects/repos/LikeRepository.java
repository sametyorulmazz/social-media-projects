package com.example.questapp_project.repos;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.questapp_project.entities.Like;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long>
{
    List<Like> findByUserIdAndPostId(Long userId, Long postId);

    List<Like> findByUserId(Long userId);

    List<Like> findByPostId(Long postId);
}
