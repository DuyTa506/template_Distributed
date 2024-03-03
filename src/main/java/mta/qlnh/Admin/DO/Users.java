package mta.qlnh.Admin.DO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
//@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "users")
public class Users {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int id;
     @Column(name = "name")
     private String name;
     private String firstname;

     public String getName() {
          return name;
     }

     public void setName(String name) {
          this.name = name;
     }

     private String lastname;
     private String email;
     private String phone;
     private String username;
     private String password;
     private String position;
     private double salary;
     @Column(name = "group_id")
     private int groupid;
     private String status;
     private int freq;
     @Enumerated(EnumType.STRING)
     private Role role;



     @OneToMany(mappedBy = "users")
     @JsonBackReference(value = "user_inv")
     private Set<Invoices> invoices;
     public Users() {

     }

     public Users(int id, String firstname, String lastname, String email, String phone, String username, String password, String position, double salary, int group_id, String status) {
          this.id = id;
          this.firstname = firstname;
          this.lastname = lastname;
          this.email = email;
          this.phone = phone;
          this.username = username;
          this.password = password;
          this.position = position;
          this.salary = salary;
          this.groupid = group_id;
          this.status = status;
          this.name = firstname+ lastname;
     }

     public int getId() {
          return id;
     }

     public void setId(int id) {
          this.id = id;
     }

     public String getFirstname() {
          return firstname;
     }

     public void setFirstname(String firstname) {
          this.firstname = firstname;
     }

     public String getLastname() {
          return lastname;
     }

     public void setLastname(String lastname) {
          this.lastname = lastname;
     }

     public String getEmail() {
          return email;
     }

     public void setEmail(String email) {
          this.email = email;
     }

     public String getPhone() {
          return phone;
     }

     public void setPhone(String phone) {
          this.phone = phone;
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
          return groupid;
     }

     public void setGroup_id(int group_id) {
          this.groupid = group_id;
     }

     public String getStatus() {
          return status;
     }


     public void setStatus(String status) {
          this.status = status;
     }


     public Set<Invoices> getInvoices() {
          return invoices;
     }

     public void setInvoices(Set<Invoices> invoices) {
          this.invoices = invoices;
     }

}
