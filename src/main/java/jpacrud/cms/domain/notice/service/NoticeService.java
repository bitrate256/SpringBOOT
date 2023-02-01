package jpacrud.cms.domain.notice.service;

import jpacrud.cms.domain.user.entity.User;
import jpacrud.cms.domain.notice.dto.NoticeCreateDto;
import jpacrud.cms.domain.notice.dto.NoticeListDto;
import jpacrud.cms.domain.notice.dto.NoticeUpdateDto;
import jpacrud.cms.domain.notice.entity.Notice;
import jpacrud.cms.domain.notice.repository.NoticeRepository;
import jpacrud.cms.domain.notice.repository.NoticeRepositorySupport;
import jpacrud.cms.domain.notice.dto.QueryModel;
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

import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class NoticeService {

    // 로거 설정
    Logger logger = LoggerFactory.getLogger(NoticeService.class);

    private final UserRepository userRepository;
    private final UserRepositorySupport userRepositorySupport;
    private final NoticeRepository noticeRepository;
    private final NoticeRepositorySupport noticeRepositorySupport;

    // find by noticeId (레포지토리)
    public Notice findById(Long noticeId) {
        return noticeRepository.findById(noticeId).orElseThrow(() -> new BusinessException(ErrorCode.ENTITY_NOT_FOUND));
    }

    // 공지 생성
    public String create(NoticeCreateDto dto) throws NoSuchAlgorithmException {

        // 받아온 userId(사용자 계정)을 작성자(noticeAuthor)에 넣기
        String userId = dto.getUserId();
        String createId = dto.getUserId();

        // 실제 작성자 찾기
        User user = userRepositorySupport.findByUserId(userId);

        if (userId.equals(user.getUserId())) {
            dto.setId(user.getId());
            dto.setNoticeAuthor(user.getUserId());

            Notice notice = Notice.create(dto, user);
            notice.setCreateId(createId);
            notice.setNoticeYn("Y");
            notice.setDelYn("Y");
            noticeRepository.save(notice);
            return "Y";
        } else {
            logger.debug("올바른 작성자가 아님");
            return "N";
        }
    }

    // 조건 없이 전체 검색
    public PagingResponse<NoticeListDto> findAllNotice(Pageable pageable, QueryModel queryModel){
        return new PagingResponse<>(noticeRepositorySupport.findAllNotice(pageable,queryModel));
    }
    // keyword 로만 검색
    public PagingResponse<NoticeListDto> findNoticeKeyword(Pageable pageable, QueryModel queryModel){
        return new PagingResponse<>(noticeRepositorySupport.findNoticeKeyword(pageable,queryModel));
    }
    // 작성자/제목/내용 선택 검색
    public PagingResponse<NoticeListDto> findByNoticeAuthorNameContent(Pageable pageable, QueryModel queryModel){
        return new PagingResponse<>(noticeRepositorySupport.findByNoticeAuthorNameContent(pageable, queryModel));
    }

    // 공지 수정 PUT
    public String update(NoticeUpdateDto dto) throws NoSuchAlgorithmException {
        Notice notice = findById(dto.getId());
        try {
            notice.update(dto);
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
        return "Y";
    }

    // 공지 삭제 DELETE
    public String delete(Long noticeId) {
        Notice notice = findById(noticeId);
        try {
            notice.delete();
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
        return "Y";
    }
    // 조회수 카운팅
    public String viewCount(Long noticeId) {
        Notice notice = findById(noticeId);
        try {
            notice.setView(notice.getView()+1);
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
        return "Y";
    }
}
