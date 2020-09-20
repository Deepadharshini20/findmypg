package in.jeeva.findmypg.providercontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import in.jeeva.findmypg.models.PGinfo;
import in.jeeva.findmypg.pojo.DetailsUpdate;
import in.jeeva.findmypg.providerservice.ProviderDaoUpdateService;

@CrossOrigin
@RequestMapping("/provider/api")
@RestController
public class ProviderDaoUpdateController {
    @Autowired
    private ProviderDaoUpdateService daoUpdateService; 

    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(@RequestBody MultipartFile file) throws Throwable {
        return ResponseEntity.ok(daoUpdateService.profileUpdate(file));
    }
    @PutMapping("/details")
    public ResponseEntity<?> updateProviderDetails(@RequestBody DetailsUpdate detailsUpdate) throws Throwable {
        return ResponseEntity.ok(daoUpdateService.detailsUpdate(detailsUpdate));
    }
    @RequestMapping(value = "/details",method = RequestMethod.DELETE)
    public void deleteProvider() throws Exception {
        daoUpdateService.deleteProvider();
    }
    @PostMapping("/pg")
    public ResponseEntity<?> addPG(@RequestBody PGinfo pGinfo) {
        return ResponseEntity.ok(daoUpdateService.addPg(pGinfo));
    }
    @PutMapping("/pgimages/{id}")
    public ResponseEntity<?> addPGimages(@RequestBody List<MultipartFile> files,@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(daoUpdateService.updatePgimages(files,id));
    }
    @GetMapping("/pgs")
    public ResponseEntity<?> getPGs() {
        List<PGinfo> pgs = daoUpdateService.getPGslist();
        return ResponseEntity.ok(pgs);
    }
    @GetMapping("/pg/{id}")
    public ResponseEntity<?>  getPG(@PathVariable Long id) throws Exception {
        PGinfo pg = daoUpdateService.getPG(id);
        return ResponseEntity.ok(pg);
    }
    @PutMapping("/pg/{id}")
    public ResponseEntity<?>  updatePG(@RequestBody PGinfo pGinfo) {
        return ResponseEntity.ok(daoUpdateService.updatePG(pGinfo));
    }
    @RequestMapping(value = "/pg/{id}",method = RequestMethod.DELETE)
    public void deletePG(@PathVariable Long id){
        daoUpdateService.deletePG(id);
    }
    @GetMapping("/details")
    public ResponseEntity<?>  getProviderDetails() {
        return ResponseEntity.ok(daoUpdateService.getProviderDetails());
    }
    @GetMapping("/profile")
    public ResponseEntity<?> getProfile() {
        return ResponseEntity.ok(daoUpdateService.getProfile());
    }
}