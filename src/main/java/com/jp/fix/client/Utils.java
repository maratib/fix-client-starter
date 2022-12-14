package com.jp.fix.client;

import quickfix.FieldNotFound;
import quickfix.LogUtil;
import quickfix.Message;
import quickfix.MessageUtils;
import quickfix.field.MsgType;

public class Utils {

    public static boolean isMessageOfType(Message message, String type) {
        try {
            return type.equals(message.getHeader().getField(new MsgType()).getValue());
        } catch (FieldNotFound e) {
            logErrorToSessionLog(message, e);
            return false;
        }
    }

    private static void logErrorToSessionLog(Message message, FieldNotFound e) {
        LogUtil.logThrowable(MessageUtils.getSessionID(message), e.getMessage(), e);
    }

}
