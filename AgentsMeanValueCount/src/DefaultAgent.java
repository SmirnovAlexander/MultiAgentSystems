import jade.core.Agent;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class DefaultAgent extends Agent {

    private ArrayList<Integer> linkedAgents;
    private int number;
    private Integer id;

    @Override
    protected void setup() {

        // Initializing agent's id field.
        this.id = Integer.parseInt(getAID().getLocalName());

        // Initializing array with id's of linked agents.
        linkedAgents = new ArrayList<>();
        ArrayList<Integer> row = App.ADJ_MATRIX.get(this.id - 1);
        for (int i = 0;i<row.size();i++){
            if(row.get(i) == 1) {
                linkedAgents.add(i + 1);
            }
        }

        // Generating random number to an agent.
        Random x = new Random();
        this.number =  x.nextInt(10);;

        System.out.println(String.format("Agent %d have number %d and connected to agents %s", this.getId(), this.number, this.getLinkedAgents()));
        System.out.println("----------------------------------------------------------------");

        // Adding behaviours.
        addBehaviour(new FindAverage(this, TimeUnit.SECONDS.toMillis(1)));
        addBehaviour(new Receiver(this));
    }

    public float getNumber() {
        return number;
    }
    public Integer getId() {
        return id;
    }
    public ArrayList<Integer> getLinkedAgents() {
        return linkedAgents;
    }
}