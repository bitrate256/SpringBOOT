package jpacrud.cms.domain.user.api;

import io.swagger.annotations.ApiOperation;
import jpacrud.cms.domain.user.dto.UserCreateDto;
import jpacrud.cms.domain.user.service.UserService;
import jpacrud.cms.global.dto.request.PageRequest;
import jpacrud.cms.global.dto.response.ApiPagingResponse;
import jpacrud.cms.global.dto.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserApi {

    // 로거 설정
    Logger logger = LoggerFactory.getLogger(UserApi.class);

    private final UserService userService;

    // 사용자 로깅
    // 어떤 활동의 어느 문구를 로깅할건지 명확한 정책이 필요함
//    @ApiOperation("사용자 로깅")
//    @PostMapping("")
//    public ApiResponse<UserLogDto> userLogging(@RequestBody UserLogDto dto) throws NoSuchAlgorithmException {
//        return new ApiResponse<>(authService.userLogging(dto));
//    }

    // 회원 가입
    @ApiOperation("회원가입")
    @PostMapping("/signup/create")
    public ApiResponse<String> createUser(@RequestBody UserCreateDto dto) throws NoSuchAlgorithmException {
        return new ApiResponse<>(userService.create(dto));
    }

    // 가입 ID 중복체크
    // 중복되는 경우 true, 중복되지 않는 경우 false 리턴 됨.
    @ApiOperation("가입ID 중복체크")
    @PostMapping("/signup/duplicate")
    public ApiResponse<Boolean> duplicateUserId(@RequestBody UserCreateDto dto) throws NoSuchAlgorithmException {
        return new ApiResponse<>(userService.duplicateUserId(dto));
    }

    // 회원 정보 수정
    // 회원 탈퇴
    // 계정 찾기
    // 비밀번호 찾기
}
