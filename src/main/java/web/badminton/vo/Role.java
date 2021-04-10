package web.badminton.vo;

import java.io.Serializable;
import java.util.Set;

public class Role implements Serializable{
 
    private static final long serialVersionUID = 1L;

    private int idRole;
    private String nameRole;

    private Set<User> users;
    
    public int getIdRole(){
        return idRole;
    }

    public void setIdRole(int idRole){
         this.idRole = idRole;
    }

    public String getNameRole(){
        return nameRole;
    }
    public void setNameRole(String nameRole ) {
        this.nameRole = nameRole;
    } 

    public Set<User> getUsers(){
        return users;
    }

    public void setUsers(Set<User> users){
        this.users = users;
    }
    
}