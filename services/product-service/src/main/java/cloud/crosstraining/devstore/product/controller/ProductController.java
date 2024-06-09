package cloud.crosstraining.devstore.product.controller;

import cloud.crosstraining.devstore.product.entity.Product;
import cloud.crosstraining.devstore.product.service.ProductService;
import cloud.crosstraining.devstore.product.service.ProductServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
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

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public Flux<Product> list() {
        return Flux.fromIterable(service.list());
    }

    @GetMapping("/{id}")
    public Mono<Product> find(@PathVariable Long id) {
        return Mono.just(service.find(id));
    }

    @PostMapping
    public Mono<Product> save(@Valid @RequestBody Product product, BindingResult result) {
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY,formatMessage(result));
        }
        return Mono.just(service.create(product));
    }

    private String formatMessage(BindingResult result) {
        List<Map<String,String>> errors = result.getAllErrors()
                .stream()
                .map(p-> {
                    Map<String,String> error = new HashMap<>();
                    error.put(p.getObjectName(),p.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder().code("01").messages(errors).build();
        String json="";
        ObjectMapper mapper = new ObjectMapper();
        try  {
            json = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

}
