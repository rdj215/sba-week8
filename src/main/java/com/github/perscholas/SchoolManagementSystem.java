package com.github.perscholas;

import com.github.perscholas.dao.StudentDao;
import com.github.perscholas.service.StudentService;
import com.github.perscholas.utils.IOConsole;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class SchoolManagementSystem implements Runnable {
    private static final IOConsole console = new IOConsole();

    @Override
    public void run() {
        String smsDashboardInput = getSchoolManagementSystemDashboardInput();
        if ("login".equals(smsDashboardInput.trim())) {
            StudentDao studentService = new StudentService(DatabaseConnection.MARIADB);
            String studentEmail = console.getStringInput("Enter your email:");
            String studentPassword = console.getStringInput("Enter your password:");
            Boolean isValidLogin = studentService.validateStudent(studentEmail, studentPassword);
            if (isValidLogin) {
                while (true){
                String studentDashboardInput = getStudentDashboardInput();
                if ("register".equals(studentDashboardInput)) {
                    Integer courseId = getCourseRegistryInput();
                    studentService.registerStudentToCourse(studentEmail, courseId);
                }else if("courses".equals(studentDashboardInput.trim())){
                    System.out.println(studentService.getStudentCourses(studentEmail));
                }else {
                    break;
                  }
                }
            }
        }
    }

    private String getSchoolManagementSystemDashboardInput() {
        return console.getStringInput(new StringBuilder()
                .append("Welcome to the School Management System Dashboard!")
                .append("\nFrom here, you can select any of the following options:")
                .append("\n\t[ login ], [ logout ]")
                .toString());
    }

    private String getStudentDashboardInput() {
        return console.getStringInput(new StringBuilder()
                .append("Welcome to the Student Dashboard!")
                .append("\nFrom here, you can select any of the following options:")
                .append("\n\t[ register ], [courses], [ logout]")
                .toString());
    }


    private Integer getCourseRegistryInput() {
        List<Integer> listOfCoursesIds = new ArrayList<>();
        return console.getIntegerInput(new StringBuilder()
                .append("Welcome to the Course Registration Dashboard!")
                .append("\nFrom here, you can select any of the following options:")
                .append("\n\t" + listOfCoursesIds.toString())
                .toString());
    }
}
