package jpacrud.cms.domain.terminal.api;

import jpacrud.cms.domain.terminal.dto.QueryModel;
import jpacrud.cms.domain.terminal.dto.TerminalCreateDto;
import jpacrud.cms.domain.terminal.dto.TerminalListDto;
import jpacrud.cms.domain.terminal.service.TerminalService;
import jpacrud.cms.domain.terminal.dto.TerminalUpdateDto;
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

import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/terminal")
public class TerminalApi {

    // 로거 설정
    Logger logger = LoggerFactory.getLogger(TerminalApi.class);
    private final TerminalService terminalService;

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
    public ApiResponse<String> createTerminal(@RequestBody TerminalCreateDto dto) throws Exception {
        return new ApiResponse<>(terminalService.create(dto));
    }

    @ApiOperation("tid중복확인")
    @GetMapping("/check/tid/{tid}")
    public ResponseEntity<Boolean> checkTidDuplicate(@PathVariable String tid) {
        return ResponseEntity.ok(terminalService.checkTidDuplication(tid));
    }

    // 추가사항 : 업체명 출력해야 함
    // 터미널 리스트
    @ApiOperation("리스트 + 페이징")
    @GetMapping("/list")
    public ApiPagingResponse<TerminalListDto> findAllPaging(
            @ApiParam(value = "현재 페이지 default 1") @RequestParam(defaultValue = "1",required = false) int page,
            @ApiParam(value = "페이지 Limit default 10") @RequestParam(defaultValue = "10",required = false) int limit,
            @ApiParam(value = "검색어 (keyword)") @RequestParam(required = false) String keyword,
            @ApiParam(value = "선택한 칼럼") @RequestParam(required = false) String columnName,
            @ApiParam(value = "시작 날짜 null 가능") @RequestParam(required = false) String startDate,
            @ApiParam(value = "끝나는 날짜 null 가능") @RequestParam(required = false) String endDate
    ){
        if (keyword == null && columnName == null) {
            // 조건없이 전체검색
            return new ApiPagingResponse<>(terminalService.findAllTerminal(new PageRequest(page,limit).of(),new QueryModel(null, null,startDate,endDate)));
        } else if (keyword != null && columnName == null) {
            // keyword 로만 검색
            return new ApiPagingResponse<>(terminalService.findTerminalKeyword(new PageRequest(page,limit).of(),new QueryModel(keyword, null,startDate,endDate)));
        } else if (keyword != null && columnName != null) {
            if (isNumeric(keyword)) {
                keyword = Integer.toString(Integer.parseInt(keyword));
            }
            // keyword + columnName 전체 검색 (단말기명 / 업체명 / 가맹점명)
            return new ApiPagingResponse<>(terminalService.findByTidCodeAgencyFranchise(new PageRequest(page, limit).of(), new QueryModel(keyword, columnName, startDate, endDate)));
        } else {
            // 검색조건 체크하고 검색어 넣지 않음. 전체검색으로 가정함.
            return new ApiPagingResponse<>(terminalService.findAllTerminal(new PageRequest(page,limit).of(),new QueryModel(null,columnName,startDate,endDate)));
        }
    }

    // 공지 수정
    @ApiOperation("단말기 수정 PUT")
    @PutMapping("/detail/update/{tid}")
    public ApiResponse<String> updateTerminal(@RequestBody TerminalUpdateDto dto, @PathVariable String tid) throws NoSuchAlgorithmException {
        return new ApiResponse<>(terminalService.update(dto, tid));
    }

    // 공지 삭제
    @ApiOperation("단말기 삭제 DELETE")
    @DeleteMapping("/detail/delete/{tid}")
    public ApiResponse<String> deleteTerminal(@PathVariable String tid) {
        return new ApiResponse<>(terminalService.delete(tid));
    }
}
