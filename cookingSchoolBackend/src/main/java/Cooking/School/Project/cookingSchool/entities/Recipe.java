package Cooking.School.Project.cookingSchool.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(generator = "recipeSequence")
    @GenericGenerator(
            name = "recipeSequence",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "user_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
@Column(name = "recipeId")
    private Long recipeId;
@Column(name = "title", nullable = false)
    private String title;

@Column(name = "description", nullable = false)
    private String description;


    //TODO lieber Enum oder Klasse?
    /*My best friend said: Welche Option du wählst, hängt von den spezifischen Anforderungen deiner Anwendung ab.
    Enums sind oft eine gute Wahl, wenn die Schwierigkeitsgrade fest und vorhersehbar sind. Numerische Werte könnten einfacher zu handhaben sein, wenn du
    mathematische Vergleiche oder Sortierungen durchführen möchtest. Eine benutzerdefinierte Klasse bietet mehr Flexibilität für komplexe Anforderungen.
     */

    @Column(name = "difficulty", nullable = false)
    private int difficulty;

    @Column(name = "duration", nullable = false)
    private String duration;

    @Column(name = "ingredients", nullable = false)
    private String ingredients;

    //TODO String?
    @Column(name = "preparation", nullable = false)
    private String preparation;

    //TODO checken ob eigene Entity sinnvoll wäre mit one-to-many auf recipe?
    //@ElementCollection
    //private Set<String> categories;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "recipe_course",
            joinColumns = @JoinColumn(name = "recipeId"),
            inverseJoinColumns = @JoinColumn(name = "courseId"))
    private Set <Course> course = new HashSet<>();

    public Recipe(){

    }

    public Recipe(Long recipeId, String title, String description, int difficulty, String duration, String ingredients, String preparation) {
        this.recipeId = recipeId;
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.duration = duration;
        this.ingredients = ingredients;
        this.preparation = preparation;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    //public Set<String> getCategories() {
      //  return categories;
    //}

    //public void setCategories(Set<String> categories) {
       // this.categories = categories;
    //}

   /* public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    } */
}