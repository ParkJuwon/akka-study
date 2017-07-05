package com.kakao.ch7.actor;

import akka.actor.UntypedActor;
import akka.dispatch.ExecutionContexts;
import akka.dispatch.Mapper;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import scala.concurrent.ExecutionContext;
import akka.agent.Agent;

/**
 * 아카의 에이전트가 동작하는 방식을 보여주는 액터
 * Created by john on 2017. 7. 5..
 */
public class AgentActor extends UntypedActor {

	private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

	public void onReceive(Object message) throws Exception {
		ExecutionContext ec = ExecutionContexts.global();
		Agent<Integer> agent = Agent.create(5, ec);

		agent.send(new Mapper<Integer, Integer>() {
			public Integer apply(Integer i) {
				return i * 2;
			}
		});

//		Thread.sleep(100); // 밑에 아닐수도 라도 적인 이유가 비동기 적으로 send 에서 선 작업이 일어나게되면.. 그럴수도 있기 때문이다.

		// 에이전트의 값이 여전히 5로 출력될 것이다 (아닐 수도)
		log.info("Current agent value = " + agent.get());
		Thread.sleep(100); // 일부러 조금 기다려 본다
		// 에이전트의 값이 10으로 호출될 것이다 (아닐 수도)
		log.info("Current agent value = {}", agent.get());

	}
}
