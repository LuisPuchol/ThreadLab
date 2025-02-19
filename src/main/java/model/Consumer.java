package model;

import java.util.Random;

public class Consumer extends Thread {
    private Model model;
    private ResourceType resourceType;
    private int delay;
    private int startDelay;
    private Random random = new Random();

    public Consumer(Model model, ResourceType resourceType, int delay, int startDelay) {
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
                if (resourceType.consumeResource()) {
                    System.out.println("Consumer consumió 1 unidad. Total: " + resourceType.getQuantity());
                } else {
                    System.out.println("Consumer intentó consumir, pero no hay recursos disponibles.");
                }
            } catch (InterruptedException e) {
                System.out.println("Consumer detenido.");
                return; // Salir del hilo si se interrumpe
            }
        }
    }

}



