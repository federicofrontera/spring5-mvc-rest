package ff.spring5mvcrest.controllers;

import ff.spring5mvcrest.api.model.CategoryDTO;
import ff.spring5mvcrest.controllers.v1.CategoryController;
import ff.spring5mvcrest.services.CategoryService;
import org.junit.BeforeClass;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

public class CategoryControllerTest {
    public static final String TEST_NAME_1 = "fruits";
    public static final String TEST_NAME_2 = "dried";

    @Mock
    CategoryService categoryService;

    @InjectMocks
    CategoryController categoryController;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    public void testListCategories() throws Exception{
        CategoryDTO category1 = new CategoryDTO();
        category1.setId(Long.valueOf(1));
        category1.setName(TEST_NAME_1);

        CategoryDTO category2 = new CategoryDTO();
        category1.setId(Long.valueOf(2));
        category1.setName(TEST_NAME_2);

        List<CategoryDTO> categoryDTOList = Arrays.asList(category1, category2);

        when(categoryService.getAllCategories()).thenReturn(categoryDTOList);

        mockMvc.perform(get(CategoryController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categories", hasSize(2)));
    }

    @Test
    public void testGetByNameCategories() throws Exception {
        CategoryDTO category1 = new CategoryDTO();
        category1.setId(1l);
        category1.setName(TEST_NAME_1);

        when(categoryService.getCategoryByName(anyString())).thenReturn(category1);

        mockMvc.perform(get(CategoryController.BASE_URL + "/fruits")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(TEST_NAME_1)));
    }
}
