package in.jeeva.findmypg.pojo;

import in.jeeva.findmypg.models.ProviderDetails;
import in.jeeva.findmypg.models.ProviderLogin;

public class RegisterRequest {
    private ProviderLogin providerLogin;
    private ProviderDetails providerDetails;

    public RegisterRequest(ProviderDetails providerDetails, ProviderLogin providerLogin) {
        this.providerDetails = providerDetails;
        this.providerLogin = providerLogin;
    }

    public ProviderLogin getProviderLogin() {
        return providerLogin;
    }

    public void setProviderLogin(ProviderLogin providerLogin) {
        this.providerLogin = providerLogin;
    }

    public ProviderDetails getProviderDetails() {
        return providerDetails;
    }

    public void setProviderDetails(ProviderDetails providerDetails) {
        this.providerDetails = providerDetails;
    }


}