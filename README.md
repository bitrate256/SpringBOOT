# jpacrud CMS 백엔드 (SPRING BOOT JPA)

## 목적
__jpacrud CMS__ 의 백엔드 개발.  
프론트 프로젝트 와는 분리보관 합니다.  
개발 방법론은 개발정책 repository를 따릅니다.  

### 1. Version Control
+ v 0.1 jpacrud.cms (JPA)
+ 프론트 : 미정

### 2. 테스트 서버 배포 정보
+ 서버 : 미정
+ 포트 : 9400
+ 배포 디렉토리 : /home/사용자명/jpacrud/cms
+ 리소스 디렉토리 : /home/사용자명/jpacrud/cms/resource
+ API 테스트 주소 : http://localhost:9400/api/jpacrud/cms/swagger-ui/#/

### 2-1. DB 정보
localhost  
Database : jpacrudcms    
user : jpacrudcms  
password : jpacrudcms  

### 3. 기능
+ CRUD  
  (로그인/등록/리스트+페이징/수정/삭제)  
+ 글로벌 기능 전부 재사용 가능
+ 현재 spring security 미적용

### 4. 추가할 것들
+ 엑셀 내보내기
+ 검색 로직 효율 개선
+ 회원(user) 기능 신규개발
  (가입ID중복체크 / 회원가입,수정,탈퇴 / 계정,비밀번호찾기)
+ 외부 요청 받아 결제내역 저장 기능 신규개발
+ http 응답코드 표준화
+ spring security 적용
