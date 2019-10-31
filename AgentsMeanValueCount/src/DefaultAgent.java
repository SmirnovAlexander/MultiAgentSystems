import com.sun.org.apache.xpath.internal.operations.Bool;
import jade.core.Agent;

import java.awt.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class DefaultAgent extends Agent {

    private ArrayList<Integer> linkedAgents;
    private int number;
    private Map<Integer, Boolean> linkedAgentsState;
    private Integer id;
    private Boolean isMainFull;
    private Map<Integer,Integer> dictionary;

    @Override
    protected void setup() {

        // Initializing agent's id field.
        this.id = Integer.parseInt(getAID().getLocalName());

        this.isMainFull = false;
        this.dictionary = new HashMap<>();


        // Initializing array with id's of linked agents.
        linkedAgents = new ArrayList<>();
        linkedAgentsState = new HashMap<>();

        ArrayList<Integer> row = App.ADJ_MATRIX.get(this.id - 1);
        for (int i = 0;i<row.size();i++){
            if(row.get(i) == 1) {
                linkedAgents.add(i + 1);
                linkedAgentsState.put(i+1, false);
            }

        }

        // Generating random number to an agent.
        Random x = new Random();
        this.number =  x.nextInt(10);;

        this.dictionary.put(this.id, this.number);

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

    public boolean isMain(){
        return (this.id == App.MAIN_AGENT_ID);

    }

    public Boolean getMainFull() {
        return isMainFull;
    }

    public void setMainFull(Boolean mainFull) {
        isMainFull = mainFull;
    }

    public Map<Integer, Integer> getDictionary() {
        return dictionary;
    }

    public void setDictionary(Map<Integer, Integer> dictionary) {
        this.dictionary = dictionary;
    }

    public Map<Integer, Boolean> getLinkedAgentsState() {
        return linkedAgentsState;
    }

    public void setLinkedAgentsState(Map<Integer, Boolean> linkedAgentsState) {
        this.linkedAgentsState = linkedAgentsState;
    }
}