<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
        <div class="container content">
            <div class="item-block flex-column">
                <c:choose>
                    <c:when test="${book != null}">
                        <div class="item">
                            <div class="flex-row">
                                <img class="full-view" src="data:image/jpg;base64,${book.base64Image}" alt="Картинка">
                                <table>
                                    <caption>${book.name}</caption>
                                    <tr><th>ISBN:</th><td>${book.isbn}</td></tr>
                                    <tr><th>Автор:</th><td>${book.author}</td></tr>
                                    <tr><th>Год выпуска:</th><td>${book.year}</td></tr>
                                    <tr><th>Издательство:</th><td>${book.publisher}</td></tr>
                                    <tr><th>Язык издания:</th><td>${book.language}</td></tr>
                                </table>
                            </div>
                        </div>
                        <hr>
                        <p id="description">${book.description}</p>
                    </c:when>
                    <c:otherwise>
                        <h3>Ничего не найдено &#128543;</h3>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>