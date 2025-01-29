package model;

public class Producer implements Runnable {
    private volatile boolean running = false;
    private Integer iteraciones = 10000;
    private Model model;
    private ResourceType resourceType;

    private Enum state;//running ended
    private int startTime;//no es int, algo para medir nanosegs

    public Producer(Model model, ResourceType resourceType) {
        this.model = model; // Asignar el modelo
        this.resourceType = resourceType;
    }

    //Suma desde X
    @Override
    public void run() {
        running = true;
        int cycles = 0; // You can adjust this value as needed

        while(running && cycles < iteraciones){
            for (int i = 0; i < iteraciones; i++) {
                resourceType.addResources();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                cycles++;
            }
        }
    }

    public void produce() {

    }

    public boolean isRunning() {
        return running;
    }

    public void stop() {
        running = false;
    }
}
