import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Receiver extends CyclicBehaviour
{
    private final DefaultAgent agent;

    Receiver(DefaultAgent agent) {
        super(agent);
        this.agent = agent;
    }

    @Override
    public void action() {
        ACLMessage msg = this.agent.receive();
        if (msg!=null)
            replyToMessage(msg);
        else {
            block();
        }
    }

    // Replying to all messages.
    public void replyToMessage(ACLMessage msg) {

        // Getting information from message.
        String content = msg.getContent();
        String sender_name = msg.getSender().getName();
        int sender_id = Integer.parseInt(sender_name.substring(0, sender_name.indexOf("@")));

        // Replying to info message (value) with delta.
        if (msg.getPerformative() == ACLMessage.INFORM) {

            ACLMessage reply = msg.createReply();
            reply.setPerformative(ACLMessage.CONFIRM);

            // Number that we got from linked agent.
            float valueNeighbour = Float.parseFloat(content);

            // Number that we will add to our number.
            float delta = (valueNeighbour - this.agent.getNumber()) / App.NUMBER_OF_AGENTS;

            // Setting new value to an agent.
            float newValue = this.agent.getNumber() + delta;
            this.agent.setNumber(newValue);

            // Noise (MC) that we send with delta.
            double randomNoise = App.NOISE_MIN + (App.NOISE_MAX - App.NOISE_MIN) * new Random().nextDouble();

            reply.setContent(String.format("%f", -delta + randomNoise));

            System.out.println(ANSI_YELLOW + String.format("Agent with ID %d received REQUEST message from agent with ID %d", this.agent.getId(), sender_id) + ANSI_RESET);
            System.out.println(ANSI_YELLOW + String.format("Sending: %f from agent %d to agent %d", -delta + randomNoise, this.agent.getId(), sender_id) + ANSI_RESET);

            this.agent.send(reply);
        }

        // Receiving delta that we should sum with our number.
        if (msg.getPerformative() == ACLMessage.CONFIRM) {

            System.out.println(ANSI_CYAN + String.format("Agent with ID %d received INFORM message from Agent with ID %d", this.agent.getId(), sender_id) + ANSI_RESET);

            // Getting delta from received message.
            float delta = Float.parseFloat(content);

            // Setting new value to an agent.
            float newValue = this.agent.getNumber() + delta;
            this.agent.setNumber(newValue);
        }
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[36m";
}
