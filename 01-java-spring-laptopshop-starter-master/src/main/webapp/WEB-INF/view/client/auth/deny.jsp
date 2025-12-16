<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Access Denied</title>
    <style>
        body {
            background: #f2f2f2;
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            background: white;
            padding: 40px;
            text-align: center;
            border-radius: 10px;
            box-shadow: 0 4px 20px rgba(0,0,0,0.1);
            max-width: 400px;
        }
        h1 {
            font-size: 48px;
            color: #d9534f;
            margin-bottom: 10px;
        }
        p {
            font-size: 18px;
            color: #555;
        }
        a {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background: #0275d8;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
        a:hover {
            background: #025aa5;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>403</h1>
    <p>Bạn không có quyền truy cập vào trang này.</p>
    <a href="/">Quay về trang chủ</a>
</div>
</body>
</html>
