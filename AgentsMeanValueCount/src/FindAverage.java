import jade.core.AID;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;
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

            if (this.agent.getMainFull() && !this.agent.getLinkedAgentsState().containsValue(false)) {
                System.out.println(ANSI_GREEN + getMeanValue() + ANSI_RESET);
                this.stop();
            }

            // Printing agent info.
            System.out.println(String.format("Agent %d: tick = %d", this.agent.getId(), getTickCount()));


            // Sending messages to agents.
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

    // Sending message to an agent by id.
    private void send_msg(int id, String content) {
        if (this.agent.getId() != id) {
            ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
            msg.setContent(content);
            AID dest = new AID(Integer.toString(id), AID.ISLOCALNAME);
            msg.addReceiver(dest);
            this.agent.send(msg);
        }
    }

    public float getMeanValue() {

        Collection<Integer> values = this.agent.getDictionary().values();

        int sum = 0;
        for (int value: values) {
            sum += value;
        }

        return ((float) sum / values.size());
    }



    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
}