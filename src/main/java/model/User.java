package model;

import java.io.Serializable;

/**
 * @program: springmvc
 * @description: ${description}
 * @author: cxn
 * @create: 2018-04-18 11:38
 * @Version v1.0
 */
public class User implements Serializable{

    private static final long serialVersionUID = 8706313286175566876L;

    private Long id;
    private Integer age;
    private String name;
    private String userName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
