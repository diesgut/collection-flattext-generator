package com.diesgut.collectionflattextgenerator;

import java.io.File;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.diesgut.collectionflattextgenerator.service.ICollectService;

@SpringBootApplication
public class StartApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(StartApplication.class);
	
	public final static int MINIMUM_OPERATION_AMOUNT = 500;
	public final static int MAXIUM_OPERATION_AMOUNT = 1500;
	
	public final static String FOLDER_DEPOSIT= System.getProperty("user.home")+"/CDS_DEPOSITS/";

	@Autowired
	@Qualifier("bbvaCollectServiceImp")
	private ICollectService bvaCollectService;
	
	@Autowired
	@Qualifier("scoCollectServiceImp")
	private ICollectService scoCollectService;
	
	@Autowired
	@Qualifier("bcpCollectServiceImp")
	private ICollectService bcpCollectService;
	
	Random random = new Random();

	public static void main(String[] args) {
		SpringApplication.run(StartApplication.class, args);

	}

	@Override
	public void run(String... args) {
		log.info("StartApplication...");
		this.runMetod();
	}

	public void runMetod() {
		log.info("runMetod...");
		boolean success = new File(FOLDER_DEPOSIT).mkdirs();
		bvaCollectService.collectFileGenerator();
		scoCollectService.collectFileGenerator();
		bcpCollectService.collectFileGenerator();
	}

}
