package cloud.crosstraining.devstore.infrastructure.adapter.out;

import cloud.crosstraining.devstore.application.port.out.ProductRepository;
import cloud.crosstraining.devstore.domain.Entity;
import cloud.crosstraining.devstore.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import cloud.crosstraining.devstore.domain.Entity;
import cloud.crosstraining.devstore.application.port.out.Repository;

import java.util.List;

public abstract class RepositoryImpl<ENTITY extends Entity<ID>, ID, JAP_ENTITY> implements Repository<ENTITY, ID> {

    private final JpaRepository<JAP_ENTITY, ID> jpaRepository;

     public RepositoryImpl(JpaRepository<JAP_ENTITY, ID> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<ENTITY> findAll() {
        return jpaRepository.findAll().stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public ENTITY findById(ID id) {
         return jpaRepository.findById(id).map(this::toDomain).orElse(null);
    }

    @Override
    public ENTITY save(ENTITY entity) {
        return toDomain(jpaRepository.save(toEntity(entity)));
    }

    @Override
    public void deleteById(ID id) {
         jpaRepository.deleteById(id);
    }

    protected abstract ENTITY toDomain(JAP_ENTITY japEntity);
    protected abstract JAP_ENTITY toEntity(ENTITY entity);
}
