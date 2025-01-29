package model;

public class Consumer implements Runnable {
    private volatile boolean running = false;
    private Integer iteraciones = 10000;
    private Model model;
    private ResourceType resourceType;

    private Enum state;//running ended
    private int startTime;//no es int, algo para medir nanosegs

    public Consumer(Model model, ResourceType resourceType) {
        this.model = model; // Asignar el modelo
        this.resourceType = resourceType;
    }

    //Resta desde X
    @Override
    public void run() {
        running = true;
        int cycles = 0;
        while(running && cycles < iteraciones){
            for (int i = 0; i < iteraciones; i++) {
                resourceType.removeResource();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                cycles++;
            }
        }
    }

    public void consume() {

    }

    public boolean isRunning() {
        return running;
    }

    public void stop() {
        running = false;
    }

}
