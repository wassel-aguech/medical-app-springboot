package pfe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import pfe.entities.Admin;
import pfe.modelsecurite.RegisterRequest;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AdminDto extends RegisterRequest {
	private boolean isadmin ;
	
	
	public static Admin toEntity(AdminDto request)
	{
		return Admin.builder()
				
				.firstName(request.getFirstName())
				.lastName(request.getLastName())
				.email(request.getEmail())
				.password(request.getPassword())
				.adress(request.getAdress())
				.phone(request.getPhone())
				
				.build();

	}
	public static AdminDto toDto(Admin request)
	{
		return AdminDto.builder()
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
