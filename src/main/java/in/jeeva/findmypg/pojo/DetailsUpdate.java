package in.jeeva.findmypg.pojo;

import in.jeeva.findmypg.models.ProviderDetails;
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
public class DetailsUpdate {
    private ProviderDetails providerDetails;
    private String password;
}
