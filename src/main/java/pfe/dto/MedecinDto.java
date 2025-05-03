package pfe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import pfe.entities.Etat;
import pfe.entities.Medecin;
import pfe.entities.Status;
import pfe.modelsecurite.RegisterRequest;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class MedecinDto extends RegisterRequest {

	private String specialite;
	private Status status;
	private Etat etat;
	private String image;


	public static Medecin toEntity(MedecinDto request)
	{
		return Medecin.builder()
				.id(request.getId())
				.firstName(request.getFirstName())
				.lastName(request.getLastName())
				.phone(request.getPhone())
				.email(request.getEmail())
				.password(request.getPassword())
				.adress(request.getAdress())
				.specialite(request.getSpecialite())
				.image(request.getImage())
				.status(request.getStatus())
				.etat(request.getEtat())
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
				.image(request.getImage())
				.status(request.getStatus())
				.etat(request.getEtat())
				
				.build();

	}

}
