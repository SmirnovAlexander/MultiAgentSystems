import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

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
        block();
    }

    // Replying to all messages.
    public void replyToMessage(ACLMessage msg) {

        // Getting information from message.
        String content = msg.getContent();
        String sender_name = msg.getSender().getName();
        int sender_id = Integer.parseInt(sender_name.substring(0, sender_name.indexOf("@")));

        // Replying to message.
        // ToDo: reply to message.
        System.out.println(ANSI_GREEN + String.format("Agent %d received message: <%s> from agent %d.", this.agent.getId(), content, sender_id) + ANSI_RESET);
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
}
