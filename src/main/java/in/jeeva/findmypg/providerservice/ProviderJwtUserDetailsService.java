package in.jeeva.findmypg.providerservice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.jeeva.findmypg.models.ProviderDetails;
import in.jeeva.findmypg.models.ProviderLogin;
import in.jeeva.findmypg.pojo.RegisterRequest;
import in.jeeva.findmypg.repository.ProviderDetailsRepo;
import in.jeeva.findmypg.repository.ProviderLoginRepo;
import in.jeeva.findmypg.util.JwtTokenUtil;

@Service
public class ProviderJwtUserDetailsService implements UserDetailsService {
	@Autowired
	private ProviderDetailsRepo providerDetailsRepo;
	@Autowired
	private ProviderLoginRepo providerLoginRepo;
	@Autowired
	private PasswordEncoder bcryptEncoder;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		final ProviderLogin providerLogin = providerLoginRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
		return new org.springframework.security.core.userdetails.User(providerLogin.getUsername(), providerLogin.getPassword(),new ArrayList<>());
	}

	public Boolean save(final RegisterRequest registerRequest) throws SQLIntegrityConstraintViolationException,Throwable{
		final ProviderLogin providerLogin = registerRequest.getProviderLogin();
		final ProviderDetails providerDetails =registerRequest.getProviderDetails();
		if (!providerLoginRepo.existsByUsername(providerLogin.getUsername())) {
			providerDetails.setUsername(providerLogin.getUsername());
			providerLogin.setPassword(bcryptEncoder.encode(
			providerLogin.getPassword()));
			providerLoginRepo.save(providerLogin);
			providerDetailsRepo.save(providerDetails);
			return true;
		}
		throw new Exception("User Already Exists");
    }
    
    public HashMap<String, String> providerResponse(final String username) throws Throwable {
		final UserDetails userDetails = loadUserByUsername(username);
		final String token = jwtTokenUtil.generateToken(userDetails);
		HashMap<String, String> values = new HashMap<String,String>() {
			private static final long serialVersionUID = 1L;
			{
				put("username",username);
				put("token",token);
			}
		};
		return values;
    }
}