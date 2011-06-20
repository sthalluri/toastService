package com.toast.service;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.toast.util.JSONException;
import com.toast.util.JSONParser;

@Service
@Scope("prototype")
public class Response {

	@Autowired
	private MessageSource messageSource;

	private Boolean success = Boolean.TRUE;
	private String successMessage;
	private String errorMessage;
	private String returnName;
	private Object returnVal;
	private String returnJson;

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getReturnName() {
		return returnName;
	}

	public void setReturnName(String returnName) {
		this.returnName = returnName;
	}

	public Object getReturnVal() {
		return returnVal;
	}

	public void setReturnVal(Object returnVal) {
		this.returnVal = returnVal;
	}

	public void addMessage(String key, Object[] args) {
		this.setSuccessMessage(messageSource.getMessage(key, args, Locale.ENGLISH));
	}

	public void addMessage(String key) {
		this.setSuccessMessage(messageSource.getMessage(key, null, Locale.ENGLISH));
	}

	public void addError(String key, Object[] args) {
		this.setErrorMessage(messageSource.getMessage(key, args, Locale.ENGLISH));
	}

	public void addError(String key) {
		this.setErrorMessage(messageSource.getMessage(key, null, Locale.ENGLISH));
	}

	public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public String getReturnJson() {
		return returnJson;
	}

	public void setReturnJson(String returnJson) {
		this.returnJson = returnJson;
	}

	@SuppressWarnings("rawtypes")
	public String toJson() {
		try {
			StringBuffer json = new StringBuffer("{\"success\":" + success);
			if (returnVal != null) {
				if (returnVal instanceof List) {
					returnJson = JSONParser.listToJSON((List) returnVal);
				} else {
					returnJson = JSONParser.toJSON(returnVal);
				}
			}
			if (returnJson == null) {
				returnJson = "{}";
			}
			System.out.println(returnJson);
			json.append(", \"returnVal\":" + returnJson);

			if (successMessage != null) {
				json.append(", \"successMessage\": \"" + successMessage + "\"");
			}

			if (errorMessage != null) {
				json.append(", \"errorMessage\": \"" + errorMessage + "\"");
			}

			json.append(" }");

			return json.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "{}";
		}
	}
}
