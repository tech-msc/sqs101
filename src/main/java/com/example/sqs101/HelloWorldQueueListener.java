package com.example.sqs101;

import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelloWorldQueueListener {

  private long temporaryMessageCounter = 0;

  @SqsListener("hello-world-queue")
  public void listen(Message<HelloWorldQueueObject> message) {
    log.info(String.format("Message received: %s", message));
    incrementReadingsCounter();
  }

  private void incrementReadingsCounter() {
    temporaryMessageCounter++;
    log.info(String.format("Message counter: %d", temporaryMessageCounter));
  }
}
