package in.jeeva.findmypg.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.jeeva.findmypg.models.ProviderLogin;

@Repository
public interface ProviderLoginRepo extends JpaRepository<ProviderLogin,String>{
    Optional<ProviderLogin> findByUsername(String username);
    Boolean existsByUsername(String username);
    @Transactional
    void deleteByUsername(String username);
}