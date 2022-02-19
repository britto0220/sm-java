package com.thooriga.service;

import com.thooriga.dto.ResponseDTO;
import com.thooriga.dto.SendMailDTO;
import com.thooriga.restService.SendMailRestService;
import com.thooriga.util.AppConstants;

public class SendMailService {

	public static SendMailService SendMailService = null;

	public static SendMailService getInstance() {
		if (SendMailService == null) {
			SendMailService = new SendMailService();
		}
		return SendMailService;
	}
	
	/**
	 * This Method user to sending mail for website through Gmail
	 * 
	 * @return 
	 * @author SANTHOSH
	 * @since 17-02-2022
	 */
	public ResponseDTO websiteThroughSendMail(SendMailDTO dto) {
		ResponseDTO resp = new ResponseDTO();
		String response = SendMailRestService.getInstance().websiteThroughSendMail(dto);
		if (response != null && response.equalsIgnoreCase(AppConstants.SUCCESS)) {
			resp.setStatus(AppConstants.SUCCESS_STATUS);
			resp.setMessage(AppConstants.SUCCESS_MSG);
		} else {
			resp.setStatus(AppConstants.FAILED_STATUS);
			resp.setMessage(AppConstants.FAILED_MSG);
		}
		return resp;
	}
}
