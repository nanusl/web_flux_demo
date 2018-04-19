package com.hbbclub.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * @author 南来
 * @version V1.0
 * @Description User
 * @date 2018-04-15 12:16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Id
    private Long id;

    private String name;

    private Integer age;

    private String description;

}
