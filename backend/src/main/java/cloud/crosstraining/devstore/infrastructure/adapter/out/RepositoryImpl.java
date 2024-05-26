package cloud.crosstraining.devstore.infrastructure.adapter.out;

import cloud.crosstraining.devstore.domain.Entity;
import cloud.crosstraining.devstore.domain.FindAllArgs;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import cloud.crosstraining.devstore.application.port.out.Repository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class RepositoryImpl<ENTITY extends Entity<ID>, ID, JAP_ENTITY> implements Repository<ENTITY, ID> {

    protected final PagingAndSortingRepository<JAP_ENTITY, ID> jpaRepository;
    protected final EntityManagerFactory entityManagerFactory;
    protected final EntityManager entityManager;

     public RepositoryImpl(PagingAndSortingRepository<JAP_ENTITY, ID> jpaRepository,EntityManagerFactory entityManagerFactory) {
        this.jpaRepository = jpaRepository;
        this.entityManagerFactory = entityManagerFactory;
        this.entityManager= entityManagerFactory.createEntityManager();
    }

    @Override
    public List<ENTITY> findAll(FindAllArgs args) {
        return _findAll(args).stream()
                .map(this::toDomain)
                .toList();
    }

    protected Set<JAP_ENTITY> _findAll(FindAllArgs args) {
        String[] sortProperties = args.getSort() != null ? args.getSort() : new String[]{"id"};
        Sort.Direction direction = args.getDesc() ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, sortProperties);
        Pageable pageable = PageRequest.of(args.getPage(), args.getSize(), sort);
        return jpaRepository.findAll(pageable).toSet();
    }

    @Override
    public ENTITY findById(ID id) {
        JAP_ENTITY entity = _findById(id);
        return  entity!=null?toDomain(entity):null;
    }

    protected JAP_ENTITY _findById(ID id) {
        return jpaRepository.findById(id).orElse(null);
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
    protected abstract Class<JAP_ENTITY> classEntity();
}
