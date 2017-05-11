package com.kakao.ch3;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * 계층구조 트리의 최하위 leaf에 해당하는 액터
 * Created by john on 2017. 4. 11..
 */
public class Ping2Actor extends UntypedActor {

	private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

	public void onReceive(Object message) throws Exception {
		if(message instanceof String) {
			String msg = (String)message;
			if("work".equals(msg)) {
				log.info("Ping2 received {}", msg);
				work();
				getSender().tell("done", getSelf());
			}
		}
	}

	private void work() throws Exception {
		Thread.sleep(1000); // 실전에서는 절대 금물!
		log.info("Ping2 working...");
	}
}
