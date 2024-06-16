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
        return Rating.builder()
                .id(entity.getId())
                .productId(entity.getProductId())
                .email(entity.getEmail())
                .rating(entity.getRating())
                .createAt(Optional.ofNullable( entity.getCreateAt() ).orElse(OffsetDateTime.ofInstant(Instant.now(), ZoneOffset.UTC)))
                .build();
    }

    @Override
    protected RatingEntity toEntity(Rating domain) {
        return RatingEntity.builder()
                .id(domain.getId())
                .productId(domain.getProductId())
                .email(domain.getEmail())
                .rating(domain.getRating())
                .createAt(Optional.ofNullable( domain.getCreateAt() ).orElse(OffsetDateTime.ofInstant(Instant.now(), ZoneOffset.UTC)))
                .build();
    }
}
