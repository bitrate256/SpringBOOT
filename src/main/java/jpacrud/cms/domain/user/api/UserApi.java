package jpacrud.cms.domain.user.api;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserApi {

    // 로거 설정
    Logger logger = LoggerFactory.getLogger(UserApi.class);

    // 사용자 로깅
    // 어떤 활동의 어느 문구를 로깅할건지 명확한 정책이 필요함
//    @ApiOperation("사용자 로깅")
//    @PostMapping("")
//    public ApiResponse<UserLogDto> userLogging(@RequestBody UserLogDto dto) throws NoSuchAlgorithmException {
//        return new ApiResponse<>(authService.userLogging(dto));
//    }

    // 가입 ID 중복체크
    // 회원 가입
    // 회원 정보 수정
    // 회원 탈퇴
    // 계정 찾기
    // 비밀번호 찾기
}
