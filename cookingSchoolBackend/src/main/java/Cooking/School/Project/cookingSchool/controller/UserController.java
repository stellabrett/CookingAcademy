package Cooking.School.Project.cookingSchool.controller;

import Cooking.School.Project.cookingSchool.Services.CourseService;
import Cooking.School.Project.cookingSchool.Services.UserService;
import Cooking.School.Project.cookingSchool.entities.Course;
import Cooking.School.Project.cookingSchool.entities.User;
import Cooking.School.Project.cookingSchool.exceptions.UserNotFoundException;
import Cooking.School.Project.cookingSchool.restapi.dto.CourseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    private CourseService courseService;


    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
            User user = userService.getUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id){
            userService.deleteUserById(id);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }

    /**
     * updates a user from user site
     * @param userId in PathVariable
     * @param updatedUser JSON - in the requestbody
     * @return
     */
    @PutMapping("/users/{userId}")
    public ResponseEntity<?> updateUser(
            @PathVariable Long userId, @RequestBody User updatedUser){
            userService.updateUser(userId, updatedUser);
            return new ResponseEntity<>("User updated successfully", HttpStatus.OK);

    }

    @PostMapping("/users/{userId}/book-course/{courseId}")
    public ResponseEntity<?> bookCourse(@PathVariable Long userId, @PathVariable Long courseId) {
        userService.bookCourse(userId, courseId);
        return ResponseEntity.ok("Course booked successfully");
    }


    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody User user){
            userService.registration(user);
            return ResponseEntity.ok("successfully registered");
    }

    /*
    @GetMapping("/courses/{courseId}")
    public ResponseEntity<Course> getUserCourseById(@PathVariable Long courseId) {
        Course course = courseService.getUserCourseById(courseId);
        return ResponseEntity.ok(course);
    }*/
}

