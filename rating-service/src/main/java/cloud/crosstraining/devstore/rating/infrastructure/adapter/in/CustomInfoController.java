package cloud.crosstraining.devstore.rating.infrastructure.adapter.in;

import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomInfoController {

    private final InfoEndpoint infoEndpoint;

    public CustomInfoController(InfoEndpoint infoEndpoint) {
        this.infoEndpoint = infoEndpoint;
    }

    @GetMapping("/info")
    public Object info() {
        return infoEndpoint.info();
    }
}
