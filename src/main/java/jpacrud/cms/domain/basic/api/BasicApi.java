package jpacrud.cms.domain.basic.api;

import jpacrud.cms.domain.basic.dto.BasicCreateDto;
import jpacrud.cms.domain.basic.dto.BasicListDto;
import jpacrud.cms.domain.basic.dto.BasicUpdateDto;
import jpacrud.cms.domain.basic.dto.QueryModel;
import jpacrud.cms.domain.basic.service.BasicService;
import jpacrud.cms.global.dto.request.PageRequest;
import jpacrud.cms.global.dto.response.ApiPagingResponse;
import jpacrud.cms.global.dto.response.ApiResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/basic")
public class BasicApi {

    // 로거 설정
    Logger logger = LoggerFactory.getLogger(BasicService.class);

    private final BasicService basicService;

    public static boolean isNumeric(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

    @ApiOperation("등록")
    @PostMapping("/create")
    public ApiResponse<String> createAdmin(@RequestBody BasicCreateDto dto) throws NoSuchAlgorithmException {
        return new ApiResponse<>(basicService.create(dto));
    }

    @ApiOperation("리스트 + 페이징")
    @GetMapping("/list")
    public ApiPagingResponse<BasicListDto> findAllPaging(
            @ApiParam(value = "현재 페이지 default 1") @RequestParam(defaultValue = "1",required = false) int page,
            @ApiParam(value = "페이지 Limit default 10") @RequestParam(defaultValue = "10",required = false) int limit,
            @ApiParam(value = "검색어 (keyword)") @RequestParam(required = false) String keyword,
            @ApiParam(value = "선택한 칼럼") @RequestParam(required = false) String columnName,
            @ApiParam(value = "시작 날짜 null 가능") @RequestParam(required = false) String startDate,
            @ApiParam(value = "끝나는 날짜 null 가능") @RequestParam(required = false) String endDate
    ){
        if (keyword == null && columnName == null) {
            // 조건없이 전체검색
            return new ApiPagingResponse<>(basicService.findAllCompany(new PageRequest(page,limit).of(),new QueryModel(null, null,startDate,endDate)));
        } else if (keyword != null && columnName == null) {
            if (isNumeric(keyword)) {
                keyword = Integer.toString(Integer.parseInt(keyword));
            }
            // keyword 로만 검색
            return new ApiPagingResponse<>(basicService.findCompanyKeyword(new PageRequest(page,limit).of(),new QueryModel(keyword, null,startDate,endDate)));
        } else if (keyword != null && columnName != null) {
            if (isNumeric(keyword)) {
                keyword = Integer.toString(Integer.parseInt(keyword));
            }
            // keyword + columnName 전체 검색
            return new ApiPagingResponse<>(basicService.findByCompanyNumNameContent(new PageRequest(page, limit).of(), new QueryModel(keyword, columnName, startDate, endDate)));
        } else {
            // 검색조건 체크하고 검색어 넣지 않음. 전체검색으로 가정함.
            return new ApiPagingResponse<>(basicService.findAllCompany(new PageRequest(page,limit).of(),new QueryModel(null,columnName,startDate,endDate)));
        }
    }

    @ApiOperation("가맹점 수정 PUT")
    @PutMapping("/detail/update/modify")
    public ApiResponse<String> updateCompany(@RequestBody BasicUpdateDto dto) throws NoSuchAlgorithmException {
        return new ApiResponse<>(basicService.update(dto));
    }

    @ApiOperation("가맹점 삭제 DELETE")
    @DeleteMapping("/detail/delete/{companyId}")
    public ApiResponse<String> deleteCompany(@PathVariable Long companyId) {
        return new ApiResponse<>(basicService.delete(companyId));
    }
}
