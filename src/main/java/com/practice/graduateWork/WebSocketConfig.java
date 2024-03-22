package com.practice.graduateWork;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.graduateWork.Service.NovelService;
import com.practice.graduateWork.handler.ReplyEchoHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    /** 싱글톤 replyEchoHandler 제공 */
    @Bean
    public ReplyEchoHandler replyEchoHandler() {
        return new ReplyEchoHandler();
    }

    @Bean
    public ObjectMapper objectMapper() {return new ObjectMapper();}


    /** /replyEcho 경로로 들어오는 요청에 대한 처리 핸들러를 replyEchoHandler로 지정하고,
     * 서버와 연결한 클라이언트의 각 세션의 구분을 위해 HTTP Session 속성을 WebSocket Session에 올려줌
     * => HttpSessionHandshakeInterceptor의 역할 */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(replyEchoHandler(), "/replyEcho")
                .setAllowedOrigins("*")
                .addInterceptors(new HttpSessionHandshakeInterceptor());
//                .withSockJS()

    }

}

/*
servlet-context.xml

<!-- websocket handler -->
<beans:bean id="replyEchoHandler" class="com.jade.swp.handler.ReplyEchoHandler" />

<websocket:handlers>
<websocket:mapping handler="replyEchoHandler" path="/replyEcho" />
<websocket:handshake-interceptors>
    <beans:bean
        class="org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor" />
</websocket:handshake-interceptors>
</websocket:handler>
</beans:beans>

 */
