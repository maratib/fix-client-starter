package com.jp.fix.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import quickfix.*;
import quickfix.field.BeginString;
import quickfix.field.DefaultApplVerID;
import quickfix.field.DefaultCstmApplVerID;
import quickfix.field.ResetSeqNumFlag;

@Component
@Slf4j
public class ClientApplication implements Application {

    private static boolean RESET_SEQ_FLAG = true;
    private static boolean isLoggedOn = false;

    public static volatile SessionID sessionID;

    @Autowired
    private AppMessageCracker messageCracker;

    @Override
    public void onCreate(SessionID sessionID) {
        System.out.println(String.format("onCreate: SessionId={%s}", sessionID));
    }

    @Override
    public void onLogon(SessionID sessionID) {
        ClientApplication.RESET_SEQ_FLAG = false;
        ClientApplication.isLoggedOn = true;
        ClientApplication.sessionID = sessionID;
        System.out.println(String.format("onLogon: SessionId={%s}", sessionID));
    }

    @Override
    public void onLogout(SessionID sessionID) {
        ClientApplication.RESET_SEQ_FLAG = true;
        ClientApplication.isLoggedOn = false;
        System.out.println("onLogon SessionID : " + sessionID);
        ClientApplication.sessionID = null;
        System.out.println(String.format("onLogout: SessionId={%s}", sessionID));
    }

    @Override
    public void toAdmin(Message msg, SessionID sessionID) {

        if (!ClientApplication.isLoggedOn) {
            msg.getHeader().setField(new ResetSeqNumFlag(RESET_SEQ_FLAG));
            // msg.getHeader().setField(new DefaultApplVerID("8")); // 1137
            msg.getHeader().setField(new DefaultCstmApplVerID("01.012.00"));
            msg.getHeader().setString(1603, "JAMIFIXUAT");
            msg.getHeader().setString(1604, "REC_JAMI PARTNERS_FIX_LBN");

            // msg.setChar(141, InitiatorApplication.LOGGED_IN);
            // msg.setString(quickfix.field.DefaultCstmApplVerID.FIELD, );
        }

        System.out.println("toAdmin -> Message : " + msg);

    }

    @Override
    public void fromAdmin(Message msg, SessionID sessionID)
            throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, RejectLogon {
        System.out.println(String.format("fromAdmin: {%s}, {%s}", msg, sessionID));
    }

    @Override
    public void toApp(Message msg, SessionID sessionID) throws DoNotSend {
        // crack(msg, sessionID);
        System.out.println(String.format("toApp: {%s}, {%s}", msg, sessionID));
    }

    @Override
    public void fromApp(Message msg, SessionID sessionID)
            throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {

        System.out.println(String.format("fromApp: {%s}, {%s}", msg, sessionID));

        try {
            // System.out.println("Going to crack ");
            messageCracker.crack(msg, sessionID);
        } catch (UnsupportedMessageType | FieldNotFound | IncorrectTagValue e) {
            log.error(e.getMessage(), e);
        }

        // String beginString = msg.getString(8);

        // System.out.println("BeginString : " + beginString);

    }

}
