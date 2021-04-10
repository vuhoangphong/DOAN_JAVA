package web.badminton.modal;

public class Role {
    int idRole;
    int idUser;
    String nameRole;
    String account;

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
         this.idRole = idRole;
    }

    public  int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNameRole() {
        return nameRole;
    }

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
