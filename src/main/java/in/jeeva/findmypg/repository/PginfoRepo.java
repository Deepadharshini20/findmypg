package in.jeeva.findmypg.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.jeeva.findmypg.models.PGinfo;

@Repository
public interface PginfoRepo extends JpaRepository<PGinfo,Long>{
    PGinfo findByUsername(String username);
    @Transactional
    void deleteByUsername(String username);
    List<PGinfo> findAllByUsername(String username);
    List<PGinfo> findAllByOrderByRoomrentDesc();
    List<PGinfo> findAllByOrderByRoomrentAsc();
    List<PGinfo> findAllByOrderByIdDesc();
    List<PGinfo> findAllByOrderByIdAsc();

}