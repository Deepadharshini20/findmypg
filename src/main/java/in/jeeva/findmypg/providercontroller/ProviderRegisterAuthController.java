package in.jeeva.findmypg.providercontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.jeeva.findmypg.pojo.JwtRequest;
import in.jeeva.findmypg.pojo.RegisterRequest;
import in.jeeva.findmypg.providerservice.ProviderJwtUserDetailsService;

@CrossOrigin
@RequestMapping("/provider")
@RestController
public class ProviderRegisterAuthController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private ProviderJwtUserDetailsService userDetailsService;

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest jwtRequest) throws Throwable {
		authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
		return ResponseEntity.ok(userDetailsService.providerResponse(jwtRequest.getUsername()));
	}

	@PostMapping("/register")
	public ResponseEntity<?> saveUser(@RequestBody RegisterRequest registerRequest) throws Throwable {
		return ResponseEntity.ok(userDetailsService.save(registerRequest));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
    }
}