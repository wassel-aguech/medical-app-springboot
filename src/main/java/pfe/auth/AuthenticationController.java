package pfe.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pfe.dto.AdminDto;
import pfe.dto.MedecinDto;
import pfe.dto.PatientDto;
import pfe.modelsecurite.AuthenticationRequest;
import pfe.modelsecurite.AuthenticationResponse;
import pfe.modelsecurite.Response;


import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
  private final AuthenticationService service;
  
  
 
  
  @PostMapping("/registermedecin")
  public ResponseEntity<Response> registermedecin(
          @RequestBody @Valid MedecinDto userRequest,
          HttpServletRequest request
  )  {
    return service.register(userRequest,request);
  }
  
  @PostMapping("/registerpatient")
  public ResponseEntity<Response> registerpatient(
          @RequestBody @Valid PatientDto userRequest,
          HttpServletRequest request
  )  {
    return service.register(userRequest,request);
  }
  
  @PostMapping("/registeradmin")
  public ResponseEntity<Response> registeradmin(
          @RequestBody @Valid AdminDto userRequest,
          HttpServletRequest request
  )  {
    return service.register(userRequest,request);
  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }


  @PostMapping("/refresh-token")
  public void refreshToken(
      HttpServletRequest request,
      HttpServletResponse response
  ) throws IOException {
    service.refreshToken(request, response);
  }


}
