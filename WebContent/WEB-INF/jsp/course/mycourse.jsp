<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <title>我的课程</title>
  <link href="<%=request.getContextPath()%>/static/style/reset.css" rel="stylesheet" type="text/css"/>
  <link href="<%=request.getContextPath()%>/static/style/style.css" rel="stylesheet" type="text/css"/>
</head>

<body>
  <div id="warpper">
  
    <div id="header">
      <div class="logo">LOGO</div>
      <div class="menu">
        <ul>
          <li><a href="">我的课程</a></li>
          <li><a href="">全部课程</a></li>
        </ul>
      </div>
      <div class="tool">TOOL</div>
    </div>
    
    <div id="breadcrumb"></div>
    
    <div id="main">
      <div id="left"></div>
      <div id="content"></div>
    </div>
    
    <div id="footer"></div>
    
   </div>
</body>

</html>