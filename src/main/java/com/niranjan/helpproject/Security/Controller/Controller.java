package com.niranjan.helpproject.Security.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
public class Controller
{

    @RequestMapping("/")
    public String didplay()
    {
        return "index.html";
    }
}
