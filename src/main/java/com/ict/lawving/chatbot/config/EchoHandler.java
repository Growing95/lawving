package com.ict.lawving.chatbot.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class EchoHandler extends TextWebSocketHandler {
	
	
	  private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	 
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessionList.add(session);
		session.sendMessage(new TextMessage("<strong>환영합니다. 무엇을 도와드릴까요?</strong><br>"
				+ "<strong>예시> 회원가입, 북마크기능 </strong>"));
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
			session.sendMessage(new TextMessage("사용자 :"+message.getPayload()));
			if (message.getPayload().equals("회원가입")) {
				session.sendMessage(new TextMessage("챗봇 : "+"<strong>"+"회원가입은 메인페이지의 우측 상단에 로그인|회원가입 의 버튼을 이용하면 됩니다."+"</strong><br>"));
			}else if(message.getPayload().equals("북마크")){
				session.sendMessage(new TextMessage("챗봇 : "+"<strong>"+"북마크 기능은 검색된 데이터 중에서 '저장' 버튼을 누르면"+
								"회원님의 MY페이지안에 북마크리스트에 추가되는 기능입니다."+"</strong><br>"));
			}else if(message.getPayload().equals("글쓰기")){
				session.sendMessage(new TextMessage("챗봇 : "+"<strong>"+"회원님은 QnA게시판에 문의작성을 이용하실 수 있으며 비로그인상태에서는 사용이 불가능합니다."+
						"또한 카카오로그인을 한 사용자는 사용할 수 없는 기능입니다."+"</strong><br>"));
			}else if(message.getPayload().equals("검색")){
				session.sendMessage(new TextMessage("챗봇 : "+"<strong>"+"법률 정보 조회를 원하시면 메인화면 하단에 '검색'기능과"+
						"그 밑에 카테고리를 통하여 정보를 조회하는 것을 추천드립니다."+"</strong><br>"));
			}else if(message.getPayload().equals("카카오로그인")){
				session.sendMessage(new TextMessage("챗봇 : "+"<strong>"+"본 웹사이트는 카카오로그인 기능을 제공하지만,"+
						"카카오 로그인 시에 일반 회원이 사용하는 기능을 사용할 수는 없습니다."+"</strong><br>"));
			}else if(message.getPayload().equals("못생김")){
				session.sendMessage(new TextMessage("챗봇 : "+"<strong>"+"반사"+"</strong><br>"));
			}else if(message.getPayload().equals("버튼이 안눌려요")){
				session.sendMessage(new TextMessage("챗봇 : "+"<strong>"+"버튼 클릭이 원활하지 않을 시에는 QnA게시판에서 관리자에게 "+
						"문의를 남기시면 최대한 빠른 시일내에 해결해드리겠습니다."+"</strong><br>"));
			}else if(message.getPayload().equals("qna는 어떻게 이용하나요?")){
				session.sendMessage(new TextMessage("챗봇 : "+"<strong>"+"QnA게시판의 게시글을 열람이 가능하시만, 문의작성 시에는 "+
						"별도의 일반회원 로그인이 필요합니다."+"</strong><br><br>"));
			}else if(message.getPayload().equals("?")){
				session.sendMessage(new TextMessage("챗봇 : "+"<strong>"+"ㅋㅋ"+"</strong><br>"));
			}else {
				session.sendMessage(new TextMessage("챗봇 : "+"<strong>"+"궁금하신 부분의 단어를 입력해주세요."+"</strong>"));
			}
	}
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sessionList.remove(session);
	}
}
