package com.practice.graduateWork.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.graduateWork.Domain.NovelDTO;
import com.practice.graduateWork.Service.NovelService;
import lombok.NoArgsConstructor;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// 이미지, 영상 등의 실시간 공유를 원한다면 BinaryWebSockethandler를 사용하기
public class ReplyEchoHandler extends TextWebSocketHandler {
    List<WebSocketSession> sessions = new ArrayList<>();

    @Autowired
    public NovelService novelService;

    @Autowired
    public ObjectMapper objectMapper;

    /** Connection이 연결된 경우 */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        System.out.println("afterConnectionEstablished: " + session);

        sessions.add(session); // 접속한 사용자의 session을 List에 추가하여 다룰 수 있음
    }


    /** 클라이언트가 Socket을 통해 message 보낸 경우 */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        System.out.println("handleTextmessage:" + session + " : " + message);
        String senderId = session.getId(); // 모든 HttpSession, WebSocketSession은 id를 반드시 가지게 되어있다
        // session.getId() == 해당 Session의 id 제공

        NovelDTO dto = objectMapper.readValue(message.getPayload(), NovelDTO.class);
        novelService.saveNovel(dto);

        for(WebSocketSession sess: sessions) {
            sess.sendMessage(new TextMessage(senderId + ": " + message.getPayload()));
            // WebSocket에서 전송할 메시지는 단순한 String이 아닌, TextMessage라는 객체로 다루어짐
            // 이때, 클라이언트로부터 받아온 message와 같은 메시지 값들은 그대로 쓰는 것이 아니라 getPayload()로 사용해야 그 값을 전달 가능
        }
    }

    public void getId(WebSocketSession session) {
        Map<String, Object> httpSession = session.getAttributes();

//        if(null == )
//            session.getId();
//        else
//            return 0; // userId
//
//        return null;
    }


    /** Connection이 종료된 경우 */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        System.out.println("afterConnectionClosed: " + session + " : " + status);

    }


}
