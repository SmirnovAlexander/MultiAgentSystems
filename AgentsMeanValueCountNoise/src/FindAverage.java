import jade.core.AID;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.Collection;
import java.util.Random;

public class FindAverage extends TickerBehaviour {

    private final DefaultAgent agent;
    private int currentStep;

    private int delayStep;
    private String delayContent;
    private int delayReceiverId;

    FindAverage(DefaultAgent agent, long period) {
        super(agent, period);
        this.setFixedPeriod(true);

        this.agent = agent;
        this.currentStep = 0;

        this.delayStep = -2;
        this.delayContent = "";
        this.delayReceiverId = 0;
    }

    @Override
    protected void onTick() {

        if (currentStep < App.MAX_STEPS) {

            // Printing agent info.
            System.out.println(String.format("Tick = %d, agent = %d, value = %f", getTickCount(), this.agent.getId(), this.agent.getNumber()));

            // Sending agent value to linked agents.
            if (!this.agent.getLinkedAgents().isEmpty()) {
                for (int receiver_id: this.agent.getLinkedAgents().keySet()) {

                    String content = Float.toString(this.agent.getNumber());

                    // If we lose connection we remembering all the information
                    // about message and sending it on next iteration.
                    if (new Random().nextDouble() <= this.agent.getLinkedAgents().get(receiver_id)) {
                        this.send_msg(receiver_id, content);
                    } else {
                        this.delayStep = currentStep;
                        this.delayContent = content;
                        this.delayReceiverId = receiver_id;
                    }

                    // If on previous iteration was undelivered message
                    // we send it on this iteration.
                    if (currentStep == delayStep + 1) {
                        this.send_msg(this.delayReceiverId, this.delayContent);
                    }
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
            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
            msg.setContent(content);
            AID dest = new AID(Integer.toString(id), AID.ISLOCALNAME);
            msg.addReceiver(dest);
            this.agent.send(msg);
        }
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
}