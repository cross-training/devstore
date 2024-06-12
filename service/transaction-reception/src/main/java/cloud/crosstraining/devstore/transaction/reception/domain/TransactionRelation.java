package cloud.crosstraining.devstore.transaction.reception.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRelation {
    private String referenceId;
    private String referenceTypeId;
    private BigDecimal amount;
}
