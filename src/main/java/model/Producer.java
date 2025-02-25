package model;

import java.util.Random;

import java.util.Random;

public class Producer extends Thread {
    private Model model;
    private ResourceType resourceType;
    private int delay;
    private int startDelay;
    private Random random = new Random();

    public Producer(Model model, ResourceType resourceType, int delay, int startDelay) {
        this.model = model;
        this.resourceType = resourceType;
        this.delay = delay;
        this.startDelay = startDelay;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(startDelay); // Delay inicial
        } catch (InterruptedException e) {
            return;
        }

        while (!isInterrupted()) {
            try {
                Thread.sleep(delay);
                resourceType.addResource();
            } catch (InterruptedException e) {
                System.out.println("Producer detenido.");
                return; // Salir del hilo si se interrumpe
            }
        }
    }

}
