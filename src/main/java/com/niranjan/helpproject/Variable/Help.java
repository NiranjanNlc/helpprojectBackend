package com.niranjan.helpproject.Variable;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class Help
{
    @Id
    @GeneratedValue
    private Long id;
    private String var1;
    private String var2;
    private String var3;
    private long helpedOne;
}
