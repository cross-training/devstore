package cloud.crosstraining.devstore.rating.infrastructure.adapter.out;

import cloud.crosstraining.devstore.common.infrastructure.adapter.out.RepositoryImpl;
import cloud.crosstraining.devstore.rating.application.port.out.RatingRepository;
import cloud.crosstraining.devstore.rating.domain.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManagerFactory;
import java.time.*;
import java.util.Optional;

@Repository
public class RatingRepositoryImpl extends RepositoryImpl<Rating,Long,RatingEntity> implements RatingRepository {

    @Autowired
    public RatingRepositoryImpl(RatingJpaRepository jpaRepository, EntityManagerFactory entityManagerFactory) {
        super(jpaRepository,entityManagerFactory);
    }

    @Override
    protected Class<RatingEntity> classEntity() {
        return RatingEntity.class;
    }

    @Override
    protected Rating toDomain(RatingEntity entity) {
        return new Rating(entity.getId(),entity.getProductId(),entity.getEmail(),entity.getRating(),entity.getDatetime());
    }

    @Override
    protected RatingEntity toEntity(Rating entity) {
        return new RatingEntity(entity.getId(),entity.getProductId(),entity.getEmail(),entity.getRating(),
                Optional.ofNullable( entity.getDatetime() ).orElse(OffsetDateTime.ofInstant(Instant.now(), ZoneOffset.UTC))
        );
    }


}
