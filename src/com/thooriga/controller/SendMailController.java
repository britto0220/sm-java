package com.thooriga.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.thooriga.dto.ResponseDTO;
import com.thooriga.dto.SendMailDTO;
import com.thooriga.service.SendMailService;

@Path("/gMail")
public class SendMailController {
	
	/**
	 * This Method user to sending mail for website through Gmail
	 * 
	 * @return ResponseDTO
	 * @author SANTHOSH
	 * @since 17-02-2022
	 */
	@POST
	@Path("/sendMail")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseDTO websiteThroughSendMail(SendMailDTO dto) {
		ResponseDTO response = SendMailService.getInstance().websiteThroughSendMail(dto);
		return response;
	}

}
