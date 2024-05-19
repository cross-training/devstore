package cloud.crosstraining.devstore.infrastructure.adapter.out;

import cloud.crosstraining.devstore.domain.Entity;
import cloud.crosstraining.devstore.domain.FindAllArgs;
import cloud.crosstraining.devstore.domain.FindArgs;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import cloud.crosstraining.devstore.application.port.out.Repository;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public abstract class RepositoryImpl<ENTITY extends Entity<ID>, ID, JAP_ENTITY> implements Repository<ENTITY, ID> {

    protected final JpaRepository<JAP_ENTITY, ID> jpaRepository;
    protected final EntityManagerFactory entityManager;

     public RepositoryImpl(JpaRepository<JAP_ENTITY, ID> jpaRepository,EntityManagerFactory entityManager) {
        this.jpaRepository = jpaRepository;
        this.entityManager = entityManager;
    }

    @Override
    public List<ENTITY> findAll(FindAllArgs args) {
        return _findAll(args).stream()
                .map(this::toDomain)
                .toList();
    }

    protected List<JAP_ENTITY> _findAll(FindAllArgs args) {
        String[] sort = args.getSort()!=null?args.getSort(): new String[]{"id"};
        Sort.Direction direction = args.getDesc()?Sort.Direction.DESC:Sort.Direction.ASC;
        PageRequest pageRequest = PageRequest.of(args.getPage(),args.getSize(),direction,  sort);
        return jpaRepository.findAll(pageRequest).stream().toList();
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
