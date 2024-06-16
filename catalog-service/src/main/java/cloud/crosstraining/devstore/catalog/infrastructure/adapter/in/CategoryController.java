package cloud.crosstraining.devstore.catalog.infrastructure.adapter.in;

import cloud.crosstraining.devstore.catalog.application.service.CategoryService;
import cloud.crosstraining.devstore.catalog.domain.Category;
import cloud.crosstraining.devstore.common.infrastructure.adapter.in.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/catalog/categories")
public class CategoryController extends Controller<Category, String> {

    @Autowired
    public CategoryController(CategoryService categoryService) {
        super(categoryService);
    }
}
