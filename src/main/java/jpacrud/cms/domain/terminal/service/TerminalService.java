package jpacrud.cms.domain.terminal.service;

import jpacrud.cms.domain.franchise.repository.FranchiseRepositorySupport;
import jpacrud.cms.domain.terminal.dto.QueryModel;
import jpacrud.cms.domain.terminal.dto.TerminalCreateDto;
import jpacrud.cms.domain.terminal.dto.TerminalListDto;
import jpacrud.cms.domain.terminal.entity.Terminal;
import jpacrud.cms.domain.terminal.repository.TerminalRepository;
import jpacrud.cms.domain.terminal.repository.TerminalRepositorySupport;
import jpacrud.cms.domain.user.entity.User;
import jpacrud.cms.domain.user.repository.UserRepositorySupport;
import jpacrud.cms.domain.franchise.entity.Franchise;
import jpacrud.cms.domain.terminal.dto.TerminalUpdateDto;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TerminalService {

    // 로거 설정
    Logger logger = LoggerFactory.getLogger(TerminalService.class);
    private final UserRepositorySupport userRepositorySupport;
    private final TerminalRepository terminalRepository;
    private final TerminalRepositorySupport terminalRepositorySupport;
    private final FranchiseRepositorySupport franchiseRepositorySupport;

    // find by terminalTid (레포지토리)
    public Terminal findByTid(String tid) {
        return terminalRepositorySupport.findByTid(tid);
    }

    @Transactional(readOnly = true)
    public boolean checkTidDuplication(String tid) {
        boolean tidDuplicate = terminalRepository.existsByTid(tid);
        return tidDuplicate;
    }

    // 터미널 생성
    public String create(TerminalCreateDto dto) throws NoSuchAlgorithmException, IOException, Exception {

        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));

        System.out.println("넘겨받은 값 프랜차이즈코드 : " + dto.getFranchiseCode());
        System.out.println("넘겨받은 값 CreateId를 UserId로 사용함 : " + dto.getCreateId());
        System.out.println("넘겨받은 값 tid : " + dto.getTid());
        System.out.println("넘겨받은 값 bno : " + dto.getBno());

        try {

            Franchise franchise = franchiseRepositorySupport.findByFranchiseCode(dto.getFranchiseCode());

            dto.setFranchiseName(franchise.getUser().getAgentName());

            // 받아온 userId(사용자 계정)을 작성자(noticeAuthor)에 넣기
            String userId = dto.getCreateId();
            // 실제 작성자 찾기
            User user = userRepositorySupport.findByUserId(userId);

            dto.setCreateId(user.getUserId());
            if (dto.getTerminalStatus().equals("Y")) {
                dto.setTransactionStartDate(localDateTime);
                dto.setAuthYn("Y"); // 외부에서 받아오는 값인지 확실하지 않음
            }

            Terminal terminalInsert = Terminal.create(dto, franchise);

            if (terminalRepositorySupport.existsByTid(dto.getTid()) == true) {
                System.out.println("TID 존재하지 않음, 저장 가능함.");
                terminalRepository.save(terminalInsert);
            } else {
                System.out.println("TID 가 중복되어 저장할 수 없습니다. TID : "+dto.getTid());
                throw new BusinessException(ErrorCode.DUPLICATE_TID);
            }
        } catch (BusinessException e) {
            logger.error("중복된 TID 존재. 등록 불가능.");
            return "TID 가 중복되어 저장할 수 없습니다. TID : "+dto.getTid();
        } catch (Exception e) {
            logger.error("단말기 등록 오류 {}", (Object) e.getStackTrace());
            System.out.println(Arrays.toString(e.getStackTrace()));
            return "N";
        }
        System.out.println("오류 안남. 잘 저장됨");
        return "Y";
    }

    // 조건 없이 전체 검색
    public PagingResponse<TerminalListDto> findAllTerminal(Pageable pageable, QueryModel queryModel){
        return new PagingResponse<>(terminalRepositorySupport.findAllTerminal(pageable,queryModel));
    }
    // keyword 로만 검색
    public PagingResponse<TerminalListDto> findTerminalKeyword(Pageable pageable, QueryModel queryModel){
        return new PagingResponse<>(terminalRepositorySupport.findTerminalKeyword(pageable,queryModel));
    }
    // 작성자/제목/내용 선택 검색
    public PagingResponse<TerminalListDto> findByTidCodeAgencyFranchise(Pageable pageable, QueryModel queryModel){
        return new PagingResponse<>(terminalRepositorySupport.findByTidCodeAgencyFranchise(pageable, queryModel));
    }

    // 공지 수정 PUT
    public String update(TerminalUpdateDto dto, String tid) throws NoSuchAlgorithmException {
        Franchise franchise = null;
//        Terminal terminal = terminalRepositorySupport.findById(dto.getId());
        Terminal terminal = null;
        try {
            franchise = franchiseRepositorySupport.findByFranchiseCode(dto.getFranchiseCode());
            dto.setFranchiseName(franchise.getUser().getAgentName());

            terminal = terminalRepositorySupport.findByTid(tid);

            if (terminalRepositorySupport.existsByTid(dto.getTid()) == true) {
                System.out.println("TID 존재하지 않음, 저장 가능함.");
            } else {
                System.out.println("TID 가 중복되어 저장할 수 없습니다. TID : " + dto.getTid());
                throw new BusinessException(ErrorCode.DUPLICATE_TID);
            }
        } catch (BusinessException e) {
            logger.error("중복된 TID 존재. 등록 불가능.");
            return "TID 가 중복되어 저장할 수 없습니다. TID : "+dto.getTid();
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
        String userId = dto.getCreateId();
        System.out.println("크리에이트아디 받아오면?"+userId);
        User user = userRepositorySupport.findByUserId(userId); // 유저 id로 유저 찾아서 객체 만들기
        try {
            terminal.update(dto, user, franchise);
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
        return "Y";
    }

    // 공지 삭제 DELETE
    public String delete(String tid) {
        Terminal terminal = terminalRepositorySupport.findByTid(tid);
        try {
            terminal.delete();
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
        return "Y";
    }




}
