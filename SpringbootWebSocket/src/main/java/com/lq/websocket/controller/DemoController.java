package com.lq.websocket.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description
 * @Date 2020/12/30 9:29
 * @Author lq
 */
@RestController
public class DemoController {
    @GetMapping("/index")
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("请求成功");
    }

    @GetMapping("/page")
    public ModelAndView page() {
        return new ModelAndView("login");
    }

}
