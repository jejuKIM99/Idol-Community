package com.weverse.sb.chat.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class ChatHandler extends TextWebSocketHandler {

    // streamingId (live) 또는 id (media)를 키로 세션 리스트 관리
    private final Map<String, List<WebSocketSession>> sessionMap = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String uri = session.getUri().toString();

        if (uri.contains("live")) {
            String streamingId = getParamFromUri(uri, "streamingId");
            if (streamingId == null) {
                log.warn("streamingId가 없어서 연결 거부: " + uri);
                session.close(CloseStatus.BAD_DATA);
                return;
            }
            sessionMap.computeIfAbsent(streamingId, k -> new ArrayList<>()).add(session);
            log.info("live streamingId=" + streamingId + " 클라이언트 접속: " + session);

        } else if (uri.contains("media")) {
            String id = getParamFromUri(uri, "id");
            if (id == null) {
                log.warn("media id가 없어서 연결 거부: " + uri);
                session.close(CloseStatus.BAD_DATA);
                return;
            }
            sessionMap.computeIfAbsent(id, k -> new ArrayList<>()).add(session);
            log.info("media id=" + id + " 클라이언트 접속: " + session);

        } else {
            log.warn("live 또는 media 경로가 아님: " + uri);
            session.close(CloseStatus.BAD_DATA);
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String uri = session.getUri().toString();
        String key = null;

        if (uri.contains("live")) {
            key = getParamFromUri(uri, "streamingId");
            if (key == null) {
                log.warn("streamingId가 없어 메시지 무시: " + uri);
                return;
            }
        } else if (uri.contains("media")) {
            key = getParamFromUri(uri, "id");
            if (key == null) {
                log.warn("media id가 없어 메시지 무시: " + uri);
                return;
            }
        } else {
            log.warn("live 또는 media 경로가 아님: " + uri);
            return;
        }

        String payload = message.getPayload();
        log.info("key=" + key + " payload: " + payload);

        List<WebSocketSession> sessions = sessionMap.get(key);
        if (sessions != null) {
            for (WebSocketSession sess : sessions) {
                if (sess.isOpen()) {
                    sess.sendMessage(message);
                }
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String uri = session.getUri().toString();
        String key = null;

        if (uri.contains("live")) {
            key = getParamFromUri(uri, "streamingId");
        } else if (uri.contains("media")) {
            key = getParamFromUri(uri, "id");
        }

        if (key != null) {
            List<WebSocketSession> sessions = sessionMap.get(key);
            if (sessions != null) {
                sessions.remove(session);
                if (sessions.isEmpty()) {
                    sessionMap.remove(key);
                }
            }
            log.info("key=" + key + " 클라이언트 접속 해제: " + session);
        }
    }

    private String getParamFromUri(String uri, String paramName) {
        try {
            java.net.URI u = new java.net.URI(uri);
            String query = u.getQuery();
            if (query == null) return null;

            String[] params = query.split("&");
            for (String param : params) {
                String[] pair = param.split("=");
                if (pair.length == 2 && paramName.equals(pair[0])) {
                    return pair[1];
                }
            }
        } catch (Exception e) {
            log.error("URI 파싱 오류", e);
        }
        return null;
    }
}
