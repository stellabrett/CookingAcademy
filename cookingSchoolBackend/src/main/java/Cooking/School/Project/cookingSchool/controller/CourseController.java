package Cooking.School.Project.cookingSchool.controller;

import Cooking.School.Project.cookingSchool.Services.CourseService;
import Cooking.School.Project.cookingSchool.entities.Course;
import Cooking.School.Project.cookingSchool.exceptions.CourseNotFoundException;
import Cooking.School.Project.cookingSchool.repository.CourseTagRepository;
import Cooking.School.Project.cookingSchool.restapi.dto.CourseTagsRecipeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseTagRepository courseTagRepository;


    @GetMapping("/courses")
    public ResponseEntity<List<CourseTagsRecipeResponse>> getAllCourses(){
        try{
            List<CourseTagsRecipeResponse> courseTagsRecipeResponses = courseService.getAllCourses();
            return new ResponseEntity<>(courseTagsRecipeResponses,HttpStatus.OK );
        }catch (CourseNotFoundException cnfe){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/courses/details/{id}")
    public ResponseEntity<Course> getCourseDetails(@PathVariable Long id) {
        Course course = courseService.getCourseDetails(id);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }
    @GetMapping("/courses/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Course course = courseService.getCourseById(id);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

}
