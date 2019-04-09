package pl.wnuk.demoapp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldEndpoint {

        @RequestMapping(method = RequestMethod.GET, path = "/hello")
        String hello() {
            return "Hello heroku  World!";
        }
    }

