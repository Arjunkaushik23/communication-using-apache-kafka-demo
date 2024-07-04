package com.kafka.communication.consumer.service;

import com.kafka.communication.consumer.entity.Message;
import com.kafka.communication.consumer.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private final static Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    private final MessageRepository messageRepository;

    public KafkaConsumer(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @KafkaListener(topics="my_topic", groupId = "group_id")
    public void consume(String message) {
        LOGGER.info(String.format("Consumed Message: %s", message));
        Message msg = new Message(message);
        messageRepository.save(msg);
    }
}
