package cloud.crosstraining.devstore.common.application.service;

import cloud.crosstraining.devstore.common.application.port.out.Repository;
import cloud.crosstraining.devstore.common.domain.Entity;
import cloud.crosstraining.devstore.common.domain.FindAllArgs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Slf4j
public abstract class Service<ENTITY extends Entity<ID>,ID> {

    protected final Repository<ENTITY,ID> repository;

    @Autowired
    public Service(Repository<ENTITY,ID> repository) {
        this.repository = repository;
    }

    public Mono<ENTITY> getById(ID id) {
        return repository.findById(id);
    }

    public Flux<ENTITY> getAll(FindAllArgs args) {
        return repository.findAll(args);
    }

    public Mono<ENTITY> create(ENTITY entity) {
        return repository.save(entity);
    }
    public Flux<ENTITY> bulkInsert(List<ENTITY> entities){
        return repository.bulkInsert(entities);
    }

    public Mono<ENTITY> update(ID id, ENTITY entity) {
        Mono<ENTITY> existingEntity = repository.findById(id);
        if (existingEntity != null) {
            entity.setId(id);
            return repository.save(entity);
        } else {
            return null;
        }
    }

    public Mono<ENTITY> updatePartially(ID id, Map<String, Object> updates) {
        Mono<ENTITY> _entity = repository.findById(id);
        if (_entity == null) {
            String message = "Id: " + id + " Not found";
            log.error(message);
            new RuntimeException(message);
        }
        ENTITY entity = _entity.block();
        updates.forEach((key, value) -> {
            try {
                Field field = entity.getClass().getDeclaredField(key);
                field.setAccessible(true);
                field.set(entity, value);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                String message = "Failed to update field: " + key;
                log.error(message);
                throw new RuntimeException(message, e);
            }
        });
        return repository.save(entity);
    }

    public Mono<Void> deleteById(ID id) {
        repository.deleteById(id);
        return Mono.empty();
    }
}
