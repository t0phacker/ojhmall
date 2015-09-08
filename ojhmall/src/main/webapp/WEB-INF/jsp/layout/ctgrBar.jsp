<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="masthead">
	<nav class="navbar navbar-default">
		<ul class="nav nav-justified">
			<c:choose>
				<c:when test="${fn:length(upperCategoryList) > 0 }">
					<c:forEach items="${upperCategoryList}" var="row">
						<li class="dropdown-toggle"><a href="#"
							class="dropdown-toggle" data-toggle="dropdown" role="button"
							aria-expanded="false"> ${row.ctgrName } <span class="caret"></span>
						</a>
							<ul class="dropdown-menu" role="menu">
								<c:forEach items="${lowerCategoryList}" var="col">
									<c:if
										test="${col.ctgrNumber > row.ctgrNumber && col.ctgrNumber < (row.ctgrNumber + 100)}">
										<li><a
											href="/ojhmall/category/ctgrView.do?ctgrVal=${col.ctgrNumber }">${col.ctgrName }</a></li>
									</c:if>
								</c:forEach>

							</ul></li>
					</c:forEach>
				</c:when>
			</c:choose>
		</ul>
	</nav>
</div>