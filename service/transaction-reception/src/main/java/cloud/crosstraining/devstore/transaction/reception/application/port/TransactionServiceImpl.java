package cloud.crosstraining.devstore.transaction.reception.application.port;

import cloud.crosstraining.devstore.transaction.reception.application.port.in.TransactionService;
import cloud.crosstraining.devstore.transaction.reception.application.port.out.TransactionQueue;
import cloud.crosstraining.devstore.transaction.reception.domain.Transaction;
import cloud.crosstraining.devstore.transaction.reception.domain.TransactionReceptionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.UUID;

public class TransactionServiceImpl  implements TransactionService {

    private final TransactionQueue queueService;

    @Autowired
    public TransactionServiceImpl(TransactionQueue queueService) {
        this.queueService = queueService;
    }

    @Override
    public Flux<TransactionReceptionResponse> receive(List<Transaction> transactions) {
        UUID lotId = UUID.randomUUID();
        return Flux.fromIterable(transactions)
                .doOnNext(transaction -> {
                    transaction.setProcessId(UUID.randomUUID());
                    transaction.setLotId(lotId);
                }).collectList()
                .doOnNext(queueService::queued)
                .flatMapMany(Flux::fromIterable)
                .map(transaction -> new TransactionReceptionResponse(
                        transaction.getId(),
                        transaction.getProcessId(),
                        transaction.getLotId()));
    }
}
