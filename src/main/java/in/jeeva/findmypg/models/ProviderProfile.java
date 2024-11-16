package in.jeeva.findmypg.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
public class ProviderProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String filename;
    private String filetype;
    @Lob
    @Column(columnDefinition="LONGBLOB")
    private byte[] profile;

    public ProviderProfile(String filename, String filetype, byte[] profile, Long id) {
        this.filename = filename;
        this.filetype = filetype;
        this.id = id;
        this.profile = profile;
    }

    public ProviderProfile() {
    }


    
}