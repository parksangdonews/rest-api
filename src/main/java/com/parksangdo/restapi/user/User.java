package com.parksangdo.restapi.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

// domain class

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("UserInfo")
@JsonIgnoreProperties(value = {"password", "ssn"}, allowSetters = true, ignoreUnknown = true)
@ApiModel(description = "Domain Object for User Detail Infomations.")
@Entity
public class User {




    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Size(min = 2, message = "Name 은 2글자 이상 입력하세요.")
    @ApiModelProperty(notes = "User Name Input")
    private String name;

    @Past
    @ApiModelProperty(notes = "Registed Date")
    private Date joinDate;

    // JsonIgnore 으로 개별로 지정하거나 클래스단에서 JsonIgnoreProperties 사용
    //@JsonIgnore
    @ApiModelProperty(notes = "Password")
    private String password;

    //@JsonIgnore
    @ApiModelProperty(notes = "JUMIN NO")
    private String ssn;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public User(int id, String name, Date joinDate, String password, String ssn) {
        this.id = id;
        this.name = name;
        this.joinDate = joinDate;
        this.password = password;
        this.ssn = ssn;
    }
}
