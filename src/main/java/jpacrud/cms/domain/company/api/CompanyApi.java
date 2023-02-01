package jpacrud.cms.domain.company.api;

import jpacrud.cms.domain.company.entity.Company;
import jpacrud.cms.domain.company.dto.CompanyCreateDto;
import jpacrud.cms.domain.company.dto.CompanyUpdateDto;
import jpacrud.cms.domain.company.dto.QueryModel;
import jpacrud.cms.domain.company.service.CompanyService;
import jpacrud.cms.global.dto.request.PageRequest;
import jpacrud.cms.global.dto.response.ApiPagingResponse;
import jpacrud.cms.global.dto.response.ApiResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/company")
public class CompanyApi {

    // 로거 설정
    Logger logger = LoggerFactory.getLogger(CompanyApi.class);

    // 등록/업데이트 시 등록/업데이트한 사용자는 create_id 로 통일 필요함
    // user_id 는 등록시 새로 생성한 id 이기 때문

    // id 중복체크 api 필요함 (user API에 작성)

    private final CompanyService companyService;

    // 업체등록
    @ApiOperation("가맹점 등록")
    @PostMapping("/create")
    public ApiResponse<String> createCompanyCode(@RequestBody CompanyCreateDto dto) throws NoSuchAlgorithmException, IOException {
        return new ApiResponse<>(companyService.create(dto));
    }

    @ApiOperation("사용자 ID 중복확인")
    @GetMapping("/check/userId/{userId}")
    public ResponseEntity<Boolean> checkUserIdDuplicate(@PathVariable String userId) {
        return ResponseEntity.ok(companyService.checkUserIdDuplicate(userId));
    }

    // 업체코드검색 (현재 로그인한 업체 밑으로 검색하기)
    @ApiOperation("업체코드검색 (현재 로그인한 업체 밑으로 검색하기)")
    @GetMapping("/list")
    public ApiPagingResponse<Company> searchCompanyCode(
            @ApiParam(value = "현재 페이지 default 1") @RequestParam(defaultValue = "1",required = false) int page,
            @ApiParam(value = "페이지 Limit default 10") @RequestParam(defaultValue = "10",required = false) int limit,
            @ApiParam(value = "검색어 (keyword)") @RequestParam(required = false) String keyword,
            @ApiParam(value = "선택한 칼럼") @RequestParam(required = false) String columnName,
            @ApiParam(value = "시작 날짜 null 가능") @RequestParam(required = false) String startDate,
            @ApiParam(value = "끝나는 날짜 null 가능") @RequestParam(required = false) String endDate,
            @ApiParam(value = "현재 사용자의 Id") @RequestParam(required = false) String createId,
            @ApiParam(value = "업체의 레벨 (1:총판, 2:지사, 3:대리점)") @RequestParam(required = false) int companyLevel
    ){
        // 검색조건 : 업체명 / 대표자명 / 전화번호 / 담당자명 / 담장자연락처 / 로그인아이디
        // 현재 검색조건 : 업체명 / 대표자명 / 담당자명 / 로그인아이디
        if (keyword == null && columnName == null) {
            // 조건없이 전체검색
            // + 업체 본인의 레벨보다 상위의 레벨을 갖고있는 업체를 조회하도록 해야 함. -> 프론트에선 상위레벨을 +1 해서 보내줌 => 현재 맞는 레벨 생성
            return new ApiPagingResponse<>(companyService.findAllCompany(new PageRequest(page, limit).of(), new QueryModel(null, null, startDate, endDate, createId, companyLevel)));
        } else if (keyword != null && columnName == null) {
            // keyword 로만 검색
            return new ApiPagingResponse<>(companyService.findCompanyKeyword(new PageRequest(page, limit).of(), new QueryModel(keyword, null, startDate, endDate, createId, companyLevel)));
        } else if (keyword != null && columnName != null) {
            // keyword + columnName 전체 검색
            return new ApiPagingResponse<>(companyService.findByCompanySelectedKeyword(new PageRequest(page, limit).of(), new QueryModel(keyword, columnName, startDate, endDate, createId, companyLevel)));
        } else {
            // 검색조건 체크하고 검색어 넣지 않음. 전체검색으로 가정함.
            return new ApiPagingResponse<>(companyService.findAllCompany(new PageRequest(page, limit).of(), new QueryModel(null, columnName, startDate, endDate, createId, companyLevel)));
        }
    }

    // 업체 수정
    @ApiOperation("업체 수정 PUT")
    @PutMapping("/detail/update/modify")
    public ApiResponse<String> updateNotice(@RequestBody CompanyUpdateDto dto) throws NoSuchAlgorithmException {
        return new ApiResponse<>(companyService.update(dto));
    }

    // 업체 삭제
    @ApiOperation("업체 삭제 DELETE")
    @DeleteMapping("/detail/delete/{companyCode}")
    public ApiResponse<String> deleteNotice(@PathVariable Long companyCode) throws NoSuchAlgorithmException {
        return new ApiResponse<>(companyService.delete(companyCode));
    }

}
