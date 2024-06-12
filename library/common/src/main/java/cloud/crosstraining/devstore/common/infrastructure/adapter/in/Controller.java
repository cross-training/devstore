package cloud.crosstraining.devstore.common.infrastructure.adapter.in;

import cloud.crosstraining.devstore.common.application.service.Service;
import cloud.crosstraining.devstore.common.domain.Entity;
import cloud.crosstraining.devstore.common.domain.ErrorMessage;
import cloud.crosstraining.devstore.common.domain.FindAllArgs;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequestMapping
public abstract class Controller<ENTITY extends  Entity<ID>,ID> {

    private final Service<ENTITY,ID> service;

    @Autowired
    public Controller(Service<ENTITY,ID> service) {
        this.service = service;
    }

    @GetMapping
    public Flux<ENTITY> getAll(String filters, String includes, Integer page, Integer size, String sort, Boolean desc) {
        FindAllArgs args = new FindAllArgs(filters,includes,page,size,sort,desc);
        return service.getAll(args);
    }

    @GetMapping("/{id}")
    public Mono<ENTITY> getById(@PathVariable ID id) {
        log.info("Fetching with id: {}", id);
        return service.getById(id);
    }

    @PostMapping
    public Mono<ENTITY> create(@Valid @RequestBody ENTITY entity, BindingResult result) {
        log.info("Creating: {}", result);
        if (result.hasErrors()){
            String errorMessage = this.formatMessage(entity.getClass().getCanonicalName(), result);
            log.error(errorMessage);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,errorMessage );
        }
        return service.create(entity);
    }

    @PostMapping("/bulk")
    public Flux<ENTITY> bulkInsert(@Valid @RequestBody List<ENTITY> entities, BindingResult result ) {
        if (entities == null || entities.size() == 0) {
            String errorMessage = "entities can't empty";
            log.error(errorMessage);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,errorMessage);
        }
        log.info("Bulk Creating: {}", entities);
        if (result.hasErrors()){
            String errorMessage = this.formatMessage(entities.stream().findFirst().getClass().getCanonicalName(), result);
            log.error(errorMessage);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,errorMessage );
        }
        return service.bulkInsert(entities);
    }

    @PutMapping("/{id}")
    public Mono<ENTITY> update(@PathVariable ID id, @Valid @RequestBody ENTITY entity, BindingResult result) {
        log.info("Update {}", entity);
        if (result.hasErrors()){
            String errorMessage = this.formatMessage(entity.getClass().getCanonicalName(), result);
            log.error(errorMessage);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,errorMessage );
        }
        return service.update(id, entity);
    }

    @PatchMapping("/{id}")
    public Mono<ENTITY> updatePartially(@PathVariable ID id, @RequestBody Map<String, Object> updates) {
        log.info("Update id: {} updates: {}", id, updates);
        return service.updatePartially(id, updates);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable ID id) {
        log.info("Delete id: {}", id);
        service.deleteById(id);
    }

    private String formatMessage(String code,BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String>  error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code(code)
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
