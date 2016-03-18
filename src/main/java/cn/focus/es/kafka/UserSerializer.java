package cn.focus.es.kafka;

import java.nio.charset.Charset;
import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import com.alibaba.fastjson.JSONObject;

public class UserSerializer implements Serializer<User>{

	

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		
	}

	@Override
	public byte[] serialize(String topic, User data) {
		return JSONObject.toJSONString(data).getBytes(Charset.forName("UTF-8"));
	}

	@Override
	public void close() {
		
	}

}
