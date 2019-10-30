import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
public class DefaultAgent extends Agent {

    private int[] linkedAgents;
    // ToDO: Initialize linkedAgents from adjacency matrix.

    private float number;
    private Integer id;

    @Override
    protected void setup() {

        int id = Integer.parseInt(getAID().getLocalName());
        this.id = id;

        // Generating random number to an agent.
        Random x = new Random();
        this.number =  x.nextInt(10);;

        addBehaviour(new FindAverage(this, TimeUnit.SECONDS.toMillis(1)));
        addBehaviour(new Receiver(this));
    }

    public float getNumber() {
        return number;
    }
    public Integer getId() {
        return id;
    }
}