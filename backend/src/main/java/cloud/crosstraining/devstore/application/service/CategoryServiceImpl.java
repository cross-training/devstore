package cloud.crosstraining.devstore.application.service;

import cloud.crosstraining.devstore.application.port.in.CategoryService;
import cloud.crosstraining.devstore.application.port.out.CategoryRepository;
import cloud.crosstraining.devstore.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<Category,Long> implements CategoryService {

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        super(categoryRepository);
    }
}
