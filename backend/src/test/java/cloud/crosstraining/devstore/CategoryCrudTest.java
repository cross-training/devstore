package cloud.crosstraining.devstore;
import cloud.crosstraining.devstore.application.port.in.CategoryService;
import cloud.crosstraining.devstore.application.port.out.CategoryRepository;
import cloud.crosstraining.devstore.application.service.CategoryServiceImpl;
import cloud.crosstraining.devstore.domain.Category;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryCrudTest {

	@Mock
	private CategoryRepository categoryRepository;

	@InjectMocks
	private CategoryServiceImpl service;

	@Test
	public void testCrud() {
		// create
		Category category = new Category(null,"laptops","laptops","Portable computers suitable for various tasks");
		when(categoryRepository.save(any())).thenReturn(category);
		category.setId(1L);
		Category savedCategory = service.create(category);

		// find
		when(categoryRepository.findById(savedCategory.getId())).thenReturn(savedCategory);
		Category retrievedCategory = service.getById(savedCategory.getId());
		assertThat(retrievedCategory).isEqualTo(savedCategory);

		// update
		Category updatedCategory = service.getById(category.getId());
		updatedCategory.setName("Laptops");
		when(categoryRepository.save(any())).thenReturn(updatedCategory);
		Category modifiedCategory = service.update(updatedCategory.getId(), updatedCategory);
		assertThat(modifiedCategory).isEqualTo(updatedCategory);

		// delete
		doNothing().when(categoryRepository).deleteById(updatedCategory.getId());
		service.deleteById(updatedCategory.getId());
		verify(categoryRepository, times(1)).deleteById(updatedCategory.getId());
	}
}
