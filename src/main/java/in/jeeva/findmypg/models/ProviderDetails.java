package in.jeeva.findmypg.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class ProviderDetails {  
    @Id
    @Column(unique = true)
    private String username;
    private String name;
    @Column(unique = true)
    private long contact;
    @Column(unique = true)
    private String address;
    @Column(unique = true)
    private String email;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_fk")
    private ProviderProfile providerProfile;
}