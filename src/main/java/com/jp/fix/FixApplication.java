package com.jp.fix;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.core.io.FileSystemResource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FixApplication {

	@Value("${server.port}")
	private int serverPort;

	@Value("${app.version}")
	public String version;

	public static String appVersion;
	// public static final String configPath = new
	// FileSystemResource("").getFile().getAbsolutePath() + "/config";

	public static void main(String[] args) {
		SpringApplication.run(FixApplication.class, args);
	}

	@PostConstruct()
	public void onStart() throws Exception {
		// initCheck();
		// System.out.println("ConfigPath :" + configPath);
		FixApplication.appVersion = version;
		System.out.println("Application started at port : " + serverPort);
	}

	@PreDestroy
	public void onExit() {
		System.out.println("Exiting app...");
	}

}
