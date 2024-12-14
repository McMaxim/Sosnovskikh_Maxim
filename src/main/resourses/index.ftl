<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Главная страница</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/yegor256/tacit@gh-pages/tacit-css-1.6.0.min.css"/>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            color: #333;
        }

        h1 {
            text-align: center;
            color: #333;
            margin-top: 20px;
            font-size: 2em;
        }

        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #007BFF; /* Синий фон для заголовков */
            color: white;
        }

        tr:hover {
            background-color: #f5f5f5;
        }

        a {
            text-decoration: none;
            color: #007BFF; /* Синие ссылки */
        }

        a:hover {
            text-decoration: underline;
        }

        .container {
            padding: 20px;
        }

    </style>
</head>

<body>

    <div class="container">
        <h1>Посты</h1>
        <table>
            <tr>
                <th>Название</th>
                <th>Теги</th>
                <th>Количество комментариев</th>
            </tr>
            <#list posts as post>
            <tr>
                <td><a href="/article/${post.id}">${post.header}</a></td>
                <td>${post.tags}</td>
                <td>${post.comments_count}</td>
            </tr>
            </#list>
        </table>
    </div>

</body>
</html>
