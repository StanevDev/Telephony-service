package edu.jam.telephony;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({
                "edu.jam.telephony.dao.impl",
                "edu.jam.telephony.service.impl"})
public class TelephonyApplication {

	public static void main(String[] args) {
	    ApplicationContext context =
                SpringApplication.run(TelephonyApplication.class, args);
	}

}
