package cloud.crosstraining.devstore.common.application.service;

import cloud.crosstraining.devstore.common.application.port.in.Service;
import cloud.crosstraining.devstore.common.application.port.out.Repository;
import cloud.crosstraining.devstore.common.domain.Entity;
import cloud.crosstraining.devstore.common.domain.FindAllArgs;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public abstract class ServiceImpl<ENTITY extends Entity<ID>,ID> implements Service<ENTITY,ID> {
    private final Repository<ENTITY,ID> repository;

    @Autowired
    public ServiceImpl(Repository<ENTITY,ID> repository) {
        this.repository = repository;
    }

    @Override
    public Mono<ENTITY> getById(ID id) {
        return repository.findById(id);
    }

    @Override
    public Flux<ENTITY> getAll(FindAllArgs args) {
        return repository.findAll(args);
    }

    @Override
    public Mono<ENTITY> create(ENTITY entity) {
        return repository.save(entity);
    }
    @Override
    public Flux<ENTITY> bulkInsert(List<ENTITY> entities){
        return repository.bulkInsert(entities);
    }

    @Override
    public Mono<ENTITY> update(ID id, ENTITY entity) {
        Mono<ENTITY> existingEntity = repository.findById(id);
        if (existingEntity != null) {
            entity.setId(id);
            return repository.save(entity);
        } else {
            return null;
        }
    }

    @Override
    public Mono<ENTITY> updatePartially(ID id, Map<String, Object> updates) {
        Mono<ENTITY> _entity = repository.findById(id);
        if (_entity == null) {
            new RuntimeException("Id: " + id + " Not found");
        }
        ENTITY entity = _entity.block();
        updates.forEach((key, value) -> {
            try {
                Field field = entity.getClass().getDeclaredField(key);
                field.setAccessible(true);
                field.set(entity, value);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException("Failed to update field: " + key, e);
            }
        });
        return repository.save(entity);
    }

    @Override
    public Mono<Void> deleteById(ID id) {
        repository.deleteById(id);
        return Mono.empty();
    }
}
