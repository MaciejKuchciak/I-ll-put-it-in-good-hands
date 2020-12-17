package pl.coderslab.charity.service;

import pl.coderslab.charity.entity.Category;

import java.util.List;

public interface CategoryService {

    void addCategory(Category category);

    List<Category> getAllCategories();
}
