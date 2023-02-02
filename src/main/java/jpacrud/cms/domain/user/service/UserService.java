package jpacrud.cms.domain.user.service;

import jpacrud.cms.domain.user.dto.UserCreateDto;
import jpacrud.cms.domain.user.entity.User;
import jpacrud.cms.domain.user.repository.UserRepository;
import jpacrud.cms.domain.user.repository.UserRepositorySupport;
import jpacrud.cms.global.dto.response.PagingResponse;
import jpacrud.cms.global.error.exception.BusinessException;
import jpacrud.cms.global.error.model.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService {

    // 로거 설정
    Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final UserRepositorySupport userRepositorySupport;

    public String create(UserCreateDto dto) {

        // 받아온 사용자 ID 정보를 dto 째로 넣기
        if (Objects.equals(dto.getDuplicateId(), "Y")) {
            logger.error("ID 중복됨 {}", dto.getUserId());
            throw new BusinessException(ErrorCode.DUPLICATE_LOGIN_ID);
        } else {
            User user = User.createUserOnly(dto);
            userRepository.save(user);
            return "Y";
        }
    }

    // UserId 존재 여부 판단.
    // 중복되는 경우 true, 중복되지 않는 경우 false 리턴 됨.
    public Boolean duplicateUserId(UserCreateDto dto) {
        return userRepository.existsByUserId(dto.getUserId());
    }
}
