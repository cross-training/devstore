package cloud.crosstraining.devstore.application.port.out;

import cloud.crosstraining.devstore.domain.Entity;
import cloud.crosstraining.devstore.domain.FindAllArgs;
import cloud.crosstraining.devstore.domain.FindArgs;

import java.util.List;

public interface Repository<ENTITY extends Entity<ID>,ID> {
    List<ENTITY> findAll(FindAllArgs args);
    ENTITY findById(ID id);
    ENTITY save(ENTITY entity);
    void deleteById(ID id);
}
