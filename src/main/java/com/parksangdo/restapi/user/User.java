package com.parksangdo.restapi.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

// domain class

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("UserInfo")
@JsonIgnoreProperties(value={"password","ssn"})
public class User {

    private Integer id;

    @Size(min = 2, message="Name 은 2글자 이상 입력하세요.")
    private String name;

    @Past
    private Date joinDate;

    // JsonIgnore 으로 개별로 지정하거나 클래스단에서 JsonIgnoreProperties 사용
    //@JsonIgnore
    private String password;

    //@JsonIgnore
    private String ssn;

}
