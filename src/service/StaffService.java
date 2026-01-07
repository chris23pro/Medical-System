package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

import Model.Staff;
import repository.StaffRepository;

public class StaffService {

    private StaffRepository staffRepository;

    public StaffService() {
        this(new StaffRepository());
    }

    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    public void loadFromCsv(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;
            br.readLine(); // skip header

            while ((line = br.readLine()) != null) {
                String[] data = CsvUtils.parseLine(line);
                if (data.length < 12) {
                    continue;
                }

                Staff staff = new Staff(
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
                        data[10],
                        data[11]
                );
                staffRepository.add(staff);
            }

        } catch (Exception e) {
            System.out.println("Error loading staff CSV: " + e.getMessage());
        }
    }
}
