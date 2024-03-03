package mta.qlnh.Client.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Users {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int id;
     private String name;
     private String username;
     private String password;
     private String position;
     private double salary;
     private int group_id;

     public Users() {
     }

     public Users(int id, String name, String username, String password, String position, double salary, int group_id) {
          this.id = id;
          this.name = name;
          this.username = username;
          this.password = password;
          this.position = position;
          this.salary = salary;
          this.group_id = group_id;
     }

     public int getId() {
          return id;
     }

     public void setId(int id) {
          this.id = id;
     }

     public String getName() {
          return name;
     }

     public void setName(String name) {
          this.name = name;
     }

     public String getUsername() {
          return username;
     }

     public void setUsername(String username) {
          this.username = username;
     }

     public String getPassword() {
          return password;
     }

     public void setPassword(String password) {
          this.password = password;
     }

     public String getPosition() {
          return position;
     }

     public void setPosition(String position) {
          this.position = position;
     }

     public double getSalary() {
          return salary;
     }

     public void setSalary(double salary) {
          this.salary = salary;
     }

     public int getGroup_id() {
          return group_id;
     }

     public void setGroup_id(int group_id) {
          this.group_id = group_id;
     }
}
