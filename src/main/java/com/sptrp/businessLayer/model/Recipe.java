package com.sptrp.businessLayer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity(name = "recipe")
@Table(name = "recipes")
public class Recipe {

	@Id
	@JsonIgnore
	@Column(name = "recipe_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	@NotBlank
	private String name;

	@Column
	@NotBlank
	private String description;

	@Column
	@NotEmpty
	@ElementCollection
	private List<String> directions;

	@Column
	@NotEmpty
	@ElementCollection
	private List<String> ingredients;

	@Column
	@NotBlank
	private String category;

	@Column
	@Size(min = 8)
	private String date;

	@JsonIgnore
	@ManyToOne
	private User author;


	public Recipe() {
		this.date = LocalDateTime.now().toString();
	}

	public Long getId() {
		return this.id;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setDirections(List<String> directions){
		this.directions = directions;
	}

	public List<String> getDirections(){
		return directions;
	}

	public void setIngredients(List<String> ingredients){
		this.ingredients = ingredients;
	}

	public List<String> getIngredients(){
		return ingredients;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDate() {
		return date;
	}

	public void setAuthor(User user) {
		author = user;
	}

	public User getAuthor() {
		return author;
	}

	@Override
 	public String toString(){
		return
			"Recipe2{" +
			"directions = '" + directions + '\'' +
			",name = '" + name + '\'' +
			",description = '" + description + '\'' +
			",ingredients = '" + ingredients + '\'' +
			"}";
		}

}
