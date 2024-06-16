package cloud.crosstraining.devstore.rating.infrastructure.adapter.in;

import cloud.crosstraining.devstore.common.infrastructure.adapter.in.Controller;
import cloud.crosstraining.devstore.rating.application.service.RatingService;
import cloud.crosstraining.devstore.rating.domain.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rating")
public class RatingController extends Controller<Rating,Long> {

    @Autowired
    public RatingController(RatingService service) {
        super(service);
    }
}
