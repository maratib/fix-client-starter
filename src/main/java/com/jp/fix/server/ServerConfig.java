package com.jp.fix.server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;
import quickfix.*;

@Configuration
@Slf4j
public class ServerConfig {
    private static final String configFile = "config/fix/server.cfg";

    @Autowired
    ServerApplication application;

    @Bean
    public ThreadedSocketAcceptor threadedSocketAcceptor() {
        log.info("going through RNTConfig");
        ThreadedSocketAcceptor threadedSocketAcceptor = null;

        try {
            SessionSettings settings = new SessionSettings(new FileInputStream(configFile));
            // System.out.println("SETTINGS : " + settings);
            MessageStoreFactory storeFactory = new FileStoreFactory(settings);
            LogFactory logFactory = new FileLogFactory(settings);
            MessageFactory messageFactory = new DefaultMessageFactory();
            threadedSocketAcceptor = new ThreadedSocketAcceptor(
                    application, storeFactory, settings, logFactory,
                    messageFactory);
            threadedSocketAcceptor.start();
        } catch (ConfigError configError) {
            configError.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return threadedSocketAcceptor;
    }
}
