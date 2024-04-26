package com.example.sqs101;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlResponse;

@RequiredArgsConstructor
@Component
public class HelloWorldQueuePublisher {

  private static final String QUEUE_NAME = "hello-world-queue";

  private final SqsAsyncClient sqsAsyncClient;
  private final ObjectMapper objectMapper;


  public CompletableFuture<Void> sendToUrl(String queueUrl, Object payload) {
    return this.sqsAsyncClient.sendMessage(
            request -> request.messageBody(getMessageBodyAsJson(payload)).queueUrl(queueUrl))
        .thenRun(() -> {
        });
  }

  public CompletableFuture<Void> send(String queueName, Object payload) {
    return this.sqsAsyncClient
        .getQueueUrl(request -> request.queueName(queueName))
        .thenApply(GetQueueUrlResponse::queueUrl)
        .thenCompose(queueUrl -> sendToUrl(queueUrl, payload));
  }

  private String getMessageBodyAsJson(Object payload) {
    try {
      return objectMapper.writeValueAsString(payload);
    } catch (JsonProcessingException e) {
      throw new IllegalArgumentException("Error converting payload: " + payload, e);
    }
  }


}
