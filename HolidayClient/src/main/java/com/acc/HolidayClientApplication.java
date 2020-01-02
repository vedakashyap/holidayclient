package com.acc;

import javax.xml.datatype.DatatypeConfigurationException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class HolidayClientApplication {

	public static void main(String[] args) throws DatatypeConfigurationException{
		
		ConfigurableApplicationContext context = SpringApplication.run(HolidayClientApplication.class, args);
		HolidayClient hc = (HolidayClient) context.getBean("holidayBean");
		hc.requestHoliday();
	}

}
