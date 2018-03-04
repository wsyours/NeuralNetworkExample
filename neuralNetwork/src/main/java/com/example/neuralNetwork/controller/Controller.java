package com.example.neuralNetwork.controller;

import com.googlecode.fannj.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.neuralNetwork.service.NetService;

/**
 * Created by wsyours 04.03.2018
 */

@RestController
@RequestMapping("/control")
public class Controller {


    @Autowired
/**
 * Neural Network service
 */
    private NetService netService;

    /**
     * Constructor
     */
    public Controller(NetService netService) {
        this.netService = netService;
    }

    @RequestMapping(value = "train", method = {RequestMethod.GET, RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public void teachNetwork() {
        netService.train();
    }

    @RequestMapping(value = "test", method = {RequestMethod.GET, RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public void testNetwork() {
        netService.test();
    }
}
