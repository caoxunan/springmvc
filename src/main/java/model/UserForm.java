package model;

import java.io.Serializable;
import java.util.List;

/**
 * @program: springmvc
 * @description: ${description}
 * @author: cxn
 * @create: 2018-04-18 15:21
 * @Version v1.0
 */
public class UserForm implements Serializable{

    private static final long serialVersionUID = 1179272630863724572L;

    List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UserForm{" +
                "users=" + users +
                '}';
    }
}
