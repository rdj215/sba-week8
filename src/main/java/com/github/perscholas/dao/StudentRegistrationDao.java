package com.github.perscholas.dao;

import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.model.StudentInterface;

import java.util.List;

public interface StudentRegistrationDao {
    public List<CourseInterface> getAllRegisteredCourses(String studentEmail);
    public List<StudentInterface> getAllRegisteredStudents(Integer courseId);
}
