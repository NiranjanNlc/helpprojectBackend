package com.niranjan.helpproject.Security.User;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer
{
    @Id
    @GeneratedValue
    private Long id;
    private String rid;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String postCode;
    private String email;

    public Customer(String rid, String firstName, String lastName, String phoneNumber, String postalCode, String email)
    {
      this.rid=rid;
      this.firstName=firstName;
      this.lastName = lastName;
      this.phoneNumber=phoneNumber;
      this.postCode=postalCode;
      this.email=email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                       "id=" + id +
                       ", rid='" + rid + '\'' +
                       ", firstName='" + firstName + '\'' +
                       ", lastName='" + lastName + '\'' +
                       ", phoneNumber='" + phoneNumber + '\'' +
                       ", postCode='" + postCode + '\'' +
                       ", email='" + email + '\'' +
                       '}';
    }
}
