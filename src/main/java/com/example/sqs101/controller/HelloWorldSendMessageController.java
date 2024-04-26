package com.example.sqs101;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "sqs101/send-message")
@RequiredArgsConstructor
public class HelloWorldSendMessageController {

  private final HelloWorldQueuePublisher queuePublisher;

  @GetMapping
  public String status() {
    return "running ... " + LocalDateTime.now();
  }

  @PostMapping
  public ResponseEntity<HelloWorldQueueObject> sendMessage(@RequestBody HelloWorldObjectDTO helloWorldObjectDTO) {

    HelloWorldQueueObject queueObject = new HelloWorldQueueObject(helloWorldObjectDTO.getMessage());

    queuePublisher.send("hello-world-queue", queueObject);

    return ResponseEntity.ok(queueObject);
  }

}

