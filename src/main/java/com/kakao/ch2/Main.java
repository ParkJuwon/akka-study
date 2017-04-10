package com.kakao.ch2;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Main {

    public static void main(String[] args) {
        // 모든 액터는 어떤 액터시스템 내부에서 동작을 수행 (액터를 담는 컨테이너라고 생각)
        ActorSystem actorSystem = ActorSystem.create("TestSystem");
        ActorRef ping = actorSystem.actorOf(Props.create(PingActor.class), "pingActor");
        ping.tell("start", ActorRef.noSender());
    }
}
