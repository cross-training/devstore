package cloud.crosstraining.devstore.infrastructure.adapter.out;

import cloud.crosstraining.devstore.domain.Entity;
import cloud.crosstraining.devstore.domain.FindAllArgs;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import cloud.crosstraining.devstore.application.port.out.Repository;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class RepositoryImpl<ENTITY extends Entity<ID>, ID, JAP_ENTITY> implements Repository<ENTITY, ID> {

    protected final JpaRepository<JAP_ENTITY, ID> jpaRepository;
    protected final EntityManagerFactory entityManagerFactory;

     public RepositoryImpl(JpaRepository<JAP_ENTITY, ID> jpaRepository,EntityManagerFactory entityManagerFactory) {
        this.jpaRepository = jpaRepository;
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public List<ENTITY> findAll(FindAllArgs args) {
        return _findAll(args).stream()
                .map(this::toDomain)
                .toList();
    }

    protected Set<JAP_ENTITY> _findAll(FindAllArgs args) {
        return jpaRepository.findAll().stream().collect(Collectors.toSet());
        // TODO: solve pagination
        // String[] sort = args.getSort()!=null?args.getSort(): new String[]{"id"};
        // Sort.Direction direction = args.getDesc()?Sort.Direction.DESC:Sort.Direction.ASC;
        // PageRequest pageRequest = PageRequest.of(args.getPage(),args.getSize(),direction,  sort);
        // return jpaRepository.findAll(pageRequest).toSet();
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
}
