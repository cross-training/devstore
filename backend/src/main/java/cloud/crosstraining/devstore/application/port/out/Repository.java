package cloud.crosstraining.devstore.application.port.out;

import cloud.crosstraining.devstore.domain.Entity;
import cloud.crosstraining.devstore.domain.FindAllArgs;
import cloud.crosstraining.devstore.domain.FindArgs;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface Repository<ENTITY extends Entity<ID>,ID> {
    Flux<ENTITY> findAll(FindAllArgs args);
    Mono<ENTITY> findById(ID id);
    Mono<ENTITY> save(ENTITY entity);
    Mono<Void> deleteById(ID id);
    Flux<ENTITY> bulkInsert(List<ENTITY> entities);
}
