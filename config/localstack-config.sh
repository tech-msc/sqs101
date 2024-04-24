#!/bin/bash

echo "configuring localstack"

#export AWS_ACCESS_KEY_ID="test"
#export AWS_SECRET_ACCESS_KEY="test"
#export AWS_DEFAULT_REGION="us-east-1"

awslocal sqs create-queue --queue-name hello-world-queue

# sends example message
#awslocal sqs send-message --queue-url http://127.0.0.1:4566/000000000000/hello-world-queue --message-body '{"message": "test"}'

