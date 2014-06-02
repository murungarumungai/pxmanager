<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<%--<sql:query var="categories" dataSource="jdbc/affableMySqlDS">
    SELECT *  FROM category
</sql:query>--%>

<%--<sql:query var="selectedCategory" dataSource="jdbc/affableMySqlDS">
    SELECT name FROM category WHERE id = ?
    <sql:param value="${pageContext.request.queryString}"/>
</sql:query> 

<c:set var="categoryName" value="${selectedCategory.rows[0].name}"></c:set> --%>  

<%--<sql:query var="categoryProducts" dataSource="jdbc/affableMySqlDS">
    SELECT * FROM product WHERE category_id = ?
    <sql:param value="${pageContext.request.queryString}"/>
</sql:query>--%>

<div id="indexLeftColumn">
    <c:forEach var="category" items="${categories}">
        <%-- <c:out value="${category.id}"></c:out>
        <c:out value="${'aa'}"></c:out>
        <c:out value="${pageContext.request.queryString}"></c:out> --%>
        <c:choose>
            <c:when test="${category.id == pageContext.request.queryString}">
                <div class="categoryButton" id="selectedCategory">
                    <span class="categoryText">
                        ${category.name}
                    </span>
                </div>
            </c:when>
            <c:otherwise>
                <a href="category?${category.id}" class="categoryButton">
                    <span class="categoryText">
                        ${category.name}
                    </span>
                </a>
            </c:otherwise>
        </c:choose>

    </c:forEach>
</div>
<div id="indexRightColumn">
    <p id="categoryTitle">
        <span style="background-color: #f5eabe; padding: 7px;">${selectedCategory.name}</span>
    </p>
    <table id="productTable">
        <c:forEach var="product" items="${categoryProducts}" varStatus="iter">    
            <tr>
                <td class="${((iter.index % 2) == 0) ? 'lightBlue' : 'white'}">
                    <img src="${initParam.productImagePath}${product.name}.png" alt="${product.name}">
                </td>
                <td>
                    ${product.name}
                    <br>
                    <span class="smallText">${product.description}</span>
                </td>
                <td> &euro; ${product.price} / unit </td>
                <td>
                    <form action="addToCart" method="post">
                        <input type="hidden"
                               name="productId"
                               value="${product.id}">
                        <input type="submit" value="add to cart">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>