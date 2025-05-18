package pfe.modelsecurite;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String adress;
	private String phone;
	private Long cin;
	private Set<String> roles;
	/*private String cin;
	private String diplome;
	private Date dateDebutTravail;*/
}
