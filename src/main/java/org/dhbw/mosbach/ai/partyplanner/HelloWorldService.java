package org.dhbw.mosbach.ai.partyplanner;

import javax.jws.WebMethod;
import javax.jws.WebService;

//see http://cxf.apache.org/docs/developing-a-service.html
@WebService(name = "helloWorld")
public interface HelloWorldService
{
	@WebMethod
	String helloWorld();
}
