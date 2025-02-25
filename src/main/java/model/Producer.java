package model;

import java.util.Random;

import java.util.Random;

import java.time.LocalTime;
import java.util.Random;

public class Producer extends Thread {
    private static int idCounter = 1;
    private int id;
    private Model model;
    private ResourceType resourceType;
    private int produceDelay;
    private int startDelay;
    private int timesProduced;
    private long processingTime;
    private LocalTime startTime;
    private LocalTime endTime;
    private ThreadState state;

    public Producer(Model model, ResourceType resourceType, int produceDelay, int startDelay) {
        this.id = idCounter++;
        this.model = model;
        this.resourceType = resourceType;
        this.produceDelay = produceDelay;
        this.startDelay = startDelay;
        this.timesProduced = 0;
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


                Thread.sleep(produceDelay);
                resourceType.addResource();
                timesProduced++;
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

    public Integer[] getProducerInfo() {
        return new Integer[]{
                id,
                resourceType.getResourceID(),
                startDelay,
                produceDelay,
                timesProduced,
                (int) processingTime,
                startTime.getHour() * 10000 + startTime.getMinute() * 100 + startTime.getSecond(),
                (endTime != null) ? (endTime.getHour() * 10000 + endTime.getMinute() * 100 + endTime.getSecond()) : 0
        };
    }
}

