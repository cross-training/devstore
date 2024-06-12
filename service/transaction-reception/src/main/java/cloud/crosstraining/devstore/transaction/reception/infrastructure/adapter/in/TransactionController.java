package cloud.crosstraining.devstore.transaction.reception.infrastructure.adapter.in;

import cloud.crosstraining.devstore.transaction.reception.application.port.in.TransactionService;
import cloud.crosstraining.devstore.transaction.reception.domain.Transaction;
import cloud.crosstraining.devstore.transaction.reception.domain.TransactionReceptionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import java.util.List;

@RestController
@RequestMapping("/transaction/reception")
public class TransactionController {

    private final TransactionService service;
    @Autowired
    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping
    public Flux<TransactionReceptionResponse> receive(List<Transaction> transactions) {
        return this.service.receive(transactions);
    }

}
