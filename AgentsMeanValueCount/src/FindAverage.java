import jade.core.AID;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.Collection;

public class FindAverage extends TickerBehaviour {

    private final DefaultAgent agent;
    private int currentStep;

    FindAverage(DefaultAgent agent, long period) {
        super(agent, period);
        this.setFixedPeriod(true);
        this.agent = agent;
        this.currentStep = 0;
    }

    @Override
    protected void onTick() {

        if (currentStep < App.MAX_STEPS) {

            // Printing agent info.
            System.out.println(String.format("Tick = %d, agent %d: ", getTickCount(), this.agent.getId()));

            // Terminating agent if he had collected info about other agents and all it's neighbours also collected all information.
            if (this.agent.getIsHaveInfoAboutAllAgents() && !this.agent.getLinkedAgentsState().containsValue(false)) {

                // Sending answer to server only from main agent.
                if (this.agent.getIsMain()) {
                    System.out.println(String.format("The mean value of agents is: %f", getMeanValue()));
                    System.out.println("----------------------------------------------------------------");
                }

                this.stop();
            }

            // Sending request messages to linked agents.
            if (!this.agent.getLinkedAgents().isEmpty()) {
                for (int receiver_id: this.agent.getLinkedAgents()) {
                    String content = String.format("Agent %d requesting isMapFull and HashMap from agent %d.", this.agent.getId(), receiver_id);
                    this.send_msg(receiver_id, content);
                }
            }

            this.currentStep++;
        } else {
            this.stop();
        }
    }

    // Sending request message to an agent by id.
    private void send_msg(int id, String content) {
        if (this.agent.getId() != id) {
            ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
            msg.setContent(content);
            AID dest = new AID(Integer.toString(id), AID.ISLOCALNAME);
            msg.addReceiver(dest);
            this.agent.send(msg);
        }
    }

    // Counting mean value of values from agentsInfo dictionary.
    public float getMeanValue() {
        Collection<Integer> values = this.agent.getAgentsInfo().values();
        int sum = 0;
        for (int value: values) {
            sum += value;
        }
        return ((float) sum / values.size());
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
}