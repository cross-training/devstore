package cloud.crosstraining.devstore.application.service;

import cloud.crosstraining.devstore.domain.Entity;
import cloud.crosstraining.devstore.application.port.out.Repository;
import cloud.crosstraining.devstore.application.port.in.Service;
import cloud.crosstraining.devstore.domain.FindAllArgs;
import cloud.crosstraining.devstore.domain.FindArgs;
import org.springframework.beans.factory.annotation.Autowired;

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
    public ENTITY getById(ID id) {
        return repository.findById(id);
    }

    @Override
    public List<ENTITY> getAll(FindAllArgs args) {
        return repository.findAll(args);
    }

    @Override
    public ENTITY create(ENTITY entity) {
        return repository.save(entity);
    }

    @Override
    public ENTITY update(ID id, ENTITY entity) {
        ENTITY existingEntity = repository.findById(id);
        if (existingEntity != null) {
            entity.setId(id);
            return repository.save(entity);
        } else {
            return null;
        }
    }

    @Override
    public ENTITY updatePartially(ID id, Map<String, Object> updates) {
        ENTITY entity = repository.findById(id);
        if (entity == null) {
            new RuntimeException("Id: " + id + " Not found");
        }
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
    public void deleteById(ID id) {
        repository.deleteById(id);
    }
}
