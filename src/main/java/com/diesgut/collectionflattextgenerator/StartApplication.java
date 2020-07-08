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
//		scoCollectService.collectFileGenerator();
//		bcpCollectService.collectFileGenerator();
		
//		String cadenaSco="0200F220848188E0800000000000000000181600000000000000003550000000000000000622144\n" + 
//				"4395383430622000901006 1006 0056 000000000000000AGENCIA CENTRO\n" + 
//				"HISTORICO 6040023013802000000 000000 REC 0000010 013838822\n" + 
//				"000 01000000";
		
		String cadenaSco="0200F220848188E0800000000000000000181600000000000000003550000000000000000622144 4395383430622000901006 1006 0056 000000000000000AGENCIA CENTRO HISTORICO 6040023013802000000 000000 REC 0000010 013838822 000 01000000";
		
	}

}
