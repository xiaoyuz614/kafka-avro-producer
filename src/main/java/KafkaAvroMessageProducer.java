
import java.util.Properties;

import org.apache.avro.generic.GenericRecord;
import org.apache.avro.generic.GenericRecordBuilder;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.avro.AvroMapper;
import com.fasterxml.jackson.dataformat.avro.AvroSchema;

/**
 * Producer for Avro-Encoded Kafka Messages. 
 * 
 * @author Affan Hasan
 */
public class KafkaAvroMessageProducer {
	
	private static final String TOPIC_NAME = "customer-topic-1";
	private static final String KAFKA_SERVER_ADDRESS = "localhost:9090";
	private static final String AVRO_SERIALIZER_CLASS = "io.confluent.kafka.serializers.KafkaAvroSerializer";
	private static final String SCHEMA_REGISTRY_SERVER_URL = "http://localhost:8081";
	
    public static void main(final String[] args ) throws JsonMappingException {
    	// Kafka Producer Configurations
    	final Properties kafkaProps = new Properties();
    	kafkaProps.put("bootstrap.servers", KAFKA_SERVER_ADDRESS);
    	kafkaProps.put("key.serializer", AVRO_SERIALIZER_CLASS);
    	kafkaProps.put("value.serializer", AVRO_SERIALIZER_CLASS);
    	kafkaProps.put("schema.registry.url", SCHEMA_REGISTRY_SERVER_URL);
    	
    	// Schema Generation For Our Customer Class
    	final AvroMapper avroMapper = new AvroMapper();
    	final AvroSchema schema = avroMapper.schemaFor(Customer.class);
    	try(final Producer<String, GenericRecord> producer = new KafkaProducer<>(kafkaProps);) {
    		
    		// Publishing The Messages
    		for (int c = 0; c < 100; c++) {
    			final Customer customer = CustomerGenerator.getNext();
    			GenericRecordBuilder recordBuilder = new GenericRecordBuilder(schema.getAvroSchema());
            	recordBuilder.set("name", customer.getName());
            	recordBuilder.set("email", customer.getEmail());
            	recordBuilder.set("contactNumber", customer.getContactNumber());
    			final GenericRecord genericRecord = recordBuilder.build();
    			final ProducerRecord<String, GenericRecord> producerRecord = new ProducerRecord<>(TOPIC_NAME, 
    					"customer", genericRecord);
    			producer.send(producerRecord);
    			System.out.println("Published message for customer: " + customer);
    		}
    	}
    }
}
