package com.example.neuralNetwork.service;

import com.sun.jna.win32.StdCallLibrary;
import org.springframework.stereotype.Service;

/**
 * Created by wsyours 04.03.2018
 */
@Service
public interface NetService extends StdCallLibrary {

    /**
     * Intitalisation, training and saving netfork file
     **/
     void train();

    /**
     * Testing network
     **/
    void test();

}
