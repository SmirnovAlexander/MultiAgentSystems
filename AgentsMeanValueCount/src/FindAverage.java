import jade.core.AID;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

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
            System.out.println(String.format("Agent %d: tick = %d", this.agent.getId(), getTickCount()));

            // Sending messages to agents.
            if (!this.agent.getLinkedAgents().isEmpty()) {
                for (int receiver_id: this.agent.getLinkedAgents()) {
                    String content = String.format("Hello to agent %d from agent %d", receiver_id, this.agent.getId());
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
            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
            msg.setContent(content);
            AID dest = new AID(Integer.toString(id), AID.ISLOCALNAME);
            msg.addReceiver(dest);
            this.agent.send(msg);
        }
    }
}