package cloud.crosstraining.devstore.rating.application.service;

import cloud.crosstraining.devstore.common.application.service.ServiceImpl;
import cloud.crosstraining.devstore.rating.application.port.in.RatingService;
import cloud.crosstraining.devstore.rating.application.port.out.RatingRepository;
import cloud.crosstraining.devstore.rating.domain.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl extends ServiceImpl<Rating,Long> implements RatingService {

    @Autowired
    public RatingServiceImpl(RatingRepository repository) {
        super(repository);
    }
}
