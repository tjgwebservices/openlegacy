package org.openlegacy.web.mvc;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/reload")
public class ReloadContextController {

	private String reloadPassword = null;

	@Inject
	private ApplicationContext applicationContext;

	/**
	 * allow "localhost" to reload by default
	 */
	private String ip = "localhost";

	@RequestMapping(method = RequestMethod.GET)
	public void reload(@RequestParam(value="password",required=false) String password, HttpServletRequest request, HttpServletResponse response) {
		
		if (request.getServerName().toString().contains(ip)){
			// OK	
		}
		else{
			if (reloadPassword == null) {
				throw (new RuntimeException("Reload password not configured. Reload is not supported"));
			}
			if (!StringUtils.equals(reloadPassword, password)) {
				throw (new RuntimeException("Password dont match reload password"));
			}
		}
		
		((ConfigurableApplicationContext)applicationContext.getParent()).refresh();
		((ConfigurableApplicationContext)applicationContext).refresh();
		response.setStatus(HttpServletResponse.SC_OK);
	}

	public void setReloadPassword(String reloadPassword) {
		this.reloadPassword = reloadPassword;
	}
	
	public void setEnableReloadFromIp(String ip) {
		this.ip = ip;
	}
}
