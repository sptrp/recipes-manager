package recipes.main.presentationLayer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import recipes.main.businessLayer.model.Recipe;
import recipes.main.businessLayer.model.User;
import recipes.main.businessLayer.service.RecipeService;
import recipes.main.businessLayer.service.UserService;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.*;

@RestController
public class RecipeRestController {

    @Autowired
    RecipeService recipeService;

    @Autowired
    UserService userService;

    @GetMapping("/api/recipe/{id}")
    public Recipe getRecipe(@PathVariable Long id) {

        return recipeService.findRecipeById(id);
    }

    @GetMapping("/api/recipe/search")
    public List<Recipe> searchRecipe(@Valid @RequestParam(required = false) String category, @Valid @RequestParam(required = false) String name) {

        if (category != null) {
            return recipeService.findRecipesByCategory(category);
        }
        return recipeService.findRecipesByName(name);
    }

    @PostMapping("/api/recipe/new")
    public ResponseEntity<Map<String, Long>> saveRecipe(@Valid @RequestBody Recipe recipe) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.findUser(userName);

        recipe.setAuthor(currentUser);

        Recipe recipeCreate = recipeService.save(recipe);

        Map<String, Long> responseMessage = new HashMap<>() {{ put("id", recipeCreate.getId()); }};
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/api/recipe/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteRecipe(@PathVariable Long id) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.findUser(userName);

        Recipe recipe = recipeService.findRecipeById(id);

        if (currentUser.getRecipes().contains(recipe)) {
            recipeService.deleteRecipeById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/api/recipe/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateRecipe(@PathVariable Long id, @Valid @RequestBody Recipe recipe) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.findUser(userName);

        Recipe checkRecipe = recipeService.findRecipeById(id);

        if (currentUser.getRecipes().contains(checkRecipe)) {
            recipeService.updateRecipeById(id, recipe);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

}
