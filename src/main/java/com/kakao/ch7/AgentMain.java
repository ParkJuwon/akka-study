package com.kakao.ch7;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.kakao.ch7.actor.AgentActor;

/**
 * 아카의 에이전트를 보여주기 위한 메인 클래스

 */
public class AgentMain {

	public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("TestSystem");
        ActorRef agentActor = actorSystem.actorOf(Props.create(AgentActor.class), "agentActor");
        agentActor.tell("start", ActorRef.noSender());
	}

}
