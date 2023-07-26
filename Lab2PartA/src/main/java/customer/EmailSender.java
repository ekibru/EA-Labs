package customer;

import org.aspectj.lang.annotation.AfterReturning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailSender implements IEmailSender {

	@Value("smtp.acme.com")
	String outgoingMailServer;
	@Autowired
	private ILogger logger;



	public String getOutgoingMailServer() {
		return outgoingMailServer;
	}


	public void sendEmail (String email, String message){
		System.out.println("EmailSender: sending '"+message+"' to "+email );
		logger.log("Email is sent: message= "+message +" , emailaddress ="+ email  );
	}

	public void setLogger(ILogger logger) {
		this.logger = logger;
	}
}
