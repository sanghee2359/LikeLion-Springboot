package com.springboot.hello.Controller;

import com.springboot.hello.dao.UserDao;
import com.springboot.hello.domain.User;

import com.springboot.hello.domain.dto.MemberDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }
    // create
    @PostMapping("/user")
    public String add(@RequestBody User user) {
        userDao.add(user);
        return String.format("%s번째 %s의 정보가 등록되었습니다.",user.getId(),user.getName());
    }
    // read
    @GetMapping("/user/{userId}")
    public String findById(@PathVariable String userId){
        User user = userDao.findById(userId);
        return String.format("Id : %s 의 회원 정보입니다. [name:%s password:%s]", user.getId(), user.getName(),user.getPassword() );
    }
    // update
    @PostMapping("/user")
    public ResponseEntity<UserDao>
    putMember(@RequestBody UserDao userDao){
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(userDao);
    }
    // delete
    @DeleteMapping(value = "/user/all")
    public String deleteAll(){
        userDao.deleteAll();
        return "회원이 존재하지 않습니다.";
    }

}
