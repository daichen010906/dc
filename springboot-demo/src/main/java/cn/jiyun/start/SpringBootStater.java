package cn.jiyun.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class SpringBootStater {
    @GetMapping("/")
    private String home() {
        return "Hello World! 123";
    }
    public static void main(String[] args) {
        SpringApplication.run(SpringBootStater.class,args);
    }
}
