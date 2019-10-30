import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;

public class FindAverage extends TickerBehaviour {

    private final DefaultAgent agent;
    private int currentStep;
    private final int MAX_STEPS = 5;

    FindAverage(DefaultAgent agent, long period) {
        super(agent, period);
        this.setFixedPeriod(true);
        this.agent = agent;
        this.currentStep = 0;
    }

    @Override
    protected void onTick() {

        if (currentStep < MAX_STEPS) {

            // Printing agent info.
            System.out.println("Agent " + this.agent.getId() + ": tick = " + getTickCount() + ", number = " + this.agent.getNumber());

            // ToDo: send messages to all connected agents (agents have field "linkedAgents").
            // Sending message to agent.
            int receiver_id = 3;
            String content = String.format("Hello to agent %d from agent %d", receiver_id, this.agent.getId());
            this.send_msg(receiver_id, content);

            System.out.println("------------------------------------------------------------------------------------------------");
            this.currentStep++;
        } else {
            this.stop();
        }
    }

    // Sending message to an agent by id.
    private void send_msg(int id, String content) {
        if (this.agent.getId() != id) {
            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
            msg.setContent(content);
            AID dest = new AID(Integer.toString(id), AID.ISLOCALNAME);
            msg.addReceiver(dest);
            this.agent.send(msg);
        }
    }
}