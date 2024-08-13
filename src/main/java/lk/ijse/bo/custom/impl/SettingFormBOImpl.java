package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.SettingFormBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.AdminDAO;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.dto.AdminDTO;
import lk.ijse.dto.RegisterDTO;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.Admin;
import lk.ijse.entity.User;

import java.util.ArrayList;
import java.util.List;

public class SettingFormBOImpl implements SettingFormBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().DAOTypes(DAOFactory.DAOTypes.USER);
    AdminDAO adminDAO = (AdminDAO) DAOFactory.getDaoFactory().DAOTypes(DAOFactory.DAOTypes.ADMIN);


    @Override
    public List<RegisterDTO> getAllUser() {
        List<User> getAll = userDAO.getAll();
        List<RegisterDTO> registerDTO = new ArrayList<>();

        for (User register : getAll){
            registerDTO.add(new RegisterDTO(register.getId(), register.getName(), register.getEmail(),register.getPasswords(),register.getBranchName()));
        }
        return registerDTO;
    }

    @Override
    public List<AdminDTO> getAllAdmin() {
        List<Admin> adminList = adminDAO.getAll();
        List<AdminDTO> adminDTOS = new ArrayList<>();

        for (Admin admin : adminList){
            adminDTOS.add(new AdminDTO(admin.getId(), admin.getUsername(), admin.getPassword()));
        }
        return adminDTOS;
    }

    @Override
    public boolean updateAdminUsername(AdminDTO adminDTO) {
        return adminDAO.update(new Admin(adminDTO.getUsername(),adminDTO.getPassword()));
    }

    @Override
    public boolean updateAdminPassword(AdminDTO adminDTO) {
        return adminDAO.update(new Admin(adminDTO.getPassword(),adminDTO.getPassword()));
    }

    @Override
    public boolean updateUserUsername(UserDTO userDTO) {
        return userDAO.update(new User(userDTO.getName(),userDTO.getPassword()));
    }

    @Override
    public boolean updateUserPassword(UserDTO userDTO) {
        return userDAO.update(new User(userDTO.getPassword(),userDTO.getPassword()));
    }
}
