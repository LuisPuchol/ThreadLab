package view;

import javax.swing.*;

public class ButtonStop {
    private JButton buttonStop;

    public ButtonStop() {
        System.out.println("Boton Stop creado");
        buttonStop = new JButton("Stop");
        buttonStop.setActionCommand("stop");
    }

    public JButton getButtonStop() {
        return buttonStop;
    }

    public void setButtonStop(JButton buttonStop) {
        this.buttonStop = buttonStop;
    }
}
