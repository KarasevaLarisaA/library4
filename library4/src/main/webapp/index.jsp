<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

        <div class="container content flex">
            <c:choose>
                <c:when test="${pageContext.request.getParameter('id') == null}">
                    <c:forEach items="${allBooks}" var="book">
                        <div class="item-short" onclick="location.href='<c:url value="/book?id=${book.id}"/>'">
                            <img src="data:image/jpg;base64,${book.base64Image}" class="preview" alt="Картинка">
                            <div class="short-desc">${fn:substring(book.name, 0, 18)}...</div>
                        </div>
                    </c:forEach>
                </c:when>
                <c:when test="${genreBooks != null and !genreBooks.isEmpty()}">
                    <c:forEach items="${genreBooks}" var="book">
                        <div class="item-short" onclick="location.href='<c:url value="/book?id=${book.id}"/>'">
                            <img src="data:image/jpg;base64,${book.base64Image}" class="preview" alt="Картинка">
                            <div class="short-desc">${fn:substring(book.name, 0, 18)}...</div>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <h3>Ничего не найдено &#128543;</h3>
                </c:otherwise>
            </c:choose>
        </div>