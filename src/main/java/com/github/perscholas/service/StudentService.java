package com.github.perscholas.service;

import com.github.perscholas.DatabaseConnection;
import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.model.CourseInterface;
import com.github.perscholas.model.Student;
import com.github.perscholas.model.StudentInterface;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// TODO - Implement respective DAO interface
public class StudentService implements StudentDao {

    private final DatabaseConnection dbc;

    public StudentService(DatabaseConnection dbc) {
        this.dbc = dbc;
    }

    public StudentService() {
        this(DatabaseConnection.MARIADB);
    }

    @Override
    public List<StudentInterface> getAllStudents() {
//        ResultSet result = dbc.executeQuery("SELECT * FROM students");
        List<StudentInterface> list = new ArrayList<>();
        try {
            ResultSet result = dbc.executeQuery("SELECT * FROM students");
            while (result.next()) {
                String studentEmail = result.getString("email");
                String name = result.getString("name");
                String password = result.getString("password");
                StudentInterface student = new Student(studentEmail, name, password);
                list.add(student);
            }
        } catch(SQLException se) {
            throw new Error(se);
        }

        return list;
    }


    public StudentInterface getStudentByEmail(String studentEmail) {
        try {
            String sqlStatement = "SELECT * FROM `student` " +
                    "WHERE `email`'=' " + studentEmail + "';'";
            ResultSet result = dbc.executeQuery(sqlStatement);
            while(result.next()){
                 String foundStudentEmail = result.getString("email");
                 String foundName = result.getString("name");
                 String foundPassword = result.getString("password");
                return new Student(foundStudentEmail,foundName,foundPassword);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean validateStudent(String studentEmail, String password) {
    StudentInterface student = getStudentByEmail(studentEmail);
    if(student != null){
        return password.equals(student.getPassword());
    }
        return false;
    }

    @Override
    public void registerStudentToCourse(String studentEmail, int courseId) {
    StudentInterface student = getStudentByEmail(studentEmail);
    CourseInterface course= new CourseService().getCourseById(courseId);
    String sqlStatement = "insert into studentRegistration (courseId, email) values (" + course.getId()+ student.getEmail()+"');'";
    }

    @Override
    public List<CourseInterface> getStudentCourses(String studentEmail) {
        return new StudentRegistrationService().getAllRegisteredCourses(studentEmail);

    }
}
