package in.jeeva.findmypg.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
@Entity
public class PGImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String filename;
    private String filetype;
    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] profile;

    public PGImages(Long id, String filename, String filetype, byte[] profile) {
        this.id = id;
        this.filename = filename;
        this.filetype = filetype;
        this.profile = profile;
    }
    
    
}