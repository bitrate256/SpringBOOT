package jpacrud.cms.domain.user.service;

import jpacrud.cms.domain.user.entity.User;
import jpacrud.cms.domain.user.repository.UserRepository;
import jpacrud.cms.domain.user.repository.UserRepositorySupport;
import jpacrud.cms.global.application.Sha256Hash;
import jpacrud.cms.global.error.exception.BusinessException;
import jpacrud.cms.global.error.model.ErrorCode;
import jpacrud.cms.global.provider.TokenProvider;
import jpacrud.cms.domain.user.dto.LoginDto;
import jpacrud.cms.domain.user.dto.TokenAndUserDto;
import jpacrud.cms.domain.user.dto.UserIdAndAuthDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AuthService {

    // 로거 설정
    Logger logger = LoggerFactory.getLogger(AuthService.class);

    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserRepositorySupport userRepositorySupport;

    public TokenAndUserDto login(HttpServletRequest httpServletRequest,LoginDto dto) throws NoSuchAlgorithmException {

        Sha256Hash sha256Hash = new Sha256Hash();

        logger.debug("로그인 유저 아이디 {}", dto.getUserId());
        logger.debug("로그인 유저 비번 {}", dto.getUserPw());
        User user = userRepositorySupport.findByUserId(dto.getUserId());

        logger.debug("로그인 유저 아이디 확인 {}", user.getUserId());
        logger.debug("로그인 유저 권한 확인 {}", user.getUserAuth());

        // 로그인 히스토리 업데이트
//        user.updateLastLoginDate();
        // 암호화 해야 하지만 임시 주석
//        matchPassword(sha256Hash.encrypt(dto.getPassword()),user.getPassword());

        logger.debug("로그인 유저 패스워드 확인");
        matchPassword((dto.getUserPw()), user.getUserPw());
        logger.debug("dto 통해 넘어온 패스워드 {}", dto.getUserPw());
        logger.debug("user 통해 넘어온 패스워드 {}", user.getUserPw());

        String token = createToken(user);

        // 로그인 히스토리 생성
//        AdminLoginHistory adminLoginHistory = AdminLoginHistory.create(user,getLocalServerIp(httpServletRequest));
//        adminLoginHistoryRepository.save(adminLoginHistory);

        // 어드민에서 id찾기
        // 사용자 테이블 에서 사용자 찾기
//        AdminInfo adminInfo =  adminRepositorySupport.findByIdNull(user.getId());

        // 브랜치가 같으면 로그인데이트 추가
//        if(adminInfo != null && adminInfo.getBranch() != null){
//            branchRepository.findById(adminInfo.getBranch().getId()).orElseThrow(()->new BusinessException(ErrorCode.ENTITY_NOT_FOUND))
//                    .updateLastLoginDate();
//        }

        logger.debug("로그인 user 확인 : {}", user);
        logger.debug("로그인 token 확인 : {}", token);

        return new TokenAndUserDto(user,token);
    }

    // 토큰 생성
    public String createToken(User user){
        return tokenProvider.createToken(String.valueOf(user.getId()), user.getUserAuth());
    }

    // 비밀번호 확인
    public void matchPassword(String reqPassword,String userPassword){

        logger.debug("로그인 reqPassword 확인 : {}", reqPassword);
        logger.debug("로그인 userPassword 확인 : {}", userPassword);

        boolean result =reqPassword.equals(userPassword);

        if(!result){
            throw new BusinessException(ErrorCode.NOT_MATCH_PASSWORD);
        }
    }


    public String duplicateId(String id){

//        if(userRepository.findByLoginId(id).isPresent()){
//           throw  new BusinessException(ErrorCode.DUPLICATE_LOGIN_ID);
//        };
        return "Y";
    }

//    public UserIdAndRoleDto getRole(User user) {
//        return new UserIdAndRoleDto(user);
//    }
    public UserIdAndAuthDto getAuth(User user) {
        return new UserIdAndAuthDto(user);
    }

    private String getLocalServerIp(HttpServletRequest request) {

        String ip = request.getHeader("X-Forwarded-For");

        log.info("X-FORWARDED-FOR : " + ip);

        if (ip == null) {
            ip = request.getHeader("Proxy-Client-IP");
            log.info("Proxy-Client-IP : " + ip);
        }
        if (ip == null) {
            ip = request.getHeader("WL-Proxy-Client-IP");
            log.info("WL-Proxy-Client-IP : " + ip);
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_CLIENT_IP");
            log.info("HTTP_CLIENT_IP : " + ip);
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            log.info("HTTP_X_FORWARDED_FOR : " + ip);
        }
        if (ip == null) {
            ip = request.getRemoteAddr();
            log.info("getRemoteAddr : "+ip);
        }
        log.info("Result : IP Address : "+ip);

        return ip;
    }


//    public String userLogging(UserLogDto dto) {
//
//    }
}
