package jpacrud.cms.domain.company.service;

import jpacrud.cms.domain.company.entity.Company;
import jpacrud.cms.domain.company.repository.CompanyRepository;
import jpacrud.cms.domain.user.entity.User;
import jpacrud.cms.domain.user.repository.UserRepository;
import jpacrud.cms.domain.user.repository.UserRepositorySupport;
import jpacrud.cms.domain.company.dto.CompanyCreateDto;
import jpacrud.cms.domain.company.dto.CompanyUpdateDto;
import jpacrud.cms.domain.company.dto.QueryModel;
import jpacrud.cms.domain.company.repository.CompanyRepositorySupport;
import jpacrud.cms.global.dto.response.PagingResponse;
import jpacrud.cms.global.error.exception.BusinessException;
import jpacrud.cms.global.error.model.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CompanyService {

    // 로거 설정
    Logger logger = LoggerFactory.getLogger(CompanyService.class);

    private final UserRepository userRepository;
    private final UserRepositorySupport userRepositorySupport;
    private final CompanyRepository companyRepository;
    private final CompanyRepositorySupport companyRepositorySupport;

    @Transactional(readOnly = true)
    public boolean checkUserIdDuplicate(String userId) {
        boolean userIdDuplicate = companyRepositorySupport.existsByUserId(userId);
        return userIdDuplicate;
    }

    // find by companyCode (레포지토리)
    public Company findById(Long companyCode) {
        return companyRepository.findById(companyCode).orElseThrow(() -> new BusinessException(ErrorCode.ENTITY_NOT_FOUND));
    }

    // 업체 등록
    public String create(CompanyCreateDto dto) throws NoSuchAlgorithmException, IOException {

        try {
            User userInsert = User.createWithCompany(dto);

            logger.debug("create_id (작성자 id) {}",userInsert.getCreateId());
            logger.debug("user_id (생성하고자 하는 id) {}",userInsert.getUserId());

            // 사용자 저장
            userRepository.save(userInsert);
            // 사용자Seq 삽입하기 위해 user_info ID(계정) 이용하여 사용자 찾아오기
            User user = userRepositorySupport.findByUserId(dto.getCreateId());

            // 업체 생성
            Company companyInsert = Company.create(dto, user);

            // 임시, 이후에 mid값 받아오도록 해야 함
            // 생성한 업체 저장
            companyRepository.save(companyInsert);

        } catch (Exception e) {
            logger.error("가맹점 등록 오류 {}", (Object) e.getStackTrace());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        return "Y";
    }

    // 조건 없이 전체 검색
    public PagingResponse<Company> findAllCompany(Pageable pageable, QueryModel queryModel){
        return new PagingResponse<>(companyRepositorySupport.findAllCompany(pageable,queryModel));
    }
    // keyword 로만 검색
    public PagingResponse<Company> findCompanyKeyword(Pageable pageable, QueryModel queryModel){
        return new PagingResponse<>(companyRepositorySupport.findCompanyKeyword(pageable,queryModel));
    }
    // 작성자/제목/내용 선택 검색
    public PagingResponse<Company> findByCompanySelectedKeyword(Pageable pageable, QueryModel queryModel){
        return new PagingResponse<>(companyRepositorySupport.findByCompanySelectedKeyword(pageable, queryModel));
    }

    // 업체 수정
    public String update(CompanyUpdateDto dto) throws NoSuchAlgorithmException {
        Company company = findById(dto.getId());

        // 실제 작성자 찾기
        String userId = dto.getUserId();
        User user = userRepositorySupport.findByUserId(userId); // 유저 id로 유저 찾아서 객체 만들기
        try {
            company.update(dto, user);
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
        return "Y";
    }

    // 업체 삭제
    public String delete(Long companyCode) throws NoSuchAlgorithmException {
        Company company = findById(companyCode);

        // 실제 작성자 찾기
        String userId = company.getUser().getUserId();
        User user = userRepositorySupport.findByUserId(userId); // 여기 작업중이었음. 유저 id로 유저 찾아서 객체 만들기
        try {
            company.delete();
            user.delete();
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
        return "Y";
    }
}
