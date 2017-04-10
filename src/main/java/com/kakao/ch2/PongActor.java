package com.kakao.ch2;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class PongActor extends UntypedActor {
    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    private ActorRef ping;

    // pingActor의 getSelf 인자를 ActorRef로 받는다.
    public PongActor(ActorRef ping) {
        this.ping = ping;
    }

    public void onReceive(Object message) throws Exception {
        if(message instanceof String) {
            String msg = (String) message;
            log.info("Pong received {}", msg);
            ping.tell("pong", getSelf());
            Thread.sleep(1000); // 실전에선 금기다.
            // 액터의 철학은 기본적으로 모든 것이 비동기적이고, 어느 것도 중단(Blocking) 되지 않는다는 데 있다.
        }

    }
}
