<h2>📎 Blog Project</h2>
<br></br>
<p data-ke-size="size16"><b>SpringBoot JPA 를 이용한 개인 블로그 프로젝트 입니다.</b></p>
<img width="1440" alt="스크린샷 2023-03-02 오후 2 19 50" src="https://user-images.githubusercontent.com/90013740/222338450-858a36b3-7185-4d55-b617-3c860c000daf.png">
<br></br>
<h2>들어가며</h2>
<h3>1. 프로젝트 소개</h3>
<p>프로젝트를 시작하게 된 계기는 나만의 개인 기술 블로그를 만들고 싶어 시작했습니다.<br>
독학으로 관련 기술들을 학습한 후 제작한 개인적 만족감을 가지고 있는 프로젝트입니다 </p>
<h3>2. 프로젝트 기능</h3>
<ul><p>프로젝트의 주요 기능은 다음과 같습니다.</p>
  <li>블로그 - CRUD 기능, 파일업로드(현재 진행중), 조회수, 페이징 및 좋아요(현재 진행중) 기능</li>
  <li>사용자 - Security,JWT를 이용한 회원가입 및 로그인(현재 진행중), 회원정보 수정, 회원가입시 유효성 검사 및 중복 검사</li>
  <li>댓글 - CRUD 기능</li>
</ul>
<h3>3. 사용 기술</h3>
<h4>3-1 백엔드</h4>
  <h5><p>주요 프레임워크 / 라이브러리<p></h5>
  <ul>
    <li>Java 11</li>
    <li>SpringBoot 2.5.6</li>
    <li>JPA(Spring Data JPA)</li>
    <li>Spring Security</li>
    <li>JWT</li>
  </ul>
  <h5><p>Build Tool<p></h5>
  <ul>
    <li>Gradle</li>
  </ul>
  <h5><p>DataBase<p></h5>
  <ul>
    <li>Postgresql</li>
  </ul>
  <h4>3-2 프론트엔드</h4>
  <ul>
    <li>Html/Css</li>
    <li>JavaScript</li>
    <li>Thymeleaf</li>
    <li>Bootstrep</li>
  </ul>
  <h3>4.구현 화면</h3>
  <details>
  <summary><h4>게시글 관련</h4></summary>
    <div markdown="1">
      <strong>1. 게시글 전체 목록</strong>
      <img width="1575" alt="게시글 전체 목록" src="https://user-images.githubusercontent.com/90013740/222345919-d2a2025a-49a7-4037-80a3-2ba367bd8d62.png">
      <strong>2. 게시글 등록</strong>
      <img width="1605" alt="게시글 등록" src="https://user-images.githubusercontent.com/90013740/222346469-f563da18-bbf0-4256-8d95-4a324623cec7.png">
      <strong>3. 게시글 상세보기</strong>
      <img width="1508" alt="게시글 상세보기" src="https://user-images.githubusercontent.com/90013740/222346585-7aa36121-f60e-4753-bfd6-736bdbce83af.png">
      <strong>4. 게시글 수정</strong>
      <img width="1819" alt="게시글수정1" src="https://user-images.githubusercontent.com/90013740/222347972-96584e2b-ce41-475e-baaf-1b19696f65e3.png">
      <img width="1745" alt="게시글수정2" src="https://user-images.githubusercontent.com/90013740/222347987-44d08a21-370d-4275-af7e-e09c31f87c3c.png">
      <strong>5. 게시글 삭제</strong>
      <img width="1848" alt="게시글 삭제1" src="https://user-images.githubusercontent.com/90013740/222348205-95444f4c-c79b-4bcd-b409-e72d5302fe9f.png">
      <img width="1842" alt="게시글 삭제2" src="https://user-images.githubusercontent.com/90013740/222348226-a0fbde01-162e-48b6-bbed-123a4695eaa4.png">
  </div>
  </details>
  <details>
  <summary><h4>댓글 관련</h4></summary>
    <div markdown="1">
      <strong>1.댓글 등록</strong>
      <img width="1427" alt="댓글 등록" src="https://user-images.githubusercontent.com/90013740/222349311-1991afe6-b529-4d95-9e6f-ae4ddf611908.png">
      <img width="1508" alt="댓글 등록2" src="https://user-images.githubusercontent.com/90013740/222349335-f470cfff-19d5-4d8d-8a80-423e238f7e01.png">
      <strong>2.댓글 삭제</strong>
      <img width="1816" alt="댓글 삭제" src="https://user-images.githubusercontent.com/90013740/222349405-4c0d2110-8ff8-488e-8c23-0b38080c96e1.png">
      <img width="1827" alt="댓글 삭제2" src="https://user-images.githubusercontent.com/90013740/222349424-7ea9a10e-f763-4983-8c1f-d90b68248770.png">
      <strong>3.댓글 수정</strong>
      화면 
  </div>
  </details>
  <br></br>
  <h3>5.구조 및 설계</h3>
  <strong>1.DB 설계</strong>
  ![image](https://user-images.githubusercontent.com/90013740/222350999-1f412dc0-4ee4-475d-87bd-8f1378fe84e5.png)

