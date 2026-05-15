package com.oksusu.session.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 페이지 라우터 처리하기 위해
@Controller
public class IndexController {

    // 권한 체크할 떄 필요한 페이지

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/admin/page")
    public String admin() {
        return "admin/admin";
    }

    @GetMapping("/user/page")
    public String user() {
        return "user/user";
    }
}
