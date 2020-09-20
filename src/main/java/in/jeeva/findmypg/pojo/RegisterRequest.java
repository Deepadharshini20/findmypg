package in.jeeva.findmypg.pojo;

import in.jeeva.findmypg.models.ProviderDetails;
import in.jeeva.findmypg.models.ProviderLogin;
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
public class RegisterRequest {
    private ProviderLogin providerLogin;
    private ProviderDetails providerDetails;
}