package cn.focus.es.kafka;

import java.nio.charset.Charset;
import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.alibaba.fastjson.JSONObject;

public class UserDeserializer implements Deserializer<User> {

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		
	}

	@Override
	public User deserialize(String topic, byte[] data) {
		User user = null;
		try {
			user = JSONObject.parseObject(new String(data,Charset.forName("UTF-8")), User.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void close() {
		
	}

}
