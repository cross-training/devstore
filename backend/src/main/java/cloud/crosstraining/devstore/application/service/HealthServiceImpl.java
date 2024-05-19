package cloud.crosstraining.devstore.application.service;

import org.springframework.stereotype.Service;
import cloud.crosstraining.devstore.application.port.in.HealthService;

@Service
public class HealthServiceImpl implements  HealthService {

    @Override
    public String health() {
        return "Service is up and running!";
    }
}
