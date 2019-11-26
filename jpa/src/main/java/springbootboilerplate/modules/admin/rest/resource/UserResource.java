package springbootboilerplate.modules.admin.rest.resource;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import springbootboilerplate.domain.User;

@Getter
@NoArgsConstructor
@Data
public class UserResource {
    private Long id;

    private String username;

    private String email;

    private String phone;

    private Boolean enabled;

    public static UserResource of(User user) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, UserResource.class);
    }
}
