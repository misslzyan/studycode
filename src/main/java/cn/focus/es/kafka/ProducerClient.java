package cn.focus.es.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class ProducerClient {
	
	public static void main(String[] args) {
		Properties props = new Properties();
		 props.put("bootstrap.servers", "10.31.84.184:9092");
		 props.put("acks", "all");
		 props.put("retries", 0);
		 props.put("batch.size", 16384);
		 props.put("linger.ms", 1);//延迟1ms 想server commit message
		 props.put("buffer.memory", 33554432);
		 props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		 props.put("value.serializer", "cn.focus.es.kafka.UserSerializer");

		 Producer<String, User> producer = new KafkaProducer<>(props);
		 for(int i = 0; i < 100; i++)
		     producer.send(new ProducerRecord<String, User>("user-topic", Integer.toString(i), new User(i+"",i+"")),new Callback() {
				
				@Override
				public void onCompletion(RecordMetadata metadata, Exception exception) {
					if(exception!=null)
						exception.printStackTrace();
					System.out.println(metadata.partition());
					System.out.println(metadata.topic());
					System.out.println(metadata.offset());
					
				}
			});

		 producer.close();
		 
	}
}
