package cloud.crosstraining.devstore.application.port.in;
import cloud.crosstraining.devstore.domain.Entity;
import cloud.crosstraining.devstore.domain.FindAllArgs;
import cloud.crosstraining.devstore.domain.FindArgs;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public interface Service <ENTITY extends Entity<ID>,ID> {
    Flux<ENTITY> getAll(FindAllArgs args);
    Mono<ENTITY> getById(ID id);
    Mono<ENTITY> create(ENTITY entity);
    Flux<ENTITY> bulkInsert(List<ENTITY> entities);
    Mono<ENTITY> update(ID id,ENTITY entity);
    Mono<ENTITY> updatePartially(ID id, Map<String, Object> updates);
    Mono<Void> deleteById(ID id);
}
