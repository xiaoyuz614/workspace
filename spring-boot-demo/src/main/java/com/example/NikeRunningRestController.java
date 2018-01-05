package com.example;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NikeRunningRestController {
    @RequestMapping(value = "/",method= RequestMethod.GET)
    public String hellowWorld(){
        return "Hello Nike Running";
    }

    @RequestMapping(value="/{studentName}",method=RequestMethod.GET)
    public String helloStudent(@PathVariable String studentName){
        return "Hello "+studentName;
    }
}
