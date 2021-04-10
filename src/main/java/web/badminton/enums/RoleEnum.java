package web.badminton.enums;

public enum RoleEnum {
    ROLE_MEMBER(1),
    ROLE_SHOP(3),
    ROLE_ADMIN(2);

    private int role;

     RoleEnum(int role){
        this.role = role;
    }

    public int get(){
        return this.role;
    }
}
