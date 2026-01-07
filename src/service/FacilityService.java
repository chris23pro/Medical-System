package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

import Model.Facility;
import repository.FacilityRepository;

public class FacilityService {

    private FacilityRepository facilityRepository;

    public FacilityService() {
        this(new FacilityRepository());
    }

    public FacilityService(FacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }

    public List<Facility> getAllFacilities() {
        return facilityRepository.findAll();
    }

    public void loadFromCsv(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;
            br.readLine(); // skip header

            while ((line = br.readLine()) != null) {
                String[] data = CsvUtils.parseLine(line);
                if (data.length < 11) {
                    continue;
                }

                Facility facility = new Facility(
                        data[0],
                        data[1],
                        data[2],
                        data[3],
                        data[4],
                        data[5],
                        data[6],
                        data[7],
                        data[8],
                        data[9],
                        data[10]
                );
                facilityRepository.add(facility);
            }

        } catch (Exception e) {
            System.out.println("Error loading facilities CSV: " + e.getMessage());
        }
    }
}
