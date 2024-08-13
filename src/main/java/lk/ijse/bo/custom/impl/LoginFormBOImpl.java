package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.LoginFormBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.AdminDAO;
import lk.ijse.dto.AdminDTO;
import lk.ijse.entity.Admin;

import java.util.ArrayList;
import java.util.List;

public class LoginFormBOImpl implements LoginFormBO {

    AdminDAO adminDAO = (AdminDAO) DAOFactory.getDaoFactory().DAOTypes(DAOFactory.DAOTypes.ADMIN);

    @Override
    public List<AdminDTO> getAllAdmin() {
        List<Admin> adminList = adminDAO.getAll();
        List<AdminDTO> adminDTOS = new ArrayList<>();

        for (Admin admin : adminList){
            adminDTOS.add(new AdminDTO(admin.getId(),admin.getUsername(),admin.getPassword()));
        }
        return adminDTOS;
    }
}
