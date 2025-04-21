package pfe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import pfe.entities.Medecin;
import pfe.modelsecurite.RegisterRequest;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class MedecinDto extends RegisterRequest {
	private String specialite;


	public static Medecin toEntity(MedecinDto request)
	{
		return Medecin.builder()
				
				.firstName(request.getFirstName())
				.lastName(request.getLastName())
				.email(request.getEmail())
				.password(request.getPassword())
				.adress(request.getAdress())
				.specialite(request.getSpecialite())
				.phone(request.getPhone())
				
				.build();

	}
	public static MedecinDto toDto(Medecin request)
	{
		return MedecinDto.builder()
				.id(request.getId())
				.firstName(request.getFirstName())
				.lastName(request.getLastName())
				.email(request.getEmail())
				.password(request.getPassword())
				.specialite(request.getSpecialite())
				.adress(request.getAdress())
				.phone(request.getPhone())
				
				.build();

	}

}
