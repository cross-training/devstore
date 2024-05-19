package cloud.crosstraining.devstore.infrastructure.adapter.in;

import cloud.crosstraining.devstore.application.port.in.Service;
import cloud.crosstraining.devstore.domain.Entity;
import cloud.crosstraining.devstore.domain.FindAllArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RequestMapping
public abstract class Controller<ENTITY extends  Entity<ID>,ID> {

    private final Service<ENTITY,ID> service;

    @Autowired
    public Controller(Service<ENTITY,ID> service) {
        this.service = service;
    }

    @GetMapping
    public List<ENTITY> getAll(String filters, String includes,Integer page, Integer size,String sort, Boolean desc) {
        FindAllArgs args = new FindAllArgs(filters,includes,page,size,sort,desc);
        return service.getAll(args);
    }

    @GetMapping("/{id}")
    public ENTITY getById(@PathVariable ID id) {
        return service.getById(id);
    }

    @PostMapping
    public ENTITY create(@RequestBody ENTITY entity) {
        return service.create(entity);
    }

    @PutMapping("/{id}")
    public ENTITY update(@PathVariable ID id, @RequestBody ENTITY entity) {
        return service.update(id, entity);
    }

    @PatchMapping("/{id}")
    public ENTITY updatePartially(@PathVariable ID id, @RequestBody Map<String, Object> updates) {
        return service.updatePartially(id, updates);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable ID id) {
        service.deleteById(id);
    }
}
