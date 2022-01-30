package com.sptrp.persistenceLayer;

import com.sptrp.businessLayer.model.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    Recipe findRecipeById(Long id);

    List<Recipe> findByNameContainsIgnoreCaseOrderByDateDesc(String name);
    List<Recipe> findByCategoryIgnoreCaseOrderByDateDesc(String category);

    void deleteRecipeById(Long id);
}
