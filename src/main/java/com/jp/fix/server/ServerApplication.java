package com.jp.fix.server;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import quickfix.*;

@Component
@Slf4j
public class ServerApplication implements Application {

    private static String LOGGED_IN = "Y";

    @Override
    public void onCreate(SessionID sessionID) {
        log.warn("onCreate: SessionId={}", sessionID);
    }

    @Override
    public void onLogon(SessionID sessionID) {
        log.warn("onLogon: SessionId={}", sessionID);
    }

    @Override
    public void onLogout(SessionID sessionID) {
        log.warn("onLogout: SessionId={}", sessionID);
    }

    @Override
    public void toAdmin(Message msg, SessionID sessionID) {
        log.warn("toAdmin: Message={}, SessionId={}", msg, sessionID);
    }

    @Override
    public void fromAdmin(Message msg, SessionID sessionID)
            throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, RejectLogon {
        log.warn("fromAdmin: Message={}, SessionId={}", msg, sessionID);
    }

    @Override
    public void toApp(Message msg, SessionID sessionID) throws DoNotSend {
        log.warn("toApp: Message={}, SessionId={}", msg, sessionID);
    }

    @Override
    public void fromApp(Message msg, SessionID sessionID)
            throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
        log.warn("fromApp: Message={}, SessionId={}", msg, sessionID);
    }

}
