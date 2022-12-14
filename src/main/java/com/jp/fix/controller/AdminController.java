package com.jp.fix.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jp.fix.FixApplication;

import quickfix.*;
import quickfix.Message.Header;
import quickfix.field.*;

@RestController
@RequestMapping("admin")
public class AdminController {

    @GetMapping
    public String home() throws SessionNotFound {

        Message message = new Message();
        Header header = message.getHeader();

        header.setField(new BeginString("FIXT.1.1"));
        header.setField(new SenderCompID("CLIENT"));
        header.setField(new TargetCompID("SERVER"));
        header.setField(new MsgType("D"));
        // message.setField(new OrigClOrdID("123"));
        // message.setField(new ClOrdID("321"));
        // message.setField(new Side(Side.BUY));
        message.setField(new Text("Cancel My Order!"));

        Session.sendToTarget(message);

        return "Admin version : " + FixApplication.appVersion;
    }

    @GetMapping("start")
    public String start() {
        return "Starting...";
    }
}
