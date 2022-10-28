package com.springboot.hello.Controller;

import com.springboot.hello.dao.UserDao;
import com.springboot.hello.domain.User;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v2/user")
public class UserController {
    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }
    // create
    @PostMapping("/create")
    public String add(@RequestBody User user) {
        userDao.add(user);
        return String.format("%s번째 %s의 정보가 등록되었습니다.",user.getId(),user.getName());
    }
    // read
    @GetMapping("/read/{userId}")
    public String findById(@PathVariable String userId){
        User user = userDao.findById(userId);
        return String.format("Id : %s 의 회원 정보입니다. [name:%s password:%s]", user.getId(), user.getName(),user.getPassword() );
    }
    @GetMapping(value = "/read/all")
    public String readAll() {
        List<User> users = userDao.getAll();
        String str = "";

        for (User user : users) {
            str += String.format("id : %s name : %s password : %s\n",
                    user.getId(), user.getName(), user.getPassword());
        }
        return str;
    }
    // update
    @PutMapping("/update/{userId}")
    public String updateUser(@RequestBody User user, @PathVariable String userId){
        //user.setId(userId);
        userDao.update(user, userId);
        return String.format("%s 회원의 정보가 성공적으로 업데이트 되었습니다.\nname:%s\npassword:%s\n"
                , userDao.findById(user.getId())
                , userDao.findById(user.getName())
                , userDao.findById(user.getPassword()));
    }

    // delete
    @DeleteMapping(value = "/delete/all")
    public String deleteAll(){
        userDao.deleteAll();
        return "회원이 존재하지 않습니다.";
    }

}
