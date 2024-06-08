package cloud.crosstraining.devstore.transaction.reception.application.port.out;

import cloud.crosstraining.devstore.transaction.reception.domain.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionQueue {
    void queued(List<Transaction> transactions);
}
