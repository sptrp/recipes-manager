package recipes.main.businessLayer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import recipes.main.businessLayer.model.Recipe;
import recipes.main.persistenceLayer.RecipeRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Recipe findRecipeById(Long id) {
        if (recipeRepository.findById(id).equals(Optional.empty())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        } else {
            return recipeRepository.findRecipeById(id);
        }
    }

    public List<Recipe> findRecipesByName(String name) {

        var recipes = recipeRepository.findByNameContainsIgnoreCaseOrderByDateDesc(name);

        return recipes.isEmpty() ? new ArrayList<>() : recipes;
    }

    public List<Recipe> findRecipesByCategory(String category) {

        var recipes = recipeRepository.findByCategoryIgnoreCaseOrderByDateDesc(category);

        return recipes.isEmpty() ? new ArrayList<>() : recipes;
    }


    public Recipe save(Recipe recipeToSave) {
        return recipeRepository.save(recipeToSave);
    }

    public void deleteRecipeById(Long id) {
        if (recipeRepository.findById(id).equals(Optional.empty())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        } else {
            recipeRepository.deleteRecipeById(id);
        }
    }

    public void updateRecipeById(Long id, Recipe recipeUpdate) {

        Recipe recipeToSave = recipeRepository.findRecipeById(id);

        if (recipeToSave == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        } else {
            recipeToSave.setName(recipeUpdate.getName());
            recipeToSave.setDescription(recipeUpdate.getDescription());
            recipeToSave.setDirections(recipeUpdate.getDirections());
            recipeToSave.setIngredients(recipeUpdate.getIngredients());
            recipeToSave.setCategory(recipeUpdate.getCategory());
            recipeToSave.setDate(LocalDateTime.now().toString());

            recipeRepository.save(recipeToSave);
        }
    }

}
