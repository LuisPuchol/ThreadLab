package view;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel{
    private JPanel buttonPanel;
    private ButtonPlay buttonPlay;
    private ButtonStop buttonStop;
    private ButtonCanvaThreat buttonCanvaThreat;
    private Viewer canvas;

    public ControlPanel() {
        System.out.println("Controls creado");

        buttonPlay = new ButtonPlay();
        buttonStop = new ButtonStop();
        buttonCanvaThreat = new ButtonCanvaThreat();

        buttonPanel = new JPanel();

        buttonPanel.add(buttonPlay.getButtonPlay());
        buttonPanel.add(buttonStop.getButtonStop());
        buttonPanel.add(buttonCanvaThreat.getButtonCanvaThreat());
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    public JButton getButtonOK() {
        return buttonPlay.getButtonPlay();
    }

    public JButton getButtonCancel() {
        return buttonStop.getButtonStop();
    }

    public JButton getButtonCanva() {
        return buttonCanvaThreat.getButtonCanvaThreat();
    }

    public Canvas getCanvas() {
        return canvas.getCanvas();
    }

    public void setButtonPanel(JPanel buttonPanel) {
        this.buttonPanel = buttonPanel;
    }

}
