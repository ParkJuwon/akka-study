package com.kakao.ch2;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class PingActor extends UntypedActor {
    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    private ActorRef pong;


	/**
	 * 모든 액터는 다음과 같은 라이프사이클을 가지고 있다
	 * - 생성(create)
	 * - 재시작(restart)
	 * - 멈춤(stop)
	 *
	 * 모든 액터는 라이프사이클과 관련된 메서드로서 다음과 같은 메서드를 가지고 있다.
	 * preStart : 생성자가 완료된 직후에 실행.
	 * preStop : 액터의 라이프사이클이 완전히 종료하기 직전에 실행.
	 * preRestart : 재시작이 이루어지기 직전에 실행.
	 * postRestart : 재시작이 이루어진 직후에 실행.
	 */


	@Override
    public void preStart() throws Exception {
        // pongAcor constructor 에게 전달
        this.pong = context().actorOf(Props.create(PongActor.class, getSelf()), "pongActor");
    }

    public void onReceive(Object message) throws Exception {
        if(message instanceof String) {
            String msg = (String) message;
            log.info("Ping received {}", msg);
            pong.tell("ping", getSelf());
        }

    }
}
