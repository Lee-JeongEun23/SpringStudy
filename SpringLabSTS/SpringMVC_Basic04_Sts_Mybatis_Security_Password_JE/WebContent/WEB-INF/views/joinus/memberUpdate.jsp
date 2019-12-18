<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="member" value="${member }" />

<div id="content">
	<form action="" method="post">
		<h2>회원정보 수정</h2>
		<h3 class="hidden">방문페이지 로그</h3>
		<p id="breadscrumb" class="block_hlist clear">
			<img alt="Step1 개인정보 등록" src="images/step2.png" />
		</p>
		<h3 class="hidden">회원수정 폼</h3>
		<div id="join-form" class="join-form margin-large">
			<dl class="join-form-row">
				<dt class="join-form-title">아이디</dt>
				<dd class="join-form-data">
					<input type="text" name="userid" value="${member.userid}" readonly/> <input id="btnCheckUid"
						class="button" type="button" value="중복확인" />
				</dd>
			</dl>
			<dl class="join-form-row">
				<dt class="join-form-title">비밀번호</dt>
				<dd class="join-form-data">
					<input type="password" name="pwd" />
				</dd>
			</dl>
			<dl class="join-form-row">
				<dt class="join-form-title">비밀번호 확인</dt>
				<dd class="join-form-data">
					<input type="password" name="pwd2" />
				</dd>
			</dl>
			<dl class="join-form-row">
				<dt class="join-form-title">이름</dt>
				<dd class="join-form-data">
					<input type="text" name="name" value="${member.name}" />
				</dd>
			</dl>
			<dl class="join-form-row">
				<dt class="join-form-title">성별</dt>
				<dd class="join-form-data">
					<select name="gender">
					<c:choose> 
						<c:when test="${member.gender=='남성'}">
							<option>남성</option>
						</c:when>
						<c:otherwise>
							<option>여성</option>
						</c:otherwise>
					</c:choose>
					</select>
				</dd>
			</dl>
			<dl class="join-form-row">
				<dt class="join-form-title">핸드폰 번호</dt>
				<dd class="join-form-data">
					<input type="text" name="cphone" value="${member.cphone}"/><span>[대시(-)를 포함할 것: 예)
						010-3456-2934]</span>
				</dd>
			</dl>
			<dl class="join-form-row">
				<dt class="join-form-title">이메일</dt>
				<dd class="join-form-data">
					<input type="text" name="email" value="${member.email}" />
				</dd>
			</dl>
		</div>
		<div id="buttonLine">
			<input class="btn-okay button" type="submit" value="수정"/>
		</div>
	</form>

</div>
