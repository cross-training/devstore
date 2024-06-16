package cloud.crosstraining.devstore.rating.application.service;

import cloud.crosstraining.devstore.common.application.service.Service;
import cloud.crosstraining.devstore.rating.application.port.out.RatingRepository;
import cloud.crosstraining.devstore.rating.domain.Rating;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class RatingService extends Service<Rating,Long>  {

    @Autowired
    public RatingService(RatingRepository repository) {
        super(repository);
    }
}
