package com.jp.fix.client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;
import quickfix.*;
import quickfix.field.ApplVerID;

@Configuration
@Slf4j
public class ClientConfig {
    private static final String configFile = "config/fix/client.cfg";

    @Autowired
    ClientApplication application;

    @Bean
    public ThreadedSocketInitiator threadedSocketInitiator() {
        log.info("going through RNTConfig");
        ThreadedSocketInitiator threadedSocketInitiator = null;

        try {
            SessionSettings settings = new SessionSettings(new FileInputStream(configFile));
            // System.out.println("SETTINGS : " + settings);
            MessageStoreFactory storeFactory = new FileStoreFactory(settings);
            LogFactory logFactory = new FileLogFactory(settings);
            MessageFactory messageFactory = new DefaultMessageFactory(ApplVerID.FIX50SP2);
            threadedSocketInitiator = new ThreadedSocketInitiator(
                    application, storeFactory, settings, logFactory,
                    messageFactory);
        } catch (ConfigError configError) {
            configError.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return threadedSocketInitiator;
    }
}
