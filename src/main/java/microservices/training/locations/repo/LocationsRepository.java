package microservices.training.locations.repo;

import microservices.training.locations.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationsRepository extends JpaRepository<Location, Long> {
}
