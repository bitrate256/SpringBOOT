package jpacrud.cms.domain.basic.service;

import jpacrud.cms.domain.basic.dto.BasicUpdateDto;
import jpacrud.cms.domain.basic.entity.BasicEntity;
import jpacrud.cms.global.dto.response.PagingResponse;
import jpacrud.cms.global.error.exception.BusinessException;
import jpacrud.cms.global.error.model.ErrorCode;
import jpacrud.cms.domain.basic.dto.BasicCreateDto;
import jpacrud.cms.domain.basic.dto.BasicListDto;
import jpacrud.cms.domain.basic.repository.BasicRepository;
import jpacrud.cms.domain.basic.repository.BasicRepositorySupport;
import jpacrud.cms.domain.basic.dto.QueryModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BasicService {

    // 로거 설정
    Logger logger = LoggerFactory.getLogger(BasicService.class);

    private final BasicRepository basicRepository;
    private final BasicRepositorySupport basicRepositorySupport;

    // find by companyId (레포지토리)
    public BasicEntity findById(Long companyId) {
        return basicRepository.findById(companyId).orElseThrow(() -> new BusinessException(ErrorCode.ENTITY_NOT_FOUND));
    }

    // 가맹점 생성
    public String create(BasicCreateDto dto) throws NoSuchAlgorithmException {

        BasicEntity basicEntity = BasicEntity.create(dto);
        basicEntity.setUseYN("Y");
        basicRepository.save(basicEntity);

        return "Y";
    }

    // 조건 없이 전체 검색
    public PagingResponse<BasicListDto> findAllCompany(Pageable pageable, QueryModel queryModel){
        return new PagingResponse<>(basicRepositorySupport.findAllCompany(pageable,queryModel));
    }
    // keyword 로만 검색
    public PagingResponse<BasicListDto> findCompanyKeyword(Pageable pageable, QueryModel queryModel){
        return new PagingResponse<>(basicRepositorySupport.findCompanyKeyword(pageable,queryModel));
    }

    public PagingResponse<BasicListDto> findByCompanyNumNameContent(Pageable pageable, QueryModel queryModel){
        return new PagingResponse<>(basicRepositorySupport.findByCompanyNumNameContent(pageable, queryModel));
    }

    // 가맹점 수정 PUT
    public String update(BasicUpdateDto dto) throws NoSuchAlgorithmException {
        BasicEntity basicEntity = findById(dto.getCompanyId());
        try {
            basicEntity.update(dto);
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
        return "Y";
    }

    // 가맹점 삭제 DELETE
    public String delete(Long companyId) {
        BasicEntity basicEntity = findById(companyId);
        try {
            basicEntity.delete();
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
        return "Y";
    }
}
