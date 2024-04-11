package produsers;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class Producer {
    public static void main(String[] args) {
        Properties properties = new Properties();
        //адрес брокера
        properties.put("bootstrap.servers", "localhost:9092");
        //ключи и значения представлены строками, поэтому указываются  строковые десериализаторы, чтобы Kafka produser API мог правильно интерпретировать байтовые данные и преобразовать их обратно в строки
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(properties);
        try{
            for (int i = 0; i < 45; i++){
                System.out.println(i);
                kafkaProducer.send(new ProducerRecord<String, String>("no-spring-topic", Integer.toString(i), "test message - " + i));

            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            kafkaProducer.close();
        }


    }

}
