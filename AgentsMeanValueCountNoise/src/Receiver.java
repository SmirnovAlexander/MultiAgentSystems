import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.HashMap;
import java.util.Map;

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

        // Replying to request message with IsHaveInfoAboutAllAgents and agentsInfo.
        if (msg.getPerformative() == ACLMessage.REQUEST) {

            ACLMessage reply = msg.createReply();
            reply.setPerformative(ACLMessage.INFORM);
            reply.setContent(String.format("%b@%s", this.agent.getIsHaveInfoAboutAllAgents(), this.agent.getAgentsInfo().toString()));

            System.out.println(ANSI_YELLOW + String.format("Agent with ID %d received REQUEST message from agent with ID %d", this.agent.getId(), sender_id) + ANSI_RESET);
            System.out.println(ANSI_YELLOW + String.format("Sending: %b@%s from agent %d to agent %d", this.agent.getIsHaveInfoAboutAllAgents(), this.agent.getAgentsInfo().toString(), this.agent.getId(), sender_id) + ANSI_RESET);

            this.agent.send(reply);
        }

        // Parsing and applying information.
        if (msg.getPerformative() == ACLMessage.INFORM) {

            System.out.println(ANSI_CYAN + String.format("Agent with ID %d received INFORM message from Agent with ID %d", this.agent.getId(), sender_id) + ANSI_RESET);

            // Getting info in format ("bool@Dict").
            String[] msgContent = msg.getContent().split("@");
            boolean isNeighbourHaveInfoAboutAllAgents = Boolean.parseBoolean(msgContent[0]);
            Map<Integer,Integer> neighbourMap = getMapFromString(msgContent[1]);

            // If our neighbour have information about all agents, we change it's state in our LinkedAgentsState dictionary.
            if (isNeighbourHaveInfoAboutAllAgents) {
                Map<Integer, Boolean> newLinkedAgentsState = this.agent.getLinkedAgentsState();
                newLinkedAgentsState.put(sender_id, true);
                this.agent.setLinkedAgentsState(newLinkedAgentsState);
            }

            // If our agentInfo map equals to neighbours agentInfo map, that means
            // that we collected information about all agents.
            if (this.agent.getAgentsInfo().equals(neighbourMap)) {
                this.agent.setIsHaveInfoAboutAllAgents(true);
            } else {

                // Merging received map with our's agent's map.
                Map<Integer,Integer> mergedAgentsInfo;
                mergedAgentsInfo = this.agent.getAgentsInfo();
                neighbourMap.keySet().removeAll(mergedAgentsInfo.keySet());
                mergedAgentsInfo.putAll(neighbourMap);

                this.agent.setAgentsInfo(mergedAgentsInfo);
            }

            // If our neighbour have information about all agents, that means that after merging
            // information we will also have information about all agents.
            if (isNeighbourHaveInfoAboutAllAgents) {
                this.agent.setIsHaveInfoAboutAllAgents(true);
            }
        }
    }

    // Parsing string map into map.
    private Map<Integer,Integer> getMapFromString(String str) {
        str = str.substring(1, str.length()-1);                                              // Removing curly brackets.
        String[] keyValuePairs = str.split(",");                                                // Splitting string to create key-value pairs.

        Map<Integer,Integer> map = new HashMap<>();
        for(String pair : keyValuePairs) {
            String[] entry = pair.split("=");                                                   // Splitting pairs to get key, value.
            map.put(Integer.parseInt(entry[0].trim()), Integer.parseInt(entry[1].trim()));         // Adding them to the amp and trimming whitespaces.
        }
        return map;
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
}
