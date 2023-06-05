package com.ecommerce.productservice.controllers;

import com.ecommerce.productservice.models.Category;
import com.ecommerce.productservice.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
@CrossOrigin("*")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    @GetMapping("/all")
    public List<Category> getAllcategory() {
        return categoryRepository.findAll();
    }

    @PostMapping("/save")
    public Category savecategory(@RequestBody Category c) {
        return categoryRepository.save(c);
    }

    @GetMapping ("/getone/{id}")
    public Category getOnecategory(@PathVariable Long id){
        return categoryRepository.findById(id).orElse(null);
    }

    @PutMapping("/update/{Id}")
    public Category update(@RequestBody Category c, @PathVariable Long Id) {

        Category c1 = categoryRepository.findById(Id).orElse(null);
        if (c1 != null) {
            c.setId(Id);
            return categoryRepository.saveAndFlush(c);
        }
        else{
            throw new RuntimeException("FAIL!");
        }


    }

    @DeleteMapping("/delete/{Id}")
    public HashMap<String,String> deleteCategory(@PathVariable Long Id) {
        HashMap message= new HashMap();
        try{
            categoryRepository.deleteById(Id);
            message.put("etat","category deleted");
            return message;
        }
        catch (Exception e) {
            message.put("etat","category not deleted");
            return message;
        }
    }
}
