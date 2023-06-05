package claimservice.repositories;

import claimservice.models.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimRepository extends JpaRepository<Claim,Long> {
}
