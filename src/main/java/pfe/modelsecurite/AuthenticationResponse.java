package pfe.modelsecurite;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pfe.entities.User;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {


 @JsonProperty("accessToken")
 private String accessToken;
 @JsonProperty("refreshToken")
 private String refreshToken;
 private User user;
 private String firstName;
 private Long id;
}

