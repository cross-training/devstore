package cloud.crosstraining.devstore.catalog.application.service;

import cloud.crosstraining.devstore.catalog.application.port.out.CategoryRepository;
import cloud.crosstraining.devstore.catalog.domain.Category;
import cloud.crosstraining.devstore.common.application.service.Service;
import org.springframework.beans.factory.annotation.Autowired;


public class CategoryService extends Service<Category,String> {

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        super(categoryRepository);
    }
}
