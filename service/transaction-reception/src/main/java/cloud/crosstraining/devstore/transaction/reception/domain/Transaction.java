package cloud.crosstraining.devstore.transaction.reception.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private String id;
    private String typeId;
    private String movementTypeId;
    private String number;
    private String customerId;
    private String accountId;
    private String currencyId;
    private BigDecimal amount;
    private OffsetDateTime transactionDate;
    private OffsetDateTime dueDate;
    private List<TransactionDetail> details;
    private List<TransactionRelation> relations;
    private UUID lotId;
    private UUID processId;
}
