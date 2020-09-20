package in.jeeva.findmypg.userservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.jeeva.findmypg.models.PGinfo;
import in.jeeva.findmypg.models.ProviderDetails;
import in.jeeva.findmypg.repository.PginfoRepo;
import in.jeeva.findmypg.repository.ProviderDetailsRepo;

@Service
public class UserDaoUpdateService {
    @Autowired
    private PginfoRepo  pginfoRepo;
    @Autowired
    private ProviderDetailsRepo providerDetailsRepo;

    public List<PGinfo> getPG() {
        return pginfoRepo.findAll();
    }
    public List<PGinfo> sortByIdAsc() {
        return pginfoRepo.findAllByOrderByIdAsc();
    }
    public List<PGinfo> sortByIdDesc() {
        return pginfoRepo.findAllByOrderByIdDesc();
    }
    public List<PGinfo> sortByRoomrentAsc() {
        return pginfoRepo.findAllByOrderByRoomrentAsc();
    }
    public List<PGinfo> sortByRoomrentDesc() {
        return pginfoRepo.findAllByOrderByRoomrentDesc();
    }
    public List<Object> getPGandDetails(Long id) throws Exception {
        List<Object> PGandDetails = new ArrayList<>();
        PGinfo pGinfo = pginfoRepo.findById(id).orElseThrow(()-> new Exception("PG Not Found"));
        ProviderDetails providerDetails = providerDetailsRepo.findByUsername(pGinfo.getUsername()).orElseThrow(()-> new Exception("Details found"));
        PGandDetails.add(providerDetails);
        PGandDetails.add(pGinfo);
        return PGandDetails;
    }
}