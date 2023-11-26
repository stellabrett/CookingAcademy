package Cooking.School.Project.cookingSchool.controller;

import Cooking.School.Project.cookingSchool.Services.*;
import Cooking.School.Project.cookingSchool.entities.*;
import Cooking.School.Project.cookingSchool.exceptions.*;
import Cooking.School.Project.cookingSchool.restapi.inputParams.CourseInputParam;

import Cooking.School.Project.cookingSchool.restapi.inputParams.CourseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class AdminController {

    @Autowired
   private CourseService courseService;

    @Autowired
    private UserService userService;

    @Autowired
    private TagService tagService;

//-------------------------------- Admin course
    @PostMapping("admin/courses")
    public ResponseEntity<?> createCourse(@RequestBody CourseRequest request) {
        try {
            Long courseId = courseService.createCourse(request);
            return ResponseEntity.ok("Kurs erfolgreich erstellt "+courseId);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("nonono");
        }
    }


    @GetMapping("admin/courses")
    public ResponseEntity<List<Course>> getAllCourses(){
        List<Course> courses = courseService.getAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @DeleteMapping("admin/courses/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id){
        try{
            courseService.deleteCourseById(id);
            return new ResponseEntity<>("Kurs erfolgreich gelöscht", HttpStatus.OK);
        } catch (PrimaryIdNullOrEmptyException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("admin/courses/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Long id, @RequestBody CourseInputParam param){
        try{
            courseService.updateCourseById(id, param);
            return new ResponseEntity<>("Kurs erfolgreich aktualisiert", HttpStatus.OK);
        } catch (PrimaryIdNullOrEmptyException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //------------------------- Admin tags

    @PostMapping("admin/courseTag")
    public CourseTag addCourseTag(@RequestBody CourseTag courseTag){
        return tagService.addCourseTag(courseTag);
    }



}
