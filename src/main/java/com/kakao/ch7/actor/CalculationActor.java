package com.kakao.ch7.actor;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * Created by john on 2017. 5. 18..
 */
public class CalculationActor extends UntypedActor {
	private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

	public void onReceive(Object message) throws Exception {
		if(message instanceof Integer) {
			Integer msg = (Integer) message;
			log.info("CalculationActor received {}", msg);
			work(msg);
			getSender().tell(msg * 2, getSelf());
		}
	}

	private void work(Integer n) throws Exception {
		log.info("CalculationActor working on " + n);
		Thread.sleep(1000);
		log.info("CalculationActor completed " + n);
	}
}
