package view;

import javax.swing.*;

public class ButtonThread {
    private JButton buttonThreats;

    public ButtonThread() {
        System.out.println("Boton Threats creado");
        buttonThreats = new JButton("1000");
        buttonThreats.setActionCommand("thread");
    }

    public JButton getButtonThreats() {
        return buttonThreats;
    }

    public void setButtonThreats(JButton buttonThreats) {
        this.buttonThreats = buttonThreats;
    }
}
