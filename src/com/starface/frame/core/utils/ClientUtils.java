package com.starface.frame.core.utils;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.starface.frame.core.web.domain.Result.Status;

import net.sf.json.JSONObject;

public class ClientUtils {
	
	private final static String RESOULTCODE = "resultCode";
	private final static int RESOULTCODESUCCESS = 200;
	private final static String  RESULTMESSAGE= "resultMessage";
	private final static String  RESULTSTATUS= "resultStatus";
	
	public static String success(String info){
		JSONObject jsonObject = new JSONObject();
		jsonObject.accumulate(RESOULTCODE, RESOULTCODESUCCESS);
		jsonObject.accumulate(RESULTMESSAGE, info);
		jsonObject.accumulate(RESULTSTATUS, true);
		return jsonObject.toString();
	}
	
	public static String success(String info,Map map){
		JSONObject jsonObject = new JSONObject();
		jsonObject.accumulate(RESOULTCODE, RESOULTCODESUCCESS);
		jsonObject.accumulate(RESULTMESSAGE, info);
		jsonObject.accumulate(RESULTSTATUS, true);
		Iterator<Entry<String, Object>> iterator = map.entrySet().iterator();
		Entry<String, Object> entry;
		while (iterator.hasNext()) {
			entry = iterator.next();
			jsonObject.accumulate(entry.getKey(), entry.getValue());
		}
		return jsonObject.toString();
	}
	
	public static String success(String info,String key,Object obj){
		JSONObject jsonObject = new JSONObject();
		jsonObject.accumulate(RESOULTCODE, RESOULTCODESUCCESS);
		jsonObject.accumulate(RESULTMESSAGE, info);
		jsonObject.accumulate(RESULTSTATUS, true);
		jsonObject.accumulate(key, obj);
		return jsonObject.toString();
	}
	
	
	
	public static String failure(String info){
		JSONObject jsonObject = new JSONObject();
		jsonObject.accumulate(RESOULTCODE, RESOULTCODESUCCESS);
		jsonObject.accumulate(RESULTMESSAGE, info);
		jsonObject.accumulate(RESULTSTATUS, false);
		return jsonObject.toString();
	}
	
	public static String failure(int status,String info){
		JSONObject jsonObject = new JSONObject();
		jsonObject.accumulate(RESOULTCODE, status);
		jsonObject.accumulate(RESULTMESSAGE, info);
		jsonObject.accumulate(RESULTSTATUS, false);
		return jsonObject.toString();
	}
	
	public static String failure(String info,Map map){
		JSONObject jsonObject = new JSONObject();
		jsonObject.accumulate(RESOULTCODE, RESOULTCODESUCCESS);
		jsonObject.accumulate(RESULTMESSAGE, info);
		jsonObject.accumulate(RESULTSTATUS, false);
		Iterator<Entry<String, Object>> iterator = map.entrySet().iterator();
		Entry<String, Object> entry;
		while (iterator.hasNext()) {
			entry = iterator.next();
			jsonObject.accumulate(entry.getKey(), entry.getValue());
		}
		return jsonObject.toString();
	}
	public static String failure(int status,String info,Map map){
		JSONObject jsonObject = new JSONObject();
		jsonObject.accumulate(RESOULTCODE, status);
		jsonObject.accumulate(RESULTMESSAGE, info);
		jsonObject.accumulate(RESULTSTATUS, false);
		Iterator<Entry<String, Object>> iterator = map.entrySet().iterator();
		Entry<String, Object> entry;
		while (iterator.hasNext()) {
			entry = iterator.next();
			jsonObject.accumulate(entry.getKey(), entry.getValue());
		}
		return jsonObject.toString();
	}
	
	
}
