package controller;

import model.ConfigurationPropertiesDTO;
import model.Model;
import model.ResourceType;
import view.View;

import javax.swing.*;
import java.awt.*;

public class Controller {
    private Model model;
    private View view;


    public Controller() {
        this.model = new Model(this);
        this.view = new View(this);
    }

    public void handleCounterButton() {
        ConfigurationPropertiesDTO configDTO = view.getConfigurationPanel().toConfigurationPropertiesDTO();
        model.applyConfiguration(configDTO);
        model.play();
    }


    public void handleCancelButton() {
        model.stop();
    }

    public int returnCounter() {
        return 1; //model.getCounter();
    }

    public Object[] getCurrentData() {
        return model.getCurrentData();
    }

}
