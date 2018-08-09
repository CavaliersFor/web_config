package com.knight.configdemo;

import com.knight.configdemo.base.ApiResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@Controller
public class ConfigDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigDemoApplication.class, args);
    }

    /**
     * 测试thymeleaf是否配置成功
     * @param model 页面变量存储类
     * @return
     */
    @RequestMapping(value = "/",produces = "text/html")
    public String index(Model model) {
        model.addAttribute("name", "测试");
        boolean flag = true;
        if (flag) {
            throw new RuntimeException("错误了");
        }
        return "index";
    }

    @RequestMapping("/")
    @ResponseBody
    public ApiResponse apiResponse(){
        return ApiResponse.ofSuccess(new String[]{"ab","ggg"});
    }

}
