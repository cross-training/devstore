package cloud.crosstraining.devstore.transaction.reception.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDetail {
    private String conceptId;
    private String referenceId;
    private BigDecimal amount;
}
