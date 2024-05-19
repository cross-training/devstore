package cloud.crosstraining.devstore.application.service;

import cloud.crosstraining.devstore.domain.Entity;
import cloud.crosstraining.devstore.application.port.out.Repository;
import cloud.crosstraining.devstore.application.port.in.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public abstract class ServiceImpl<ENTITY extends Entity<ID>,ID> implements Service<ENTITY,ID> {
    private final Repository<ENTITY,ID> repository;

    @Autowired
    public ServiceImpl(Repository<ENTITY,ID> repository) {
        this.repository = repository;
    }

    @Override
    public List<ENTITY> getAll() {
        return repository.findAll();
    }

    @Override
    public ENTITY getById(ID id) {
        return repository.findById(id);
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
    public void deleteById(ID id) {
        repository.deleteById(id);
    }
}
