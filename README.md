### ✨개요
**********************
* **프로젝트 이름** : Talk
* **프로젝트 제작기간** : 2024.02.23 ~ 2024.02.28
* **프로젝트 설명** :
  * 로그인 전에는 다른 사용자들이 작성한 게시물들을 조회하는 기능만 사용하능하며, 로그인 후에는 마이페이지 수정, 팔로우, 좋아요, 게시글 작성, 좋아요 작성과 같은 기능들을 제공합니다.
  * 팔로우 같은 경우 팔로우 하고 싶은 유저의 소개 페이지 에서 팔로우가 가능합니다.
  * 게시물 조회는 작성일 기준, 좋아요순, 조회순으로 정렬 할 수 있으며, 내가 좋아요 표시나 팔로우 한 게시물 또한 조회하는 것이 가능합니다.
### 👷팀원소개
*********************
* **김형우** : Post 관련 API 제작 담당
* **김범진** : Post조회, Follow, Like 관련 API 제작 담당
* **서예진** : 프로필 관련 기능 구현
###  ⚙개발환경
*********************
<img src="https://img.shields.io/badge/Framework-%23121011?style=for-the-badge"><img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"><img src="https://img.shields.io/badge/3.22-515151?style=for-the-badge">

<img src="https://img.shields.io/badge/Build-%23121011?style=for-the-badge"><img src="https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=Gradle&logoColor=white"><img src="https://img.shields.io/badge/8.5-515151?style=for-the-badge">

<img src="https://img.shields.io/badge/Language-%23121011?style=for-the-badge"><img src="https://img.shields.io/badge/java-%23ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"><img src="https://img.shields.io/badge/17-515151?style=for-the-badge">

<img src="https://img.shields.io/badge/Project Encoding-%23121011?style=for-the-badge"><img src="https://img.shields.io/badge/UTF 8-EA2328?style=for-the-badge">

<img src="https://img.shields.io/badge/DataBase-%23121011?style=for-the-badge"><img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"><img src="https://img.shields.io/badge/8.3-515151?style=for-the-badge">

<img src="https://img.shields.io/badge/Security-%23121011?style=for-the-badge"><img src="https://img.shields.io/badge/springsecurity-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white"/>

###  ✅API명세서
*********************
![image](https://github.com/2xception/Outsourcing_Project/assets/154823447/5aa8e928-d8e1-49ac-9f99-b9607f4f28ac)

###  🎨와이어 프레임
*********************
<img src="https://teamsparta.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F83c75a39-3aba-4ba4-a792-7aefe4b07895%2F3a71b302-a679-47ae-bc67-66e20af18f77%2FUntitled.png?table=block&id=afff0626-c29d-4182-b8fa-ac53bbbbaf50&spaceId=83c75a39-3aba-4ba4-a792-7aefe4b07895&width=2000&userId=&cache=v2"/>

###  🔨ERD DIAGRAM
*********************
<img src="https://teamsparta.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F83c75a39-3aba-4ba4-a792-7aefe4b07895%2Fd02b9696-dcd8-47e2-a58a-7b96d9389262%2F%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2024-02-28_%25E1%2584%258B%25E1%2585%25A9%25E1%2584%2592%25E1%2585%25AE_3.20.40.png?table=block&id=84fb1391-79f6-4803-afe2-14df4f6cc8ff&spaceId=83c75a39-3aba-4ba4-a792-7aefe4b07895&width=2000&userId=&cache=v2"/>

###  📄KPT 회고
*********************
* **김형우**
  * Keep: 코드의 분리와 단일 책임 원칙을 준수하기 위해 리포지토리를 나누어 사용하는 방법에 대하여 배울 수 있었습니다.
  * Problem: 재사용성을 높이기 위하여 리포지토리에서 예외까지 해결하였는데 작성하고 나서 회고 때 보니 레이어 맞지않는 역할을 주는 것 같아 개선이 필요하다고 생각했습니다.
  * Try: 따로 domain 계층을 추가해 레포지토리에서 처리하던 로직을 따로 빼내는 것으로 해결할 수 있을 것 같습니다.
* **김범진**
  * Keep: 프로젝트 설계단계에서 꼼꼼하고 정확하게 설계한 것이 좋았다.
  * Problem: 글로벌 예외처리에 대해서 공부를 해야 할 것 같다.
  * Try: 해당 수업을 복습 해야겠다.
* **서예진**
  * Keep: git 이모지, 구글 코드 컨벤션 등 협업에 있어 새로운 방법으로 협업할 기회가 되는 프로젝트였다.
  * Problem: 테스트 코드 작성에 있어 아직 부족함을 느꼈다.
  * Try: 테스트 코드 관련 복습 및 블로그를 작성해야겠다.
