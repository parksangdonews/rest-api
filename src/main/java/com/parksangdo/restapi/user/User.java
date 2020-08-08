package com.parksangdo.restapi.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

// domain class

@Data
@AllArgsConstructor
public class User {

    private Integer id;

    @Size(min = 2, message="Name 은 2글자 이상 입력하세요.")
    private String name;

    @Past
    private Date joinDate;

}
