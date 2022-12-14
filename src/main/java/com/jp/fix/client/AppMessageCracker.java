package com.jp.fix.client;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import quickfix.*;

@Slf4j
@Component
public class AppMessageCracker extends MessageCracker {

    @Handler
    public void onMessage(quickfix.Message message, SessionID sessionID)
            throws FieldNotFound, UnsupportedMessageType, IncorrectTagValue {
        // Handle the message here
        System.out.println("********* Message ********");
        System.out.println(String.format("Message received for sessionID={%s}: {%s}", sessionID, message));
        System.out.println("*****************");

    }

    @Handler
    public void onMessage(quickfix.fix50sp1.ExecutionReport msg, SessionID sessionID)
            throws FieldNotFound, UnsupportedMessageType, IncorrectTagValue {

        // Handle the message here
        System.out.println("********* ExecutionReport ********");
        // System.out.println("Session : " + sessionID);
        // System.out.println("ExceReport : " + msg);
        System.out.println(String.format("Message received for sessionID={%s}: {%s}", sessionID, msg));
        System.out.println("*****************");

        // ExecutionAcknowledgement
        // quickfix.fix50sp2.ExecutionAcknowledgement ack = new
        // ExecutionAcknowledgement(null, null, null, null)
    }

    @Handler
    public void onMessage(quickfix.fix50sp1.TradeCaptureReport msg, SessionID sessionID)
            throws FieldNotFound, UnsupportedMessageType, IncorrectTagValue {

        // Handle the message here
        System.out.println("********* TradeCaptureReport ********");
        // System.out.println("Session : " + sessionID);
        // System.out.println("ExceReport : " + msg);
        System.out.println(String.format("Message received for sessionID={%s}: {%s}", sessionID, msg));
        System.out.println("*****************");

        // ExecutionAcknowledgement
        // quickfix.fix50sp2.ExecutionAcknowledgement ack = new
        // ExecutionAcknowledgement(null, null, null, null)
    }

    // @Override
    // @Handler
    // public void onMessage(quickfix.fix50sp2.OrderCancelRequest
    // orderCancelRequest, SessionID sessionID)
    // throws FieldNotFound, UnsupportedMessageType, IncorrectTagValue {

    // // Handle the message here
    // log.info("*****************");
    // log.info("Message received for sessionID={}: {}", sessionID,
    // orderCancelRequest);
    // log.info("*****************");
    // }

    // @Override
    // public void onMessage(quickfix.fix50sp2.NewOrderSingle execReport, SessionID
    // sessionID)
    // throws FieldNotFound, UnsupportedMessageType, IncorrectTagValue {

    // // Handle the message here
    // log.info("*****************");
    // log.info("Message received for sessionID={}: {}", sessionID, execReport);
    // log.info("*****************");
    // }

    // @Override
    // public void onMessage(quickfix.fix50sp2.MarketDataRequest execReport,
    // SessionID sessionID)
    // throws FieldNotFound, UnsupportedMessageType, IncorrectTagValue {

    // // Handle the message here
    // log.info("*****************");
    // log.info("Message received for sessionID={}: {}", sessionID, execReport);
    // log.info("*****************");
    // }

}
