package model;

import java.time.LocalTime;

public class Consumer extends Thread {
    private static int idCounter = 1;
    private int id;
    private Model model;
    private ResourceType resourceType;
    private int consumeDelay;
    private int startDelay;
    private int timesConsumed;
    private long processingTime;
    private LocalTime startTime;
    private LocalTime endTime;
    private ThreadState state;

    public Consumer(Model model, ResourceType resourceType, int consumeDelay, int startDelay) {
        this.id = idCounter++;
        this.model = model;
        this.resourceType = resourceType;
        this.consumeDelay = consumeDelay;
        this.startDelay = startDelay;
        this.timesConsumed = 0;
        this.processingTime = 0;
        this.state = ThreadState.IDLE;
    }

    @Override
    public void run() {
        try {
            startTime = LocalTime.now();
            Thread.sleep(startDelay);
            long startProcessing = System.currentTimeMillis();
            state = ThreadState.RUNNING;

            while (state != ThreadState.STOPPED) {
                synchronized (this) {
                    while (state == ThreadState.PAUSED) {
                        wait();
                    }
                }

                if(state == ThreadState.STOPPED) break;


                Thread.sleep(consumeDelay);
                resourceType.consumeResource();
                timesConsumed++;
                processingTime = System.currentTimeMillis() - startProcessing;
            }

            endTime = LocalTime.now();
        } catch (InterruptedException e) {
            state = ThreadState.STOPPED;
        }
    }

    public synchronized void setState(ThreadState newState) {
        this.state = newState;
        notifyAll();
    }

    public synchronized ThreadState getThreadState() {
        return state;
    }

    public Integer[] getConsumerInfo() {
        return new Integer[]{
                id,
                resourceType.getResourceID(),
                startDelay,
                consumeDelay,
                timesConsumed,
                (int) processingTime,
                startTime.getHour() * 10000 + startTime.getMinute() * 100 + startTime.getSecond(),
                (endTime != null) ? (endTime.getHour() * 10000 + endTime.getMinute() * 100 + endTime.getSecond()) : 0
        };
    }
}




