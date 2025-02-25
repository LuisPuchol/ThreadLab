package view;

import javax.swing.*;

public class ControlPanel extends JPanel{
    private JPanel buttonPanel;
    private ButtonPlay buttonPlay;
    private ButtonStop buttonStop;
    private ButtonPause buttonPause;

    public ControlPanel() {
        System.out.println("Controls creado");

        buttonPlay = new ButtonPlay();
        buttonStop = new ButtonStop();
        buttonPause = new ButtonPause();

        buttonPanel = new JPanel();

        buttonPanel.add(buttonPlay.getButtonPlay());
        buttonPanel.add(buttonStop.getButtonStop());
        buttonPanel.add(buttonPause.getButtonCanvaThreat());
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    public JButton getButtonPlay() {
        return buttonPlay.getButtonPlay();
    }

    public JButton getButtonStop() {
        return buttonStop.getButtonStop();
    }

    public JButton getButtonPause() {
        return buttonPause.getButtonCanvaThreat();
    }

}
