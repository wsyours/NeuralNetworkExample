package com.example.neuralNetwork.service;


import com.example.neuralNetwork.controller.Controller;
import com.googlecode.fannj.*;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.sun.jna.win32.StdCallFunctionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Сергей on 03.12.2017.
 */
@Service
public class NetServiceImpl implements NetService {

    /**
     * Logger
     */
    private static Logger logger = LoggerFactory.getLogger(Controller.class);

    public void train() {
        /*
        Creating layers list...
        6 input neurons,
        24 middle neurons,
        4 output neurons
         */
        List<Layer> layerList = new ArrayList<Layer>();
        layerList.add(Layer.create(6 , ActivationFunction.FANN_SIGMOID_SYMMETRIC, 0.01f));
        layerList.add(Layer.create(24, ActivationFunction.FANN_SIGMOID_SYMMETRIC, 0.01f));
        layerList.add(Layer.create(4, ActivationFunction.FANN_SIGMOID_SYMMETRIC, 0.01f));
       // System.setProperty("jna.library.path","C:\\projects\\fann-master\\bin\\x64");
        Fann fann = new Fann(layerList);
        //Creating trainer and defining training algorithm...
        Trainer trainer = new Trainer(fann);
        trainer.setTrainingAlgorithm(TrainingAlgorithm.FANN_TRAIN_RPROP);
        /*
        Training, getting lessons from train.data, max cycles - 1000000, show report each 1000 iteration,
        error less 0.001
         */
        trainer.train(new File("train.data").getAbsolutePath(), 1000000, 1000, 0.001f);
        fann.save("ann");
    }

    @Override
    public void test() {
        Fann fann = new Fann("ann");
        float[][] tests = {
                {0.15f, 0.85f, 0.74f, 0.05f, 0.25f, 0.9f},
                {0.35f, 0.15f, 0.34f, 0.18f, 0.7f, 0.1f},
                {0.6f, 0.25f, 0.9f, 0.6f, 0.25f, 0.43f},
                {1, 0.1f, 0.05f, 0.5f, 1, 1},


        };
        for (float[] test:tests){
            logger.info(getAction(fann.run(test)));
        }
    }



    private static String getAction(float[] out){
        int i = 0;
        for (int j = 1; j < 4; j++) {
            if(out[i]<out[j]){
                i = j;
            }
        }
        switch (i){
            case 0:return "Normal working";
            case 1:return "Reduce video quality";
            case 2:return "Turn on battery economy mode";
            case 3:return "Turn on battery economy mode and reduce video quality";
        }
        return "";
    }
}
