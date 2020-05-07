package ff.spring5mvcrest.api.mapper;

import ff.spring5mvcrest.api.model.CategoryDTO;
import ff.spring5mvcrest.domain.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryMapperTest {
    public static final String TEST_NAME = "test name";
    public static final int ID = 1;
    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    public void CategoryToCategoryDTOTest() throws Exception {
        //given
        Category category = new Category();
        category.setId(Long.valueOf(ID));
        category.setName(TEST_NAME);

        //when
        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        //then
        assertEquals(Long.valueOf(ID), categoryDTO.getId());
        assertEquals(TEST_NAME, categoryDTO.getName());
    }
}