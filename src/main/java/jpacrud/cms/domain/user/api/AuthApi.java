package jpacrud.cms.domain.user.api;

import jpacrud.cms.domain.user.entity.User;
import jpacrud.cms.domain.user.service.AuthService;
import jpacrud.cms.global.dto.response.ApiResponse;
import jpacrud.cms.domain.user.dto.LoginDto;
import jpacrud.cms.domain.user.dto.TokenAndUserDto;
import jpacrud.cms.domain.user.dto.UserIdAndAuthDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthApi {

    // 로거 설정
    Logger logger = LoggerFactory.getLogger(AuthApi.class);

    private final AuthService authService;

    @ApiOperation("로그인")
    @PostMapping("/login")
    public ApiResponse<TokenAndUserDto> login(@RequestBody LoginDto dto, HttpServletRequest httpServletRequest) throws NoSuchAlgorithmException {
        return new ApiResponse<>(authService.login(httpServletRequest,dto));
    }

    @ApiOperation("로그인 ID 중복체크")
    @GetMapping("/duplicate")
    public ApiResponse<String> duplicateLoginId(@RequestParam String loginId){
        return new ApiResponse<>(authService.duplicateId(loginId));
    }

    // 권한 획득 (가맹점생성등 다수 기능에서 사용중)
    @GetMapping()
    public ApiResponse<UserIdAndAuthDto> getAuth(@AuthenticationPrincipal User user){
        return new ApiResponse<>(authService.getAuth(user));
    }


}
