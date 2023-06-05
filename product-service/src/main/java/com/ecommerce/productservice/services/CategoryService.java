package com.ecommerce.productservice.services;

import com.ecommerce.productservice.models.Category;
import com.ecommerce.productservice.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAll(){
        return categoryRepository.findAll() ;
    }

    public Category createCategory ( Category category){
        return categoryRepository.save(category);
    }

    public void deleteCategory (Long id) {
        categoryRepository.deleteById(id);
    }


    public Category updateCategory ( Category category,  Long id){
        Category c1 = categoryRepository.findById(id).orElse(null);
        if (c1 !=null){
            category.setId(id);
            return categoryRepository.saveAndFlush(category);
        }
        else{
            throw new RuntimeException("fail");
        }

    }
}
