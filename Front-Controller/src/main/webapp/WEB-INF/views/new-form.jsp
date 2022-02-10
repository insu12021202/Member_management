<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <meta charset="UTF-8" />
    <link rel="stylesheet" href="/src/main/webapp/WEB-INF/CSS/style.css" />
    <title>Front-Controller</title>
  </head>
  <body>
    <div class="register_container">
      <h1 class="title">반가워요!</h1>
      <!-- 상대경로 사용, [현재 URL이 속한 계층 경로 + /save] -->
      <form class="register_content" action="save" method="post">
        <div class="username_content">
          <label class="username_label" for="username">아이디</label>
          username:
          <input
            id="username"
            placeholder="아이디"
            type="text"
            name="username"
          />
        </div>
        <div class="password_contetnt">
          <label class="password_label" placeholder="비밀번호" for="password"
            >비밀번호</label
          >
          password: <input id="password" type="password" name="password" />
        </div>
        <div class="age_content">
          <label class="age_label" for="age">나이</label>
          age: <input id="age" type="text" name="age" />
        </div>
        <button type="submit">회원 가입</button>
      </form>
    </div>
  </body>
</html>
