package cn.focus.es.kafka;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

/**
 * 简易的消费者
 * @author weidongduan
 *
 */
public class ConsumeClient {
	
	public static void main(String[] args) {
		Properties props = new Properties();
	     props.put("bootstrap.servers", "10.31.84.184:9092");
	     props.put("group.id", "group6");
	     props.put("enable.auto.commit", "true");
	     props.put("auto.commit.interval.ms", "1000");
	     props.put("session.timeout.ms", "30000");
	     props.put("auto.offset.reset","earliest");
	     props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	     props.put("value.deserializer", "cn.focus.es.kafka.UserDeserializer");
	     KafkaConsumer<String, User> consumer = new KafkaConsumer<>(props,new StringDeserializer(),new UserDeserializer());
	     consumer.subscribe(Arrays.asList("user-topic"));
//	     consumer.seekToBeginning(new TopicPartition("test", 0));
	     while (true) {
	         ConsumerRecords<String, User> records = consumer.poll(100);
	         for (ConsumerRecord<String, User> record : records)
	             System.out.println(record.offset()+"==="+record.key()+"====U:"+record.value().getUsername()+"===p:"+record.value().getPassword());
	         
	     }
	}
//	public static void main(String[] args) throws Exception {
//		Socket s = new Socket("10.31.84.184",9092);
//		System.out.println(s);
//		while(true){
//			InputStream in = s.getInputStream();
//			int a = 0;
//			while((a=in.read())!=-1){
//				System.out.println((char)a);
//			}
//		}
//	}
}
