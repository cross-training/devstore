package cloud.crosstraining.devstore.common.infrastructure.adapter.out;

import cloud.crosstraining.devstore.common.domain.Entity;
import cloud.crosstraining.devstore.common.domain.FindAllArgs;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import cloud.crosstraining.devstore.common.application.port.out.Repository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.domain.Pageable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public abstract class RepositoryImpl<ENTITY extends Entity<ID>, ID, JAP_ENTITY> implements Repository<ENTITY, ID> {

    protected final PagingAndSortingRepository<JAP_ENTITY, ID>  jpaRepository;
    protected final EntityManagerFactory entityManagerFactory;

     public RepositoryImpl(
             PagingAndSortingRepository<JAP_ENTITY, ID> jpaRepository,
             EntityManagerFactory entityManagerFactory) {
        this.jpaRepository = jpaRepository;
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Flux<ENTITY> findAll(FindAllArgs args) {
        List<ENTITY> result = _findAll(args).stream()
                .map(this::toDomain)
                .toList();
        return Flux.fromIterable(result);

    }

    protected Set<JAP_ENTITY> _findAll(FindAllArgs args) {
        String[] sortProperties = args.getSort() != null ? args.getSort() : new String[]{"id"};
        Sort.Direction direction = args.getDesc() ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, sortProperties);
        Pageable pageable = PageRequest.of(args.getPage(), args.getSize(), sort);
        return jpaRepository.findAll(pageable).toSet();
    }

    @Override
    public Mono<ENTITY> findById(ID id) {
         return Mono.just(toDomain(_findById(id)));
    }

    protected JAP_ENTITY _findById(ID id) {
         return jpaRepository.findById(id).orElse(null);
    }

    @Override
    public Mono<ENTITY> save(ENTITY entity) {
         return Mono.just(toDomain(
            jpaRepository.save(toEntity(entity)
         )));
    }

    @Override
    public Flux<ENTITY> bulkInsert(List<ENTITY> entities) {
        List<JAP_ENTITY> _entities = toEntities(entities);
        int batchSize = 100;
        EntityManager em =entityManagerFactory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            for (int i = 0; i < _entities.size(); i++) {
                JAP_ENTITY entity = _entities.get(i);
                em.persist(entity);
                if (i % batchSize == 0 && i> 0) {
                    em.flush();
                    em.clear();
                }
            }
            em.flush();
            em.clear();
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
        return  Flux.fromIterable(toDomains(_entities));
    }

    @Override
    public Mono<Void> deleteById(ID id) {
         jpaRepository.deleteById(id);
        return Mono.empty();
    }

    protected List<JAP_ENTITY> toEntities(List<ENTITY> entities) {
         return entities.stream().map(p-> toEntity(p)).toList();
    }
    protected List<ENTITY> toDomains(List<JAP_ENTITY> entities) {
        return entities.stream().map(p-> toDomain(p)).toList();
    }


    protected abstract ENTITY toDomain(JAP_ENTITY japEntity);
    protected abstract JAP_ENTITY toEntity(ENTITY entity);
    protected abstract Class<JAP_ENTITY> classEntity();
}
