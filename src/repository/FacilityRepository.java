package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import Model.Facility;

public class FacilityRepository {

    private List<Facility> facilities = new ArrayList<>();

    public void save(Facility facility) {
        facilities.add(facility);
    }

    public void add(Facility facility) {
        save(facility);
    }

    public Optional<Facility> findById(String facilityId) {
        for (Facility facility : facilities) {
            if (facility.getFacilityId().equals(facilityId)) {
                return Optional.of(facility);
            }
        }
        return Optional.empty();
    }

    public List<Facility> findAll() {
        return new ArrayList<>(facilities);
    }
}
