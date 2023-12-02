/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Employees;

/**
 *
 * @author Sarath
 */
public class Employees {
    private int id;
    private String name;
    private String gender;
    private String DOB;
    private String phoneNumber;
    private String email;
    private String salary;
    private String address;
    private String hiredate;

    public Employees(int id, String name, String gender, String DOB, String phoneNumber, String email, String salary, String address, String hiredate) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.DOB = DOB;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.salary = salary;
        this.address = address;
        this.hiredate = hiredate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getDOB() {
        return DOB;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getSalary() {
        return salary;
    }

    public String getAddress() {
        return address;
    }

    public String getHiredate() {
        return hiredate;
    }
    
    
}
