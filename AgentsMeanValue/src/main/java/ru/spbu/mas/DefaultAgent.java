package ru.spbu.mas;

import jade.core.Agent;
import java.util.concurrent.TimeUnit;

public class DefaultAgent extends Agent{
    private String[] linkedAgents;
    private float number;

    @Override
    protected void setup() {
        int id = Integer.parseInt(getAID().getLocalName());

        System.out.println("Agent #" + id);

        addBehaviour(new FindAverage(this, TimeUnit.SECONDS.toMillis(1)));
    }
}
