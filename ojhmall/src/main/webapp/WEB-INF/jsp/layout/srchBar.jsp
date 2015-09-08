<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<div class="container">
	<form class="navbar-form navbar-default" id="form_search" role="search"
		action="product/prdSearch.do" method="POST">
		<div class="form-group">
			<input type="text" class="form-control" id="prdName" name="prdName"
				placeholder="검색어를 입력하세요" value="${prdName}" />
		</div>
		<button type="submit" class="btn btn-default" onclick="search();">검색</button>
	</form>
</div>