package com.mhalton.hubforum.domain.user.repo;

import com.mhalton.hubforum.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>
{
    User findByUsername(String username);
}
