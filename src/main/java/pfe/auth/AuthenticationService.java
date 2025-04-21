package pfe.auth;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pfe.configsecurite.JwtService;
import pfe.dto.AdminDto;
import pfe.dto.MedecinDto;
import pfe.dto.PatientDto;
import pfe.entities.*;
import pfe.modelsecurite.AuthenticationRequest;
import pfe.modelsecurite.AuthenticationResponse;
import pfe.modelsecurite.RegisterRequest;
import pfe.modelsecurite.Response;
import pfe.repository.RoleRepository;
import pfe.repository.TokenRepository;
import pfe.repository.UserRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final ApplicationEventPublisher publisher;
  private final RoleRepository roleRepository;
 

  public ResponseEntity<Response>  register(RegisterRequest userRequest, final HttpServletRequest request) {
  
    boolean userExists = repository.findAll()
       .stream()
       .anyMatch(user -> userRequest.getEmail().equalsIgnoreCase(user.getEmail()));

if (userExists) {
   return ResponseEntity.badRequest().body(Response.builder()
           .responseMessage("User with provided email  already exists!")
           .build());
}
//parent
if (userRequest instanceof AdminDto) {
   User user = new Admin();
   user = AdminDto.toEntity((AdminDto) userRequest);
   user.setPassword(passwordEncoder.encode(user.getPassword()));
   user.setEnabled(true);
   Set<String> strRoles = userRequest.getRoles();
   List<Role> roles = new ArrayList<>();

   if (strRoles == null) {
       Role userRole = roleRepository.findByName("admin")
               .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
       roles.add(userRole);
   } else {
       strRoles.forEach(role -> {

           Role adminRole = roleRepository.findByName(role)
                   .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
           roles.add(adminRole);

       });
   }
   user.setRoles(roles);
   var savedUser = repository.save(user);
   ///publisher.publishEvent(new RegistrationCompleteEvent(savedUser, applicationUrl(request)));

   return new ResponseEntity<>(
           Response.builder()

                   .responseMessage("Success! Please, check your email to complete your registration")
                   .email(savedUser.getEmail())
                   .build(),
           HttpStatus.CREATED
   );
}
//eleve
if (userRequest instanceof MedecinDto) {

    User user = new Medecin();

	   user = MedecinDto.toEntity((MedecinDto) userRequest);
	   user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setEnabled(true);




    Set<String> strRoles = userRequest.getRoles();
	   List<Role> roles = new ArrayList<>();

	   if (strRoles == null) {
	       Role userRole = roleRepository.findByName("medecin")
	               .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	       roles.add(userRole);
	   } else {
	       strRoles.forEach(role -> {

	           Role adminRole = roleRepository.findByName(role)
	                   .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	           roles.add(adminRole);

	       });
	   }
	   user.setRoles(roles);
      
	   var savedUser = repository.save(user);
	  // publisher.publishEvent(new RegistrationCompleteEvent(savedUser, applicationUrl(request)));

	   return new ResponseEntity<>(
	           Response.builder()

	                   .responseMessage("Success! Please, check your email to complete your registration")
	                   .email(savedUser.getEmail())
	                   .build(),
	           HttpStatus.CREATED
	   );
	}
//pro
//agent
if (userRequest instanceof PatientDto) {
	   User user = new Patient();
	   user = PatientDto.toEntity((PatientDto) userRequest);
	   user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setEnabled(true);

    Set<String> strRoles = userRequest.getRoles();
	   List<Role> roles = new ArrayList<>();

	   if (strRoles == null) {
	       Role userRole = roleRepository.findByName("patient")
	               .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	       roles.add(userRole);
	   } else {
	       strRoles.forEach(role -> {

	           Role adminRole = roleRepository.findByName(role)
	                   .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	           roles.add(adminRole);

	       });
	   }
	   user.setRoles(roles);
	   var savedUser = repository.save(user);
	 //  publisher.publishEvent(new RegistrationCompleteEvent(savedUser, applicationUrl(request)));

	   return new ResponseEntity<>(
	           Response.builder()

	                   .responseMessage("Success! Please, check your email to complete your registration")
	                   .email(savedUser.getEmail())
	                   .build(),
	           HttpStatus.CREATED
	   );
	}




    return null;
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    var user = repository.findByEmail(request.getEmail())
        .orElseThrow();
    var claims = new HashMap<String, Object>();
    claims.put("fullname", user.getFirstName() + " " + user.getLastName());
    claims.put("userId", user.getId());
    var jwtToken = jwtService.generateToken(claims,user);
    //var jwtToken = jwtService.generateToken(user);
   
    var refreshToken = jwtService.generateRefreshToken(user);
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
    return AuthenticationResponse.builder()
        .accessToken(jwtToken)
            .refreshToken(refreshToken)
        .build();
  }

  private void saveUserToken(User user, String jwtToken) {
    var token = Token.builder()
        .user(user)
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .build();
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(User user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }

  public void refreshToken(
          HttpServletRequest request,
          HttpServletResponse response
  ) throws IOException {
    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    final String refreshToken;
    final String userEmail;
    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
      return;
    }
    refreshToken = authHeader.substring(7);
    userEmail = jwtService.extractUsername(refreshToken);
    if (userEmail != null) {
      var user = this.repository.findByEmail(userEmail)
              .orElseThrow();
      if (jwtService.isTokenValid(refreshToken, user)) {
        var accessToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);
        var authResponse = AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
      }
    }
  }



    @PostConstruct
  public void createDefaultAdmin() {

      User user =new Admin();
      String email = "<div class=\"modal\" tabindex=\"-1\">\r\n"
      		+ "  <div class=\"modal-dialog\">\r\n"
      		+ "    <div class=\"modal-content\">\r\n"
      		+ "      <div class=\"modal-header\">\r\n"
      		+ "        <h5 class=\"modal-title\">Modal title</h5>\r\n"
      		+ "        <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>\r\n"
      		+ "      </div>\r\n"
      		+ "      <div class=\"modal-body\">\r\n"
      		+ "        <p>Modal body text goes here.</p>\r\n"
      		+ "      </div>\r\n"
      		+ "      <div class=\"modal-footer\">\r\n"
      		+ "        <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Close</button>\r\n"
      		+ "        <button type=\"button\" class=\"btn btn-primary\">Save changes</button>\r\n"
      		+ "      </div>\r\n"
      		+ "    </div>\r\n"
      		+ "  </div>\r\n"
      		+ "</div>";


        if (roleRepository.findByName("admin").isEmpty()) {
            roleRepository.save(Role.builder().name("admin").build());
        }
        if (roleRepository.findByName("medecin").isEmpty()) {
            roleRepository.save(Role.builder().name("medecin").build());
        }
        if (roleRepository.findByName("patient").isEmpty()) {
            roleRepository.save(Role.builder().name("patient").build());
        }



        String emailadm = "admin@mail.com";
      if (!repository.existsByEmail(emailadm)) {
          user.setEmail("admin@mail.com");
          user.setFirstName("admin");
          user.setLastName("admin");
          user.setEnabled(true);
          user.setPassword(passwordEncoder.encode("admin"));
          List<Role> roles = new ArrayList<>();
          Role userRole = roleRepository.findByName("admin")
                  .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(userRole);
          user.setRoles(roles);

          repository.save(user);
      }
}

}

