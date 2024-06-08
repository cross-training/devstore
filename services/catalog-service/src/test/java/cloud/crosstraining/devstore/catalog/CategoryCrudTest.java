package cloud.crosstraining.devstore.catalog;
import cloud.crosstraining.devstore.catalog.application.port.out.CategoryRepository;
import cloud.crosstraining.devstore.catalog.application.service.CategoryServiceImpl;
import cloud.crosstraining.devstore.catalog.domain.Category;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
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
		Category category = new Category("laptops","laptops","Portable computers suitable for various tasks");
		when(categoryRepository.save(any())).thenReturn(Mono.just(category));
		Category savedCategory = service.create(category).block();

		// find
		when(categoryRepository.findById(savedCategory.getId())).thenReturn(Mono.just(savedCategory));
		Category retrievedCategory = service.getById(savedCategory.getId()).block();
		assertThat(retrievedCategory).isEqualTo(savedCategory);

		// update
		Category updatedCategory = service.getById(category.getId()).block();
		updatedCategory.setName("Laptops");
		when(categoryRepository.save(any())).thenReturn(Mono.just(updatedCategory));
		Category modifiedCategory = service.update(updatedCategory.getId(), updatedCategory).block();
		assertThat(modifiedCategory).isEqualTo(updatedCategory);

		// delete
		when(categoryRepository.deleteById(updatedCategory.getId())).thenReturn(Mono.empty());
		Mono<Void> deleteMono = service.deleteById(updatedCategory.getId());
		verify(categoryRepository, times(1)).deleteById(updatedCategory.getId());
	}
}
