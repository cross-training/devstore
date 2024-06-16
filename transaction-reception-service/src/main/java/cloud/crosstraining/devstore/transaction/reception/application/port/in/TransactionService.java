package cloud.crosstraining.devstore.transaction.reception.application.port.in;

import cloud.crosstraining.devstore.transaction.reception.domain.Transaction;
import cloud.crosstraining.devstore.transaction.reception.domain.TransactionReceptionResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public interface TransactionService {
    Flux<TransactionReceptionResponse> receive(List<Transaction> transactions);
}
