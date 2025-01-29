package view;

import javax.swing.*;

public class ButtonPlay {
    private JButton buttonPlay;

    public ButtonPlay() {
        System.out.println("Boton Play creado");
        buttonPlay = new JButton("Play");
        buttonPlay.setActionCommand("play");
    }

    public JButton getButtonPlay() {
        return buttonPlay;
    }

    public void setButtonPlay(JButton buttonPlay) {
        this.buttonPlay = buttonPlay;
    }
}
