package com.kakao.ch2;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class PongActor extends UntypedActor {
    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    private ActorRef ping;

    public PongActor(ActorRef ping) {
        this.ping = ping;
    }

    public void onReceive(Object message) throws Exception {
        if(message instanceof String) {
            String msg = (String) message;
            log.info("Pong received {}", msg);
            ping.tell("pong", getSelf());
            Thread.sleep(1000); // 실전에선 놉
        }

    }
}
