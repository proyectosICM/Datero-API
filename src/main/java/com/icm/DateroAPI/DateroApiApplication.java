package com.icm.DateroAPI;

import com.icm.DateroAPI.MQTT.MqttSubscriber;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DateroApiApplication {

	public static void main(String[] args) {

		// SpringApplication.run(DateroApiApplication.class, args);

		ApplicationContext context = SpringApplication.run(DateroApiApplication.class, args);

		MqttSubscriber mqttSubscriber = context.getBean(MqttSubscriber.class);
		// Llama a subscribeToTopic en la instancia
		mqttSubscriber.subscribeToTopic("prueba");
	}

}
