package com.kakao.ch4.actor;

import akka.actor.*;
import akka.actor.SupervisorStrategy.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Function;
import scala.concurrent.duration.Duration;

import static akka.actor.SupervisorStrategy.*;
/**
 * 자식 액터들을 감시하기 위한 전략을 선언하는 액터
 * Created by john on 2017. 5. 11..
 */
public class Ping1Actor extends UntypedActor {

	private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	private ActorRef child1;
	private ActorRef child2;

	public Ping1Actor() {
		child1 = context().actorOf(Props.create(Ping2Actor.class), "ping2Actor");
		child2 = context().actorOf(Props.create(Ping3Actor.class), "ping3Actor");
	}

	public void onReceive(Object message) throws Exception {
		if(message instanceof String) {
			String msg = (String) message;
			if("good".equals(msg) || "bad".equals(msg)) {
				log.info("Ping1Actor received {}", msg);
				child1.tell(msg, getSender());
				child2.tell(msg, getSender());
			}
		} else {
			unhandled(message);
		}
	}

	private static SupervisorStrategy strategy = new OneForOneStrategy(10, Duration.create("1 minute"),
			new Function<Throwable, Directive>() {
				public Directive apply(Throwable t) throws Exception {
					if(t instanceof ArithmeticException) {
						// Ping2Actor는 "bad" 메시지를 받으면 ArithmeticException을 발생
						return resume();
					} else if (t instanceof NullPointerException) {
						// Ping3Actor는 "bad" 메시지를 받으면 NullPointerException을 발생
						return restart();
					} else {
						return escalate();
					}
				}
			});

	@Override
	public SupervisorStrategy supervisorStrategy() {
		return strategy;
	}
}
