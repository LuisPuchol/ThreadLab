package view;

import javax.swing.*;

public class ButtonPause {
    private JButton buttonCanvaThreat;

    public ButtonPause() {
        System.out.println("Boton Pausa creado");
        buttonCanvaThreat = new JButton("Pausa");
        buttonCanvaThreat.setActionCommand("pause");
    }

    public JButton getButtonCanvaThreat() {
        return buttonCanvaThreat;
    }

    public void setButtonCanvaThreat(JButton buttonCanvaThreat) {
        this.buttonCanvaThreat = buttonCanvaThreat;
    }
}
