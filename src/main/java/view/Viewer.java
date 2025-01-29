package view;

import java.awt.*;

public class Viewer extends Canvas {
    public Canvas canvas;

    public Viewer() {
        System.out.println("Viewers creado");
        canvas = new Canvas();
        canvas.setSize(400, 300);
        canvas.setBackground(Color.CYAN);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }
}
