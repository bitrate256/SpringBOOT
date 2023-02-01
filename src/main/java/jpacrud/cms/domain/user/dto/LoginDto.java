package jpacrud.cms.domain.user.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    private String userId;
    private String userPw;
}
