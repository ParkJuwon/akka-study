package com.kakao.ch4.actor;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import scala.Option;

/**
 * Created by john on 2017. 5. 11..
 */
public class Ping3Actor extends UntypedActor {

	private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

	public Ping3Actor() {
		log.info("Ping3Actor constructor");
	}

	@Override
	public void preRestart(Throwable reason, Option<Object> message) throws Exception {
		log.info("Ping3Actor preRestart..");
	}

	@Override
	public void postRestart(Throwable reason) throws Exception {
		log.info("Ping3Actor postRestart..");
	}

	@Override
	public void postStop() throws Exception {
		log.info("Ping3Actor postStop..");
	}

	public void onReceive(Object message) throws Exception {
		if(message instanceof String) {
			String msg = (String)message;
			if("good".equals(msg)) {
				goodWork();
				getSender().tell("done", getSelf());
			}
			else if("bad".equals(msg)) {
				badWork();
			}
		} else {
			unhandled(message);
		}

	}

	private void goodWork() throws Exception {
		log.info("Ping3Actor is good");
	}

	/** 일부러 NullPointerException 을 발생시킨다 **/
	private void badWork() throws Exception {
		throw new NullPointerException();
	}


}
