package com.amd.contorller;

import com.amd.model.User;
import com.amd.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liduankang on 2017/9/13
 */
@Api(description = "用户服务",tags = "API UserController")
@RestController
@RequestMapping("/user")
public class UserController extends AbstractController{

  @Autowired
  private UserRepository userRepository;

  @ApiOperation(value = "新增用户")
  @GetMapping("/{currentUser}")
  public ResponseEntity save(@PathVariable String currentUser){
    User user=new User();
    user.setUserName(currentUser);
    try {
      userRepository.save(user);
      return super.callbackSuccess("保存成功!");
    } catch (Exception e) {
      e.printStackTrace();
      return super.callBusinessError("新增用户失败!");
    }

  }

  @ApiOperation(value = "查找全部用户")
  @GetMapping("list")
  public ResponseEntity findAllUser(){
    List<User> all = userRepository.findAll();

    if (all.isEmpty()) {
      return super.callbackFail("没有用户!");
    }else {
      return super.callbackSuccess(all);
    }
  }

}
