package com.ecommerce.userservice.repositories;

import com.ecommerce.userservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}
