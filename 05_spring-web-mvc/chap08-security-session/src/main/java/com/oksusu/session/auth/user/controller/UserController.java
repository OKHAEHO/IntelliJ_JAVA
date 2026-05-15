package com.oksusu.session.auth.user.controller;

import com.oksusu.session.auth.user.model.SignUpDTO;
import com.oksusu.session.auth.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    private void signUp() {

    }

    @PostMapping("/signup")
    public ModelAndView signUp(ModelAndView mv, @ModelAttribute SignUpDTO signUpDTO) {

        Integer result = userService.regist(signUpDTO);

        String message = null;

        if (result == null) {
            message = "중복회원이 존재합니다";
        }else if(result == 0){
            message = "회원가입에 실패했습니다";
            mv.setViewName("user/signup");
        }else if(result >= 1){
            message = "회원가입에 성공했습니다";
            mv.setViewName("auth/login");
        }

        mv.addObject("message", message);
        return mv;
    }
}
