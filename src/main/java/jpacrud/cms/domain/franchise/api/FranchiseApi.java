package jpacrud.cms.domain.franchise.api;

import jpacrud.cms.domain.franchise.dto.FranchiseCreateDto;
import jpacrud.cms.domain.franchise.dto.FranchiseUpdateDto;
import jpacrud.cms.domain.franchise.entity.Franchise;
import jpacrud.cms.domain.franchise.service.FranchiseService;
import jpacrud.cms.domain.franchise.dto.QueryModel;
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
@RequestMapping("/franchise")
public class FranchiseApi {

    // 로거 설정
    Logger logger = LoggerFactory.getLogger(FranchiseApi.class);

    // 등록/업데이트 시 등록/업데이트한 사용자는 create_id 로 통일 필요함
    // 왜냐하면 user_id 는 등록시 새로 생성한 id 이기 때문

    // id 중복체크 api 필요함

    private final FranchiseService franchiseService;

    // 가맹점 등록
    @ApiOperation("가맹점 등록")
    @PostMapping("/create")
    public ApiResponse<String> createFranchise(@RequestBody FranchiseCreateDto dto) throws NoSuchAlgorithmException, IOException {
        return new ApiResponse<>(franchiseService.create(dto));
    }

    @ApiOperation("사용자 ID 중복확인")
    @GetMapping("/check/userId/{userId}")
    public ResponseEntity<Boolean> checkUserIdDuplicate(@PathVariable String userId) {
        return ResponseEntity.ok(franchiseService.checkUserIdDuplicate(userId));
    }

    // 업체검색 (CompanyAPI 에 구현)

    // 가맹점 리스트
    // 리스트 출력 위해 프론트에서 주는 정보 : 상위업체패스 + 업체코드 + 업체명
    @ApiOperation("리스트 + 페이징")
    @GetMapping("/list")
    public ApiPagingResponse<Franchise> findAllPaging(
            @ApiParam(value = "현재 페이지 default 1") @RequestParam(defaultValue = "1",required = false) int page,
            @ApiParam(value = "페이지 Limit default 10") @RequestParam(defaultValue = "10",required = false) int limit,
            @ApiParam(value = "검색어 (keyword)") @RequestParam(required = false) String keyword,
            @ApiParam(value = "선택한 칼럼") @RequestParam(required = false) String columnName,
            @ApiParam(value = "시작 날짜 null 가능") @RequestParam(required = false) String startDate,
            @ApiParam(value = "끝나는 날짜 null 가능") @RequestParam(required = false) String endDate,
            @ApiParam(value = "현재 사용자의 Id") @RequestParam(required = false) String userId
    ){
        if (keyword == null && columnName == null) {
            // 조건없이 전체검색
            return new ApiPagingResponse<>(franchiseService.findAllFranchise(new PageRequest(page,limit).of(),new QueryModel(null, null,startDate,endDate, userId)));
        } else if (keyword != null && columnName == null) {
            // keyword 로만 검색
            return new ApiPagingResponse<>(franchiseService.findFranchiseKeyword(new PageRequest(page,limit).of(),new QueryModel(keyword, null,startDate,endDate, userId)));
        } else if (keyword != null && columnName != null) {
            // keyword + columnName 전체 검색
            return new ApiPagingResponse<>(franchiseService.findByFranchiseSelectedKeyword(new PageRequest(page, limit).of(), new QueryModel(keyword, columnName, startDate, endDate, userId)));
        } else {
            // 검색조건 체크하고 검색어 넣지 않음. 전체검색으로 가정함.
            return new ApiPagingResponse<>(franchiseService.findAllFranchise(new PageRequest(page,limit).of(),new QueryModel(null,columnName,startDate,endDate, userId)));
        }
    }

    // 가맹점 수정
    @ApiOperation("가맹점 수정 PUT")
    @PutMapping("/detail/update/modify")
    public ApiResponse<String> updateFranchise(@RequestBody FranchiseUpdateDto dto) throws NoSuchAlgorithmException {
        return new ApiResponse<>(franchiseService.update(dto));
    }

    // 가맹점 삭제
    @ApiOperation("가맹점 삭제 DELETE")
    @DeleteMapping("/detail/delete/{franchiseCode}")
    public ApiResponse<String> deleteFranchise(@PathVariable Long franchiseCode) throws NoSuchAlgorithmException {
        return new ApiResponse<>(franchiseService.delete(franchiseCode));
    }
}
