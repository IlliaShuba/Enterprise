package com.example.server.controllers.Ware;

import com.example.server.service.Ware.AirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/airplane")
public class AirplaneController {
    @Autowired
    private AirplaneService airplaneService;


}
