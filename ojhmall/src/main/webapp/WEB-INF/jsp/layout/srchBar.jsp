<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<div class="container">
	<form class="navbar-form navbar-default" id="form_search" role="search"
		action="product/prdSearch.do" method="POST">
		<div class="form-group">
			<input type="text" class="form-control" id="prdName" name="prdName"
				placeholder="�˻�� �Է��ϼ���" value="${prdName}" />
		</div>
		<button type="submit" class="btn btn-default" onclick="search();">�˻�</button>
	</form>
</div>