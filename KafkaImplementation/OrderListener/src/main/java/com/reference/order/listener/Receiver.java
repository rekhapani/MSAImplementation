package com.reference.order.listener;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.reference.commitment.dto.CommitmentDTO;
import com.reference.commitment.dto.OrderAssociationDTO;
import com.reference.order.dto.OrderDTO;


import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
@Component
public class Receiver {
	//public final CommitmentClientService commitmentClient;

  private static final Logger LOGGER =
      LoggerFactory.getLogger(Receiver.class);

  private CountDownLatch latch = new CountDownLatch(1);
  
 
  public CountDownLatch getLatch() {
    return latch;
  }

  
  /*@KafkaListener(topics = "${topic.helloworld}")
  public void receive(Order1DTO payLoad) {
	  
    LOGGER.info("received payload='{}'", payLoad);
    
    latch.countDown();
  }*/
  @KafkaListener(topics = "${topic.helloworld}")
  public void receive(@Payload OrderDTO data,
          @Headers MessageHeaders headers) {
	  
    
    LOGGER.info("received payload='{}'", data.getOrderId());
    System.out.println("Test REceiver" + data);
    OrderAssociationDTO associationDTO =  associateOrder(data);
    System.out.println("Test REceiver associationDTO" + associationDTO);
    latch.countDown();
  }
  
  public OrderAssociationDTO associateOrder(final OrderDTO orderDTO) {
	  RestTemplate template= new RestTemplate();
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		final HttpEntity<OrderDTO> httpEntity = new HttpEntity<>(orderDTO, headers);
		final ResponseEntity<OrderAssociationDTO> response = template.exchange(
				"http://localhost:8082" + "/associateOrder", HttpMethod.POST, httpEntity,
				new ParameterizedTypeReference<OrderAssociationDTO>() {
				});

		return getResponseBody(response);
	}

	public static <T> T getResponseBody(ResponseEntity<T> object) {
		T responseBody = null;
		if (Optional.ofNullable(object).isPresent()) {
			responseBody = object.getBody();
		}
		return responseBody;
	}
  
}
