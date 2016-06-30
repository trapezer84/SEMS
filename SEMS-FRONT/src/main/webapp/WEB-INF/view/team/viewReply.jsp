<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<table border="2px">
	<c:forEach items="${reReplies}" var="reply">
		<tr>
			<td>
				작성자 : ${ reply.memberId }
			</td>
			<td>
				내용 : ${ reply.descript }
			</td>
		</tr>
	</c:forEach> 
	</table>
