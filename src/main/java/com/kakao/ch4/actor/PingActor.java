package com.kakao.ch4.actor;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * "good"아니 "bad" 메시지를 받으면 PingActor라는 자식 액터에게 전달.
 * "done" 메시지를 받으면 화면에 결과를 출력
 * Created by john on 2017. 5. 11..
 */
public class PingActor extends UntypedActor {

	private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	private ActorRef child;

	public PingActor(){
		child = context().actorOf(Props.create(Ping1Actor.class), "ping1Actor");
	}


	public void onReceive(Object message) throws Exception {
		if(message instanceof String) {
			String msg = (String)message;
			if("good".equals(msg) || "bad".equals(msg)) {
				child.tell(msg, getSelf());
			}
			else if ("done".equals(msg)) {
				log.info("all works ars successully completed.");
			}
		}
		else {
			unhandled(message);
		}
	}
}
