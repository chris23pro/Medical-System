package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import Model.Appointment;

/**
 * In-memory implementation of AppointmentRepository.
 * Uses an ArrayList to simulate a database.
 */
public class AppointmentRepository {

    /**
     * Internal storage for Appointment objects.
     */
    private List<Appointment> appointments = new ArrayList<>();

    /**
     * Saves an appointment into memory.
     */
    public void save(Appointment appointment) {
        appointments.add(appointment);
    }

    /**
     * Searches for an appointment by ID.
     */
    public Optional<Appointment> findById(String appId) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppId().equals(appId)) {
                return Optional.of(appointment);
            }
        }
        return Optional.empty();
    }

    /**
     * Returns all appointments.
     */
    public List<Appointment> findAll() {
        return new ArrayList<>(appointments);
    }

    /**
     * Deletes an appointment by ID.
     */
    public void deleteById(String appId) {
        appointments.removeIf(appointment -> appointment.getAppId().equals(appId));
    }
}

