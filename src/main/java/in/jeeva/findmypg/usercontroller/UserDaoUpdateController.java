package in.jeeva.findmypg.usercontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.jeeva.findmypg.userservice.UserDaoUpdateService;

@CrossOrigin
@RequestMapping("/user")
@RestController
public class UserDaoUpdateController {
    @Autowired
    private UserDaoUpdateService daoUpdateService;

    @GetMapping("/pg")
    public ResponseEntity<?> getPG() {
        return ResponseEntity.ok(daoUpdateService.getPG());
    }
    @GetMapping("/pg/sort/{id}")
    public ResponseEntity<?> sortBy(@PathVariable int id) {
        if (id == 1) {
            return ResponseEntity.ok(daoUpdateService.sortByRoomrentDesc());
        } else if (id == 2) {
            return ResponseEntity.ok(daoUpdateService.sortByRoomrentAsc());
        } else if (id == 3) {
            return ResponseEntity.ok(daoUpdateService.sortByIdAsc());
        } else {
            return ResponseEntity.ok(daoUpdateService.sortByIdDesc());
        }
    }
    @GetMapping("/pg/{id}")
    public ResponseEntity<?> getPGandDetails(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(daoUpdateService.getPGandDetails(id));
    }
}