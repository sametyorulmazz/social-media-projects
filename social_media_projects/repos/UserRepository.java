package com.example.questapp_project.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.questapp_project.entities.User;

public interface UserRepository extends JpaRepository<User, Long>
{

}
