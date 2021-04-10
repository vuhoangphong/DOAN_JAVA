package web.badminton.repository;

import java.util.List;

import web.badminton.vo.User;





public interface RoleRepository{
    List<String> getListRole(String account);
    int insertRole(int  idUser ,int role);
}