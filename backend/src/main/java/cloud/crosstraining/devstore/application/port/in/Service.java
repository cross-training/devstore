package cloud.crosstraining.devstore.application.port.in;
import cloud.crosstraining.devstore.domain.Entity;
import cloud.crosstraining.devstore.domain.FindAllArgs;
import cloud.crosstraining.devstore.domain.FindArgs;

import java.util.List;
import java.util.Map;

public interface Service <ENTITY extends Entity<ID>,ID> {
    List<ENTITY> getAll(FindAllArgs args);
    ENTITY getById(ID id);
    ENTITY create(ENTITY entity);
    ENTITY update(ID id,ENTITY entity);
    ENTITY updatePartially(ID id, Map<String, Object> updates);
    void deleteById(ID id);
}
