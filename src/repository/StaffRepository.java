package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import Model.Staff;

public class StaffRepository {

    private List<Staff> staffMembers = new ArrayList<>();

    public void save(Staff staff) {
        staffMembers.add(staff);
    }

    public void add(Staff staff) {
        save(staff);
    }

    public Optional<Staff> findById(String staffId) {
        for (Staff staff : staffMembers) {
            if (staff.getStaffId().equals(staffId)) {
                return Optional.of(staff);
            }
        }
        return Optional.empty();
    }

    public List<Staff> findAll() {
        return new ArrayList<>(staffMembers);
    }
}
