package lk.ijse.bookworm.bo.custom;

import lk.ijse.bookworm.bo.SuperBO;
import lk.ijse.bookworm.dto.BranchDto;
import lk.ijse.bookworm.dto.UserDto;

import java.util.List;

public interface BranchBO extends SuperBO {
    List<BranchDto> getAllUsers();
    boolean saveBranch(BranchDto dto);
    boolean updateBranch(BranchDto dto);
    boolean deleteBranch(String id);
    BranchDto searchBranch(String id);
}
