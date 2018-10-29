package it.springboot.ocp.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    private static final String template = "Hello, my name is %s! Your message is '%s'!";
	private static final String NAME_PROP = "hello.service.name";

    @RequestMapping("/hello")
    public ServiceResp hello(@RequestParam(value="message", defaultValue="not received :D") String message) {
    	String service = System.getenv().get(NAME_PROP);
    	String msg = String.format(template, service != null ? service : "NO ONE", message);
        logger.info(msg);
    	ServiceResp resp = new ServiceResp();
    	resp.setMessage(msg);
    	resp.setServiceName(service);
		return resp ;
    }
}
