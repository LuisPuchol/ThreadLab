package controller;

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
        model.play();
    }

    public Object[] getUpdatedData() {
        // Verifica si el ResourceType es nulo
        if (model.getResourceType() == null) {
            System.out.println("ResourceType aún no está inicializado.");
            return new Integer[]{0}; // Devuelve un valor predeterminado, como 0
        }

        Integer counterValue = model.getResourceType().getQuantity();
        System.out.println("Counter value: " + counterValue);
        return new Integer[]{counterValue};
    }


    public ResourceType getResourceType() {
        return model.getResourceType();
    }

    public void handleCanvaButton(Canvas canvas) {
        new Thread(() -> {
            while (true) {
                try {
                    Color color = model.generateRandomColor();
                    SwingUtilities.invokeLater(() -> canvas.setBackground(color));
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }).start();
    }

    public void handleCancelButton() {
        model.stop();
    }

    public int returnCounter(){
        return model.getCounter();
    }

}
