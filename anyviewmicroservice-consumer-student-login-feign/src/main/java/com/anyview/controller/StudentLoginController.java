package com.anyview.controller;

import com.anyview.service.StudentLoginClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentLoginController {

    @Autowired
    private StudentLoginClientService service=null;
}
