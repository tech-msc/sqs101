#!/bin/bash

echo "configuring localstack"

#export AWS_ACCESS_KEY_ID="test"
#export AWS_SECRET_ACCESS_KEY="test"
#export AWS_DEFAULT_REGION="us-east-1"

#Estrutura de mensageria de solicitações de emissão
#awslocal sns create-topic --name sns-topic-example
awslocal sqs create-queue --queue-name queue-example


# sends example message
#awslocal sqs send-message --queue-url http://127.0.0.1:4566/000000000000/queue-example --message-body "{'message': 'test'}"

