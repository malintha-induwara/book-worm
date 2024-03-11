package lk.ijse.bookworm.bo.custom.impl;

import lk.ijse.bookworm.bo.custom.BranchBO;
import lk.ijse.bookworm.dao.DAOFactory;
import lk.ijse.bookworm.dao.custom.BranchDAO;
import lk.ijse.bookworm.dto.BranchDto;
import lk.ijse.bookworm.entity.Branch;

import java.util.ArrayList;
import java.util.List;

public class BranchBOImpl implements BranchBO {

    BranchDAO branchDAO = (BranchDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.BRANCH);


    @Override
    public List<BranchDto> getAllBranch() {

        List<BranchDto> branchDtoList = new ArrayList<>();
        for (Branch branch:branchDAO.getAll()){
            branchDtoList.add(new BranchDto(branch.getBranchID(),
                    branch.getBranchName(),
                    branch.getBranchAddress(),
                    branch.getAdmin().getUsername()));
        }

        return branchDtoList;
    }

    @Override
    public boolean saveBranch(BranchDto dto) {
        return branchDAO.save(new Branch(dto.getBranchID(),dto.getBranchName(),dto.getAddress(),AdminBOImpl.admin));
    }

    @Override
    public boolean updateBranch(BranchDto dto) {
        return branchDAO.update(new Branch(dto.getBranchID(),dto.getBranchName(),dto.getAddress(),AdminBOImpl.admin));
    }

    @Override
    public boolean deleteBranch(String id) {
        return branchDAO.delete(id);
    }

    @Override
    public BranchDto searchBranch(String id) {
        Branch branch = branchDAO.search(id);
        if (branch != null){
            return new BranchDto(branch.getBranchID(),
                    branch.getBranchName(),
                    branch.getBranchAddress(),
                    branch.getAdmin().getUsername());
        }
        return null ;
    }
}
