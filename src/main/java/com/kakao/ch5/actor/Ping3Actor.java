package com.kakao.ch5.actor;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * Created by john on 2017. 5. 12..
 */
public class Ping3Actor extends UntypedActor {

	private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

	public void onReceive(Object message) throws Exception {
		if (message instanceof String) {
			String msg = (String) message;
			log.info("Ping3 received {}", msg);
			work();
			getSender().tell("done", getSelf());
		}
	}

	private void work() throws Exception {
		Thread.sleep(1000); // 실전에서는 절대 금물!!!
		log.info("Ping3 working...");
	}
}
