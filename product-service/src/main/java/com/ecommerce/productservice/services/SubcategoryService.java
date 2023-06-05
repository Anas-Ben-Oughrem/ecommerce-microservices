package com.ecommerce.productservice.services;

import com.ecommerce.productservice.models.Category;
import com.ecommerce.productservice.models.Subcategory;
import com.ecommerce.productservice.repositories.CategoryRepository;
import com.ecommerce.productservice.repositories.SubcategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubcategoryService {

    private final SubcategoryRepository subcategoryRepository;

    private final CategoryRepository categoryRepository;

    public List<Subcategory> getAll(){
        return subcategoryRepository.findAll() ;
    }

    public Subcategory createSubcategory ( Subcategory s) {
        return subcategoryRepository.save(s);
    }

    public Subcategory createSubcategory ( Subcategory s , Long id_category){
        Category c = categoryRepository.findById(id_category).orElse(null);
        s.setCategory(c);
        return subcategoryRepository.save(s);
    }

    public void deleteSubcategory (Long id) {
        subcategoryRepository.deleteById(id);
    }

    public Subcategory updateSubcategory ( Subcategory s, Long id){
        Subcategory s1 = subcategoryRepository.findById(id).orElse(null);
        if (s1 !=null){
            s.setId(id);
            return subcategoryRepository.saveAndFlush(s);
        }
        else{
            throw new RuntimeException("fail");
        }

    }
}
