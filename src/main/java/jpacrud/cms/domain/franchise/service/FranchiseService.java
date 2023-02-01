package jpacrud.cms.domain.franchise.service;

import jpacrud.cms.domain.company.entity.Company;
import jpacrud.cms.domain.company.repository.CompanyRepository;
import jpacrud.cms.domain.franchise.dto.FranchiseCreateDto;
import jpacrud.cms.domain.franchise.dto.FranchiseUpdateDto;
import jpacrud.cms.domain.franchise.repository.FranchiseRepository;
import jpacrud.cms.domain.franchise.repository.FranchiseRepositorySupport;
import jpacrud.cms.domain.franchise.repository.PgCompanyRepository;
import jpacrud.cms.domain.franchise.repository.PgCompanyRepositorySupport;
import jpacrud.cms.domain.user.entity.User;
import jpacrud.cms.domain.franchise.dto.QueryModel;
import jpacrud.cms.domain.franchise.entity.Franchise;
import jpacrud.cms.domain.franchise.entity.PgCompany;
import jpacrud.cms.domain.user.repository.UserRepository;
import jpacrud.cms.domain.user.repository.UserRepositorySupport;
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
public class FranchiseService {

    // 로거 설정
    Logger logger = LoggerFactory.getLogger(FranchiseService.class);

    private final UserRepository userRepository;
    private final UserRepositorySupport userRepositorySupport;
    private final PgCompanyRepository pgCompanyRepository;
    private final FranchiseRepository franchiseRepository;
    private final CompanyRepository companyRepository;

    private final FranchiseRepositorySupport franchiseRepositorySupport;
    private final PgCompanyRepositorySupport pgCompanyRepositorySupport;

    @Transactional(readOnly = true)
    public boolean checkUserIdDuplicate(String userId) {
        boolean userIdDuplicate = franchiseRepositorySupport.existsByUserId(userId);
        return userIdDuplicate;
    }

    private Franchise findByIdFranchise(Long franchiseCode) {
        return franchiseRepository.findById(franchiseCode).orElseThrow(() -> new BusinessException(ErrorCode.ENTITY_NOT_FOUND));
    }

    private PgCompany findByIdPgCompany(Long pgCompanyId) {
        return pgCompanyRepository.findById(pgCompanyId).orElseThrow(() -> new BusinessException(ErrorCode.ENTITY_NOT_FOUND));
    }

    // find by companyCode (레포지토리)
    public Company findByIdCompany(Long companyCode) {
        return companyRepository.findById(companyCode).orElseThrow(() -> new BusinessException(ErrorCode.ENTITY_NOT_FOUND));
    }

    // 가맹점 등록
    public String create(FranchiseCreateDto dto) throws NoSuchAlgorithmException, IOException {


//        user.userId.append(dto.getUserId());
//        user.userPw.append(dto.getUserPw());
//        user.userAuth.append(dto.getUserAuth());
//        user.

//        User userCreateId = userRepositorySupport.findByUserId(dto.getCreateId());
//        Long createdId = userCreateId.getCreateId();
//        dto.setCreateId(createdId);
        try {
        // PG사 ID를 (pgCompanyId) 삽입해서 pgCompany찾기
        PgCompany pgCompany = pgCompanyRepository.findById(dto.getPgCompanyId()).orElseThrow(() -> new BusinessException(ErrorCode.ENTITY_NOT_FOUND));

        User userInsert = User.createWithFranchise(dto);

        logger.debug("create_id (작성자 id) {}",userInsert.getCreateId());
        logger.debug("user_id (생성하고자 하는 id) {}",userInsert.getUserId());

        // 사용자 저장
        userRepository.save(userInsert);
        // 사용자Seq 삽입하기 위해 user_info ID(계정) 이용하여 사용자 찾아오기
        User user = userRepositorySupport.findByUserId(dto.getCreateId());

        // 업체Code 삽입하기 위해 userSeq 이용하여 Company 찾아오기
        Company company = companyRepository.findById(user.getId()).orElseThrow(() -> new BusinessException(ErrorCode.ENTITY_NOT_FOUND));
        logger.debug("company ID????? {}",company.getId());
        Franchise franchiseInsert = Franchise.create(dto, pgCompany, user, company);

        // 임시, 이후에 mid값 받아오도록 해야 함
        dto.setMid("12345");
        logger.debug("franchiseInsert ID????? {}",franchiseInsert.getId());
        franchiseRepository.save(franchiseInsert);

        } catch (Exception e) {
            logger.error("가맹점 등록 오류 {}", (Object) e.getStackTrace());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        return "Y";
    }

    // 조건 없이 전체 검색
    public PagingResponse<Franchise> findAllFranchise(Pageable pageable, QueryModel queryModel){
        return new PagingResponse<>(franchiseRepositorySupport.findAllFranchise(pageable,queryModel));
    }
    // keyword 로만 검색
    public PagingResponse<Franchise> findFranchiseKeyword(Pageable pageable, QueryModel queryModel){
        return new PagingResponse<>(franchiseRepositorySupport.findFranchiseKeyword(pageable,queryModel));
    }
    // 작성자/제목/내용 선택 검색
    public PagingResponse<Franchise> findByFranchiseSelectedKeyword(Pageable pageable, QueryModel queryModel){
        return new PagingResponse<>(franchiseRepositorySupport.findByFranchiseSelectedKeyword(pageable, queryModel));
    }

    // 가맹점 수정
    public String update(FranchiseUpdateDto dto) throws NoSuchAlgorithmException {
        Franchise franchise = findByIdFranchise(dto.getId());
        PgCompany pgCompany = findByIdPgCompany(dto.getPgCompanyId());
        Company company = findByIdCompany(dto.getCompanyCode());
        // 살제 작성자 찾기
        String userId = dto.getUserId();
        User user = userRepositorySupport.findByUserId(userId); // 여기 작업중이었음. 유저 id로 유저 찾아서 객체 만들기
        try {
            franchise.update(dto, user, pgCompany, company);
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
        return "Y";
    }


    public String delete(Long franchiseCode) {
        Franchise franchise = findByIdFranchise(franchiseCode);
        // 살제 작성자 찾기
        String userId = franchise.getUser().getUserId();
        User user = userRepositorySupport.findByUserId(userId); // 여기 작업중이었음. 유저 id로 유저 찾아서 객체 만들기
        try {
            franchise.delete();
            user.delete();
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
        return "Y";
    }
}
