package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.RegisterFormBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.BranchesDAO;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.dto.BranchDTO;
import lk.ijse.dto.RegisterDTO;
import lk.ijse.entity.Branches;
import lk.ijse.entity.User;

import java.util.ArrayList;
import java.util.List;

public class RegisterFormBOImpl implements RegisterFormBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().DAOTypes(DAOFactory.DAOTypes.USER);
    BranchesDAO branchesDAO = (BranchesDAO) DAOFactory.getDaoFactory().DAOTypes(DAOFactory.DAOTypes.BRANCHES);

    @Override
    public boolean saveUser(RegisterDTO registerDTO) {
        return userDAO.save(new User(registerDTO.getId(), registerDTO.getName(), registerDTO.getEmail(), registerDTO.getPasswords(),registerDTO.getBranchName()));
    }

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
    public List<BranchDTO> getAllBranches() {
        List<Branches> getAll = branchesDAO.getAll();
        List<BranchDTO> branchDTOS = new ArrayList<>();

        for (Branches branches : getAll){
            branchDTOS.add(new BranchDTO(branches.getId(), branches.getBranchName(), branches.getBranchName()));
        }
        return branchDTOS;
    }
}
