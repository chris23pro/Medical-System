package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import Model.Appointment;

public class AppointmentRepository {

    private List<Appointment> appointments = new ArrayList<>();

    public void save(Appointment appointment) {
        appointments.add(appointment);
    }

    public void add(Appointment appointment) {
        save(appointment);
    }

    public Optional<Appointment> findById(String appId) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppId().equals(appId)) {
                return Optional.of(appointment);
            }
        }
        return Optional.empty();
    }

    public List<Appointment> findAll() {
        return new ArrayList<>(appointments);
    }

    public void deleteById(String appId) {
        appointments.removeIf(appointment -> appointment.getAppId().equals(appId));
    }
}
