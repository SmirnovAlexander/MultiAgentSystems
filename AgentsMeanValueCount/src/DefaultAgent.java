import jade.core.Agent;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class DefaultAgent extends Agent {

    private int id;
    private boolean isMain;                           // If this agent is main agent.
    private ArrayList<Integer> linkedAgents;          // List with id's of linked agents.
    private Map<Integer, Boolean> linkedAgentsState;  // List with states (isFull) of linked agents.
    private int number;                               // Number that agent stores.
    private boolean isHaveInfoAboutAllAgents;         // If agent has collected info about all other agents.
    private Map<Integer,Integer> agentsInfo;          // Information about other agents id's and their numbers.

    @Override
    protected void setup() {

        this.id = Integer.parseInt(getAID().getLocalName());

        // Main agent will be agent with id = 1.
        this.isMain = this.id == 1;

        linkedAgents = new ArrayList<>();
        linkedAgentsState = new HashMap<>();
        ArrayList<Integer> row = App.ADJ_MATRIX.get(this.id - 1);
        for (int i = 0; i < row.size(); i++){
            if(row.get(i) == 1) {
                linkedAgents.add(i + 1);
                linkedAgentsState.put(i + 1, false);
            }
        }

        // Generating random number to an agent.
        Random x = new Random();
        this.number =  x.nextInt(10);;

        this.isHaveInfoAboutAllAgents = false;

        // Putting information about itself in agentsInfo.
        this.agentsInfo = new HashMap<>();
        this.agentsInfo.put(this.id, this.number);

        System.out.println(String.format("Agent %d have number %d and connected to agents %s", this.getId(), this.number, this.getLinkedAgents()));
        System.out.println("----------------------------------------------------------------");

        // Adding behaviours.
        addBehaviour(new FindAverage(this, TimeUnit.SECONDS.toMillis(1)));
        addBehaviour(new Receiver(this));
    }

    public Integer getId() {
        return id;
    }

    public ArrayList<Integer> getLinkedAgents() {
        return linkedAgents;
    }

    public boolean getIsMain() {
        return isMain;
    }

    public Boolean getIsHaveInfoAboutAllAgents() {
        return isHaveInfoAboutAllAgents;
    }

    public void setIsHaveInfoAboutAllAgents(Boolean haveInfoAboutAllAgents) {
        isHaveInfoAboutAllAgents = haveInfoAboutAllAgents;
    }

    public Map<Integer, Integer> getAgentsInfo() {
        return agentsInfo;
    }

    public void setAgentsInfo(Map<Integer, Integer> agentsInfo) {
        this.agentsInfo = agentsInfo;
    }

    public Map<Integer, Boolean> getLinkedAgentsState() {
        return linkedAgentsState;
    }

    public void setLinkedAgentsState(Map<Integer, Boolean> linkedAgentsState) {
        this.linkedAgentsState = linkedAgentsState;
    }
}