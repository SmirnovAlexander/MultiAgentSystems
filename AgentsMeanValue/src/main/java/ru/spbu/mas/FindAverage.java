package ru.spbu.mas;

import jade.core.behaviours.TickerBehaviour;

public class FindAverage extends TickerBehaviour{
    private final DefaultAgent agent;
    private int currentStep;
    private final int MAX_STEPS = 10;

    FindAverage(DefaultAgent agent, long period) {
        super(agent, period);
        this.setFixedPeriod(true);
        this.agent = agent;
        this.currentStep = 0;
    }

    @Override
    protected void onTick() {
        if (currentStep < MAX_STEPS) {
            System.out.println("Agent " + this.agent.getLocalName() + ": tick=" + getTickCount());
            this.currentStep++;

        } else {
            this.stop();
        }
    }
}