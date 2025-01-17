# Multi-Agent Systems

This repo contains all homeworks from ["Multi-Agent Systems" course](https://github.com/SmirnovAlexander/MultiAgentSystems/tree/master/materials).

Below are listed all tasks with links and descriptions.


## Homework 1. 
15.10.19

[Platforms for development multi-agent systems](https://github.com/SmirnovAlexander/MultiAgentSystems/blob/master/materials/MT_lec04.pdf)

[JADE installation](https://github.com/SmirnovAlexander/MultiAgentSystems/blob/master/materials/Установка%20и%20работа%20с%20JADE.pdf)

[JADE Programming Tutorial](https://jade.tilab.com/doc/tutorials/JADEProgramming-Tutorial-for-beginners.pdf)

[JADE Tutorials & Guides](https://jade.tilab.com/documentation/tutorials-guides/)

- [1.1](/AgentsMeanValueCount)

   You're given 10 agents. Each agent randomly connected to other agents. Each agent proposes a number. Agents are able to communicate only with neighbours. Program should count a mean value. For example: 
   - Each agent may contain dictionary with other's agents id's, and program stops after point where each agent have information about other agents. 


## Homework 2. 
05.11.19

[Multi-Agent management in dynamic network](https://github.com/SmirnovAlexander/MultiAgentSystems/blob/master/materials/MT_lec06.pdf)

[Graph theory. Consensus in dynamic networks](https://github.com/SmirnovAlexander/MultiAgentSystems/blob/master/materials/MT_lec07.pdf)
   
 - [2.1](/AgentsMeanValueCountNoise)
 
   You're given 10 agents. Each agent randomly connected to other agents. Each agent proposes a number. Agents are able to communicate only with neighbours. Agents may send data with mistakes and with a random delay (information reaches target on the next tick). Program should count a mean value in a way:
      - If graph is balanced, sum and substract values from nearest agent so after some iteration each agent will have mean value in itself.
