package in.jeeva.findmypg.providerservice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import in.jeeva.findmypg.models.PGImages;
import in.jeeva.findmypg.models.PGinfo;
import in.jeeva.findmypg.models.ProviderDetails;
import in.jeeva.findmypg.models.ProviderLogin;
import in.jeeva.findmypg.models.ProviderProfile;
import in.jeeva.findmypg.pojo.DetailsUpdate;
import in.jeeva.findmypg.repository.PginfoRepo;
import in.jeeva.findmypg.repository.ProviderDetailsRepo;
import in.jeeva.findmypg.repository.ProviderLoginRepo;

@Service
public class ProviderDaoUpdateService {
    @Autowired
    private ProviderDetailsRepo providerDetailsRepo;
    @Autowired
    private ProviderLoginRepo providerLoginRepo;
    @Autowired
    private PasswordEncoder bcryptEncoder;
    @Autowired
    private PginfoRepo pginfoRepo;

    public ProviderProfile profileUpdate(MultipartFile file) throws Throwable {
        ProviderDetails providerDetails = providerDetailsRepo.findByUsername(extractUsername())
                .orElseThrow(() -> new Exception("User Not Exists"));
        providerDetails.setProviderProfile(extractProfilefile(file));
        return providerDetailsRepo.save(providerDetails).getProviderProfile();
    }
    public ProviderDetails detailsUpdate(DetailsUpdate detailsUpdate) throws Throwable {
        String username = extractUsername();
        if (detailsUpdate.getPassword() != "") {
            ProviderLogin providerLogin = providerLoginRepo.findByUsername(username)
                    .orElseThrow(() -> new Exception("User Not Found"));
            providerLogin.setPassword(bcryptEncoder.encode(detailsUpdate.getPassword()));
            providerLoginRepo.save(providerLogin);
        }
        ProviderDetails providerDetails = detailsUpdate.getProviderDetails();
        providerDetails.setUsername(username);
        return providerDetailsRepo.save(providerDetails);
    }
    private ProviderProfile extractProfilefile(MultipartFile file) throws Exception {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        if (filename.contains("..")) {
            throw new Exception("Invalid Profile");
        }
        ProviderProfile providerProfile = new ProviderProfile(filename, file.getContentType(), file.getBytes(), null);
        return providerProfile;
    }
    private String extractUsername() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return username;
    }
    public void deleteProvider() throws Exception {
        providerLoginRepo.deleteByUsername(extractUsername());
        providerDetailsRepo.deleteByUsername(extractUsername());
        pginfoRepo.deleteByUsername(extractUsername());
    }
    public PGinfo addPg(PGinfo pGinfo) {
        pGinfo.setUsername(extractUsername());
        return pginfoRepo.save(pGinfo);
    }
    public List<PGImages> updatePgimages(List<MultipartFile> files,Long id) throws Exception {
        List<PGImages> pgImages = 
        files.stream().map(file -> {
            try {
                return extractPgimagefile(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
        PGinfo pGinfo = pginfoRepo.findById(id).orElseThrow(() -> new Exception("Not Found"));
        pGinfo.setPgImages(pgImages);
        return pginfoRepo.save(pGinfo).getPgImages();
    }
    private PGImages extractPgimagefile(MultipartFile file) throws Exception {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        if (filename.contains("..")) {
            throw new Exception("Invalid PG Image");
        }
        PGImages pgImage = new PGImages(null, filename, file.getContentType(), file.getBytes());
        return pgImage;
    }
    public List<PGinfo> getPGslist() {
        return pginfoRepo.findAllByUsername(extractUsername());
    }
    public PGinfo getPG(Long id) throws Exception {
        return pginfoRepo.findById(id).orElseThrow(() -> new Exception("Not Found"));
    }
    public PGinfo updatePG(PGinfo pGinfo) {
        pGinfo.setUsername(extractUsername());
        return pginfoRepo.save(pGinfo);
    }
    public void deletePG(Long id) {
        pginfoRepo.deleteById(id);
    }
    public ProviderDetails getProviderDetails() throws Exception {
        return providerDetailsRepo.findByUsername(extractUsername()).orElseThrow(() -> new Exception("User Not Found"));
    }
    public ProviderProfile getProfile() throws Exception {
        ProviderProfile profile = providerDetailsRepo
                                    .findByUsername(extractUsername())
                                    .orElseThrow(() -> new Exception("User Not Found"))
                                    .getProviderProfile();
        return profile;
    }
}