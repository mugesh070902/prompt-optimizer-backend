package com.promptoptimizer.repository;

import com.promptoptimizer.model.User;
import org.springframework.data.jpa.repository.*;
import java.util.Optional;

public interface UserRepository
extends JpaRepository<User,Long>{

Optional<User> findByEmail(String email);

}
