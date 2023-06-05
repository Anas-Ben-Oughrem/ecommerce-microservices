package com.ecommerce.productservice.controllers;

import com.ecommerce.productservice.models.Subcategory;
import com.ecommerce.productservice.repositories.CategoryRepository;
import com.ecommerce.productservice.repositories.SubcategoryRepository;
import com.ecommerce.productservice.services.SubcategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subcategories")
@CrossOrigin("*")
@RequiredArgsConstructor
public class SubcategoryController {

    private final SubcategoryService subcategoryService;

    private final SubcategoryRepository subcategoryRepository;

    private final CategoryRepository categoryRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Subcategory> getAll() {
        return subcategoryService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Subcategory createSubcategory(@RequestBody Subcategory s) {
        return subcategoryService.createSubcategory(s);
    }
//    @PostMapping("/{id_categorie}")
//    public Subcategory creatSubcategory(@RequestBody Subcategory s, @PathVariable Long id_categorie) {
//        return subcategoryService.createSubcategoryService(s, id_categorie);
//    }
//    @GetMapping("/Subcategory/{id_categ}")
//
//    public ResponseEntity<List<Subcategory>> listCateg(@PathVariable Long id_categorie) {
//
//        Optional<Subcategory> Subcategorys = subcategoryRepository.findById(id_categorie);
//
//        return new ResponseEntity<List<Subcategory>>(Subcategory, HttpStatus.OK);
//    }




    @GetMapping("/getOne/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Subcategory getOnesubcategory(@PathVariable Long id) {
        return subcategoryRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Subcategory updateSubcategory(@RequestBody Subcategory s, @PathVariable Long id) {
        return subcategoryService.updateSubcategory(s, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSubcategory(@PathVariable Long id) {
        subcategoryService.deleteSubcategory(id);
    }
}

