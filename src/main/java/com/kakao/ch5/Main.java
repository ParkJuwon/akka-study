package com.kakao.ch5;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.kakao.ch5.actor.PingActor;

/**
 * 아카의 상태기계를 보여주기 위한 메인 클래스
 * Created by john on 2017. 5. 12..
 */
public class Main {
	public static void main(String[] args) {
		ActorSystem actorSystem = ActorSystem.create("TestSystem");
		ActorRef ping = actorSystem.actorOf(Props.create(PingActor.class), "pingActor");

		ping.tell("work", ActorRef.noSender());
		ping.tell("reset", ActorRef.noSender());
	}
}
