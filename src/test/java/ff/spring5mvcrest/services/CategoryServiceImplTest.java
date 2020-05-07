package ff.spring5mvcrest.services;

import ff.spring5mvcrest.api.mapper.CategoryMapper;
import ff.spring5mvcrest.api.model.CategoryDTO;
import ff.spring5mvcrest.domain.Category;
import ff.spring5mvcrest.repositories.CategoryRepository;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CategoryServiceImplTest {
    public static final long ID = 1L;
    public static final String TEST_NAME = "test name";
    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

   @BeforeEach
    public void setUp() throws Exception{
       MockitoAnnotations.initMocks(this);

       categoryService = new CategoryServiceImpl(CategoryMapper.INSTANCE, categoryRepository);
   }

   @Test
    public void testGetAllCategories() throws Exception{
       //given
       List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());
       when(categoryRepository.findAll()).thenReturn(categories);

       //when
       List<CategoryDTO> categoryDTOS = categoryService.getAllCategories();

       //then
       assertEquals(3, categoryDTOS.size());
   }

   @Test
    public void testGetCategoryByName() throws Exception{
       //given
       Category category = new Category();
       category.setId(ID);
       category.setName(TEST_NAME);
       when(categoryRepository.findByName(anyString())).thenReturn(category);

       //when
       CategoryDTO categoryDTO = categoryService.getCategoryByName(TEST_NAME);

       //then
       assertEquals(TEST_NAME, categoryDTO.getName());
       assertEquals(ID, categoryDTO.getId());

   }
}
