package com.sist.web;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.util.*;
public class ChatHandler extends TextWebSocketHandler{
    //1. 접속자의 정보를 저장 
	private Map<String,WebSocketSession> users=new HashMap<String,WebSocketSession>();
	// 입장할때 자동 호출 ==> JavaScript => open
	// 접속자의 정보를 저장 WebSocketSession session (IP , PORT) => 브라우저마다 생성 
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println(session.getId()+"님 입장하셨습니다!!");// session.getId()=>primary (절대 중복이 없다)
		users.put(session.getId(), session);
	}

	// 접속한 모든 사람에게 메세지 전송 
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		for(WebSocketSession ws:users.values())// 접속해서 저장되어 있는 모든 사람 (users)
		{
			ws.sendMessage(message);
		}
	}

	// 퇴장시에 자동 호출  ==> JavaScript => close (WebSocket => 클래스)
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println(session.getId()+"님 퇴장하셨습니다!!");
		users.remove(session.getId());
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(exception.getMessage());// 에러 메세지 출력 
		super.handleTransportError(session, exception);
	}
	
	
   
}
