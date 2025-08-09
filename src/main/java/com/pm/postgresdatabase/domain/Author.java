package com.pm.postgresdatabase.domain;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;




@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder // builder pattern
public class Author {
    private Long id; // could be null
    private String name;
    private Integer age;


}
