package in.jeeva.findmypg.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.jeeva.findmypg.models.ProviderDetails;

@Repository
public interface ProviderDetailsRepo extends JpaRepository<ProviderDetails,String>{
    Optional<ProviderDetails> findByUsername(String username);
    @Transactional
    void deleteByUsername(String username);
}