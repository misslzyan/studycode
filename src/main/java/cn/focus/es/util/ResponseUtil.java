package cn.focus.es.util;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtil {

	private static final Integer SUCCESS = 0;
	
	private static final String SUCCESS_DATA= "success";

	private static final Integer FAILURE=1;
	
	
	public static Map<String,Object> ok(){
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("errorCode", SUCCESS);
		result.put("data",SUCCESS_DATA);
		return result;
	}

	public static Map<String, Object> error(Object message) {
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("errorCode", FAILURE);
		result.put("data",message);
		return result;
	}
}