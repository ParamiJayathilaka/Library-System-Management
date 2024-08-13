package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.BranchBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.BranchesDAO;
import lk.ijse.dto.BranchDTO;
import lk.ijse.entity.Branches;

import java.util.ArrayList;
import java.util.List;

public class BranchBOImpl implements BranchBO {

    BranchesDAO branchesDAO = (BranchesDAO) DAOFactory.getDaoFactory().DAOTypes(DAOFactory.DAOTypes.BRANCHES);

    @Override
    public boolean saveBranches(BranchDTO entity) {
        return branchesDAO.save(new Branches(entity.getId(), entity.getBranchIdNumber(),entity.getBranchName()));
    }

    @Override
    public boolean updateBranches(BranchDTO entity) {
       return branchesDAO.update(new Branches(entity.getId(),entity.getBranchIdNumber(),entity.getBranchName()));
    }

    @Override
    public boolean deleteBranches(long id) {
        return branchesDAO.delete(id);
    }

    @Override
    public List<BranchDTO> getAllBranches() {
        List<Branches> branchesList = branchesDAO.getAll();

        List<BranchDTO> branchDTOS = new ArrayList<>();
        for (Branches branches : branchesList){
            branchDTOS.add(new BranchDTO(branches.getId(),branches.getBranchIdNumber(),branches.getBranchName()));
        }
        return branchDTOS;
    }
}
