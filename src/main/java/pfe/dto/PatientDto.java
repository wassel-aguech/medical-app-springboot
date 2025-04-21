package pfe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import pfe.entities.Patient;
import pfe.modelsecurite.RegisterRequest;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PatientDto extends RegisterRequest {
	  private boolean ispatient;
	  
public static Patient toEntity(PatientDto request) {
	return  Patient.builder()
			.firstName(request.getFirstName())
			.lastName(request.getLastName())
			.email(request.getEmail())
			.password(request.getPassword())
			.adress(request.getAdress())
			.phone(request.getPhone())
			.build();

}
public static PatientDto toDto(Patient request)
{
	return PatientDto.builder()
			.id(request.getId())
			.firstName(request.getFirstName())
			.lastName(request.getLastName())
			.email(request.getEmail())
			.password(request.getPassword())
			.adress(request.getAdress())
			.phone(request.getPhone())
			.build();

}
}
