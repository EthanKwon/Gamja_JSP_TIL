# Gamja_JSP_TIL

사용 교재 : 프로젝트로 배우는 자바 웹 프로그래밍

---

### 19.04.17 : 자바 웹 개발환경 구축 및 JSP, 서블릿 
### 19.04.18 : 자바 회원 가입 폼 만들기 및 수정기능 
### 19.04.19 : 자바 회원 삭제기능 및 알림 메세지 출력 기능 
### 19.04.22 : JSP 내장객체 및 자바 회원가입 프로그램 servlet
### 19.04.23 : JSP 게시물 확인 기능 추가 (Servlet으로 구현)
### 19.04.24 : JSP 회원관리 및 게시물 확인 프로그램에 Paging 기능 추가
### 19.04.25 : 표편 언어와 JSTP 사용해서 JSP View 파일과 Controller파일 나누기 및 textarea 폼 변경

---

## JSP 회원 프로그램 사용 라이브러리 목록

* #### Aphche Tomcat 사용을 위한 라이브러리 :  해당 프로젝트의 buildPath를 통해 Add library로 설정할 수 있다.
* #### JSTP 사용을 위한 라이브러리 : 필요한 lib 폴더의 파일
* #### MySQL 사용을 위한 라이브러리 : 필요한 lib 폴더의 파일


---

## JSTP를 편리하게 사용하기 위한 템플릿 설정

* #### Web/JSP : taglib

```
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
```
