package view;

import javax.swing.*;

public class ButtonCanvaThreat {
    private JButton buttonCanvaThreat;

    public ButtonCanvaThreat() {
        System.out.println("Boton Canva creado");
        buttonCanvaThreat = new JButton("Canva Threat");
        buttonCanvaThreat.setActionCommand("canva");
    }

    public JButton getButtonCanvaThreat() {
        return buttonCanvaThreat;
    }

    public void setButtonCanvaThreat(JButton buttonCanvaThreat) {
        this.buttonCanvaThreat = buttonCanvaThreat;
    }
}
