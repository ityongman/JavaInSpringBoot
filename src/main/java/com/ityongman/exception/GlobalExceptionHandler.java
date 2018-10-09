package com.ityongman.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ityongman.domain.ErrorInfo;

@ControllerAdvice
public class GlobalExceptionHandler {
	private final static String DEFAULT_VIEW_ERROR = "error";
	
	@ExceptionHandler(value=Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest req , Exception e) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception",e.getMessage());
		mav.addObject("url", req.getRequestURL());
		mav.setViewName(DEFAULT_VIEW_ERROR);
		
		return mav ;
	}
	
	@ExceptionHandler(value=Day06Exception.class)
	@ResponseBody
	public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req , Exception e) throws Exception {
		ErrorInfo<String> error = new ErrorInfo<>();
		error.setCode(ErrorInfo.ERROR);
		error.setMessage(e.getMessage());
		error.setUrl(req.getRequestURL().toString());
		error.setData("Some data ....");
		
		return error ;
	}
}
