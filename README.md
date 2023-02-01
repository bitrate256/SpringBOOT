# jpacrud CMS 백엔드 (SPRING BOOT JPA)

## 목적
__jpacrud CMS__ 의 백엔드 개발.  
프론트 프로젝트 와는 분리보관 합니다.  
개발 방법론은 개발정책 repository를 따릅니다.  

### 1. Version Control
+ v 0.1 jpacrud CMS (JPA)
+ 프론트 : 미정

### 2. 테스트 서버 배포 정보
+ 서버 : 미정
+ 포트 : 9400
+ 배포 디렉토리 : /home/사용자명/jpacrud/CMS
+ 리소스 디렉토리 : /home/사용자명/jpacrud/CMS/resource
+ swagger 주소 : http://localhost:9400/api/jpacrud/swagger-ui/#/

### 2-1. DB 정보
localhost(미정)  
Database : jpacrudCMS    
user : root  
password : root  

### 3. 기능
+ 기초적인 게시판 CRUD  
  (로그인/등록/리스트+페이징/수정/삭제)  
+ 글로벌 기능 전부 재사용 가능
+ 현재 spring security 미적용 (검토 필요)

### 4. 추가할 것들
+ 엑셀 내보내기
+ 검색 로직 효율 개선
