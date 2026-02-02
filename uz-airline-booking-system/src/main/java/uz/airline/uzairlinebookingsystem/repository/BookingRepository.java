package uz.airline.uzairlinebookingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.airline.uzairlinebookingsystem.entity.BookingEntity;

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {

}