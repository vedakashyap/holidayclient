package com.acc;

import java.math.BigInteger;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.acc.domain2.EmployeeType;
import com.acc.domain2.HolidayRequest;
import com.acc.domain2.HolidayResponse;
import com.acc.domain2.HolidayType;
import com.acc.domain2.ObjectFactory;

@Component("holidayBean")
public class HolidayClient {

  @Autowired
  private WebServiceTemplate webServiceTemplate;

  public void requestHoliday() throws DatatypeConfigurationException {
    ObjectFactory factory = new ObjectFactory();
    HolidayRequest holRequest = factory.createHolidayRequest();
    
    HolidayType holType= factory.createHolidayType();
    XMLGregorianCalendar start =DatatypeFactory.newInstance().newXMLGregorianCalendar("2019-02-22");
    XMLGregorianCalendar end =DatatypeFactory.newInstance().newXMLGregorianCalendar("2019-02-24");
    holType.setStartDate(start);
    holType.setEndDate(end);
    holRequest.setHoliday(holType);
    
    EmployeeType empType = factory.createEmployeeType();
    empType.setFirstName("Emp1");
    empType.setLastName("Emp1 Surname");
    empType.setNumber(BigInteger.valueOf(1));
    holRequest.setEmployee(empType);

    System.out.println("Client sending date[startdate="+ holType.getStartDate().toString()
    		+" ,enddate="+ holType.getEndDate().toString());

    HolidayResponse response = (HolidayResponse) webServiceTemplate.marshalSendAndReceive(holRequest);

 
    System.out.println(response.getHolidayStatus().toString());
    System.out.println(response.getStatusDate().toString());
  }
}