package com.gl.hack.repo;

import com.gl.hack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepo extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
}
