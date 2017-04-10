package com.kakao.ch2;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Main {

	public static void main(String[] args) {
		// 모든 액터는 어떤 액터시스템 내부에서 동작을 수행 (액터를 담는 컨테이너라고 생각)
		ActorSystem actorSystem = ActorSystem.create("TestSystem"); // 액터 시스템의 이름


		/**
		 * 액터를 만드는 방법 (두개의 정보가 필요 : Props, 액터의 이름)
		 * Actor의 Name은 항상 unique 해야됨 (UUID.randomUUID(); 랜덤하게 생성해 줄수도 있음)
		 * 모든 액터는 ActorRef라는 타입에 의해서만 접근될 수 있다. 액터 객체를 둘러싸고 있는 껍
		 */
		ActorRef ping = actorSystem.actorOf(Props.create(PingActor.class), "pingActor");

		/**
		 * ActorRef의 tell 메서드는 다음과 같은 두 개의 값을 인수로 받아드린다.
		 * - 메시지(message) : UntypedActor 일시 tell을 통해서 어떠한 객체 타입을 보낼 수 있음
		 * - 발신자(sender) : 발신자는 어떠한 액터다. 보통 다음과 같은 네가지 형태의 값이 사용
		 * 	> getSelf() : 자기 자신의 ActorRef 객체를 리턴. 액터 자신을 발신자로 정할 때 사용.
		 * 	> getSender() : 이것은 현재 처리중인 메시지를 보내온 발신인의 주소를 다음 액터에게 그대로 포워드 할 떄 사용.
		 * 	> 어떤 특정 액터를 가리키는 ActorRef 객체가 있다면 그것을 발신자로 정할 수도 있다. 실제 발신인의 주소를 다른 액터의 주소로 변경한다는 점에서 getSender()와 개념적으로 비슷
		 * 	> ActorRef.noSender() : 이 메서드는 개념적으로 null에 해당하는 액터 주소를 리턴 발신인 주소가 아무 의미가 없는 경우에 사
 		 */
		ping.tell("start", ActorRef.noSender());
	}
}
