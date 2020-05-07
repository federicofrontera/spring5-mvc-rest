package ff.spring5mvcrest.services;

import ff.spring5mvcrest.api.model.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryByName(String name);
}
