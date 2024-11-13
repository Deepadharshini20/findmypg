package in.jeeva.findmypg.execption;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class JwtExpired {
    private int code;
    private String message;

    public JwtExpired(int code, String message) {
        this.code = code;
        this.message = message;
    }

    
}
