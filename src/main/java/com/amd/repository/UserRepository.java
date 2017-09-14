package com.amd.repository;

import com.amd.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by liduankang on 2017/9/13
 */
public interface UserRepository extends JpaRepository<User,String>{

}
