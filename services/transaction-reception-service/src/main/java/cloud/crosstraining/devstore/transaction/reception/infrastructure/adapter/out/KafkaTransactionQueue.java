package cloud.crosstraining.devstore.transaction.reception.infrastructure.adapter.out;

import cloud.crosstraining.devstore.transaction.reception.application.port.out.TransactionQueue;
import cloud.crosstraining.devstore.transaction.reception.domain.Transaction;
import org.springframework.kafka.core.KafkaTemplate;
import java.util.List;

public class KafkaTransactionQueue implements TransactionQueue {
    @Override
    public void queued(List<Transaction> transactions) {
//        kafkaTemplate.send(TOPIC, transactions);
    }

    public void sendMessage(String message) {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" +
                        message + "] due to : " + ex.getMessage());
            }
        });
    }
}
