import jade.core.Agent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class DefaultAgent extends Agent {

    private int id;
    private HashMap<Integer, Float> linkedAgents;       // Storing connected agent's with probability of sending message.
    private float number;                               // Number that agent stores.

    @Override
    protected void setup() {

        this.id = Integer.parseInt(getAID().getLocalName());

        // Initializing connection HashMap.
        linkedAgents = new HashMap<>();
        ArrayList<Float> row = App.ADJ_MATRIX.get(this.id - 1);
        for (int i = 0; i < row.size(); i++){
            float connection = row.get(i);
            if(connection > 0.01f) {
                linkedAgents.put(i + 1, connection);
            }
        }

        // Generating random number to an agent.
        Random x = new Random();
        this.number =  x.nextInt(10);;

        System.out.println(String.format("Agent %d have number %f and connected to agents %s", this.getId(), this.number, this.getLinkedAgents()));
        System.out.println("----------------------------------------------------------------");

        // Adding behaviours.
        addBehaviour(new FindAverage(this, TimeUnit.SECONDS.toMillis(1)));
        addBehaviour(new Receiver(this));
    }

    public Integer getId() {
        return id;
    }

    public HashMap<Integer, Float> getLinkedAgents() {
        return linkedAgents;
    }

    public float getNumber() {
        return number;
    }

    public void setNumber(float number) {
        this.number = number;
    }
}