import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.*;

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

        // Replying to message.

        if (msg.getPerformative() == ACLMessage.REQUEST) {
            System.out.println(String.format("Agent with ID %d received REQUEST message from Agent with ID %d", this.agent.getId(), sender_id));

            ACLMessage reply = msg.createReply();
            reply.setPerformative(ACLMessage.INFORM);
            reply.setContent(String.format("%b@%s", this.agent.getMainFull(), this.agent.getDictionary().toString()));
            System.out.println(String.format("%b@%s", this.agent.getMainFull(), this.agent.getDictionary().toString()));
            this.agent.send(reply);

        }

        if (msg.getPerformative() == ACLMessage.INFORM) {

            System.out.println(String.format("Agent with ID %d received INFORM message from Agent with ID %d", this.agent.getId(), sender_id));
            // Получили информирование ("bool@Dict")
            String[] msgContent = msg.getContent().split("@");

            // Словарь в стринге
            String dict = msgContent[1];


            boolean isMainFull = Boolean.parseBoolean(msgContent[0]);

            // Изменяем в словаре состояний соседей состояние соседа, если его состояние true
            if (isMainFull) {
                Map<Integer, Boolean> newLinkedAgentsState = this.agent.getLinkedAgentsState();

                newLinkedAgentsState.put(sender_id, isMainFull);
                this.agent.setLinkedAgentsState(newLinkedAgentsState);
            }

            System.out.println(this.agent.getLinkedAgentsState());

            dict = dict.substring(1, dict.length()-1);           //remove curly brackets
            String[] keyValuePairs = dict.split(",");              //split the string to creat key-value pairs
            Map<Integer,Integer> map = new HashMap<>();

            for(String pair : keyValuePairs)                        //iterate over the pairs
            {
                String[] entry = pair.split("=");                   //split the pairs to get key and value
                map.put(Integer.parseInt(entry[0].trim()), Integer.parseInt(entry[1].trim()));          //add them to the hashmap and trim whitespaces
            }

            // Ставим значение текущему агенту isMainFull true
            if (isMainFull) {
                this.agent.setMainFull(isMainFull);
            }
            // Если нечего мерджить и мы главный агент, то ставим себе isMainFull = true
            if (this.agent.getDictionary().equals(map)) {

                if (this.agent.isMain()) {

                    this.agent.setMainFull(true);
                }
            }

            // Merge received map with agent's map
            Map<Integer,Integer> agentMap;
            agentMap = this.agent.getDictionary();

            map.keySet().removeAll(agentMap.keySet());
            agentMap.putAll(map);



            this.agent.setDictionary(agentMap);

        }


        //System.out.println(ANSI_GREEN + String.format("Agent %d received message: <%s> from agent %d.", this.agent.getId(), content, sender_id) + ANSI_RESET);
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";


}
