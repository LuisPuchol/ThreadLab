package controller;

import model.ConfigurationPropertiesDTO;
import model.Model;
import view.View;


import java.util.List;

public class Controller {
    private Model model;
    private View view;


    public Controller() {
        this.model = new Model(this);
        this.view = new View(this);
    }

    public void handlePlayButton() {
        ConfigurationPropertiesDTO configDTO = view.getConfigurationPanel().toConfigurationPropertiesDTO();
        model.applyConfiguration(configDTO);
        model.play();
    }

    public List<Integer[]> getResourcesData() {
        return model.getResourceTypeData();
    }

    public List<Integer[]> getConsumerData() {
        return model.getConsumerData();
    }

    public List<Integer[]> getProducerData() {
        return model.getProducerData();
    }


    public void handleStopButton() {
        model.stop();
    }

    public void handlePauseButton() {
        model.pause();
    }

    public Object[] getCurrentData() {
        return model.getCurrentData();
    }

}
