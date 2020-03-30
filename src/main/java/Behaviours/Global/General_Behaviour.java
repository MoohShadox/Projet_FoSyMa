package Behaviours.Global;

import Agents.Simple_Cognitif_Agent;
import Behaviours.Communication.*;
import Behaviours.Exploration_Behaviour;
import Behaviours.followWumpusBehaviour;
import jade.core.behaviours.FSMBehaviour;

public class General_Behaviour extends FSMBehaviour {

    private Simple_Cognitif_Agent myAgent;
    public movingBehaviour movingBehaviour;
    public Exploration_Behaviour exploration_behaviour;
    public callingBehaviour callingBehaviour;
    //public Conversation conversationBehaivour;
    public conversationBehaviour conversationBehaviour;
    public mapExchanging mapExchanging;
    public conversationMode conversationMode;

    public perceptionExchanging perceptionExchanging;
    public followWumpusBehaviour followWumpusBehaviour;

    public huntingBehaviour huntingBehaviour;

    public General_Behaviour(Simple_Cognitif_Agent myAgent){
        super(myAgent);
        this.myAgent =  myAgent;
        //Création des behaviours
        exploration_behaviour = new Exploration_Behaviour(myAgent,myAgent.Kb);
        callingBehaviour = new callingBehaviour(myAgent);
        movingBehaviour = new movingBehaviour(myAgent,exploration_behaviour,callingBehaviour);

        conversationMode = new conversationMode(this.myAgent);
        perceptionExchanging = new perceptionExchanging(this.myAgent);

        mapExchanging = new mapExchanging(this.myAgent);
        conversationBehaviour = new conversationBehaviour(myAgent,mapExchanging,conversationMode,perceptionExchanging);


        followWumpusBehaviour = new followWumpusBehaviour(this.myAgent);

        huntingBehaviour = new huntingBehaviour(this.myAgent,followWumpusBehaviour,perceptionExchanging,conversationMode);
        //Variables de réinitialisation
        String [] moving = {"MOVING"} ;
        String [] conversation = {"CONVERSATION"} ;
        String [] hunting = {"HUNTING"} ;

        //Création des états
        registerFirstState(movingBehaviour,"MOVING");
        registerState(conversationBehaviour,"CONVERSATION");
        registerState(huntingBehaviour,"HUNTING");

        registerTransition("MOVING","MOVING",0,moving);
        registerTransition("MOVING","CONVERSATION",1,conversation);
        registerDefaultTransition("MOVING","HUNTING",hunting);


        registerTransition("CONVERSATION","MOVING",1,moving);

        //registerDefaultTransition("HUNTING","MOVING",moving);

        registerDefaultTransition("CONVERSATION","MOVING",moving);



    }


}
