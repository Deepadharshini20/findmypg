package in.jeeva.findmypg.pojo;

import in.jeeva.findmypg.models.ProviderDetails;

public class DetailsUpdate {
    private ProviderDetails providerDetails;
    private String password;

    public DetailsUpdate(String password, ProviderDetails providerDetails) {
        this.password = password;
        this.providerDetails = providerDetails;
    }

    public ProviderDetails getProviderDetails() {
        return providerDetails;
    }

    public void setProviderDetails(ProviderDetails providerDetails) {
        this.providerDetails = providerDetails;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
