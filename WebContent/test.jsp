<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="test" method="POST" id="loginForm">
<input type="text" name="userEmail"  value="user email" />
<input type="submit" name="登录"/>
</form>
<br/>

注册
<form action="register" method="post" id="loginForm">
<input type="text" name="number" id="courseName" value="哈哈哈" />
<input type="text" name="name"  value="哈哈哈" />
<input type="text" name="password"  value="1" />
<input type="text" name="email"  value="哈哈哈" />
<input type="text" name="school"  value="哈哈哈" />
<input type="text" name="sex"  value="哈哈哈" />
<input type="submit" name="提交"/>
</form>
<br/>

更新用户信息
<form action="user" method="post" id="loginForm">
<input type="text" name="number" id="courseName" value="哈哈哈" />
<input type="text" name="name"  value="哈哈哈" />
<input type="text" name="id"  value="14" />
<input type="text" name="email"  value="哈哈哈" />
<input type="text" name="school"  value="哈哈哈" />
<input type="text" name="sex"  value="哈哈哈" />
<input type="text" name="type"  value="info" />
<input type="submit" name="提交"/>
</form>
<br/>

更新用户密码
<form action="user" method="post" id="loginForm">
<input type="text" name="password"  value="哈哈哈" />
<input type="text" name="id"  value="14" />
<input type="text" name="type"  value="password" />
<input type="submit" name="提交"/>
</form>
<br/>

根据课程班级查询学习用户
<form action="user" method="get" id="loginForm">
<input type="text" name="courseId"  value="1" />
<input type="text" name="classId"  value="0" />


<input type="submit" name="提交"/>
</form>
<br/>

根据Id查询用户信息
<form action="test" method="post">
<input type="text" name="id"  value="1" />
<input type="submit" name="提交"/>
</form>
<br/>
根据userId查询用户学习的课程
<form action="course" method="get">
<input type="text" name="userId"  value="1" />
<input type="submit" name="提交"/>
</form>
<br/>
根据Id查询课程信息
<form action="course" method="get">
<input type="text" name="id"  value="1" />

<input type="submit" name="提交"/>
</form>
<br/>
添加课程
<form action="course" method="post" id="loginForm">
<input type="text" name="name"  value="哈哈哈" />
<input type="text" name="detail"  value="哈哈哈" />
<input type="text" name="owner"  value="1" />
<input type="text" name="picture"  value="哈哈哈" />
<input type="text" name="type"  value="add" />
<input type="submit" name="提交"/>
</form>
<br/>

根据Id删除课程信息
<form action="course" method="post">
<input type="text" name="id"  value="7" />
<input type="text" name="type"  value="delete" />
<input type="submit" name="提交"/>
</form>
<br/>

根据Id修改课程信息
<form action="course" method="post">
<input type="text" name="id"  value="7" />
<input type="text" name="name"  value="哈哈哈aaa" />
<input type="text" name="detail"  value="哈哈哈aaa" />
<input type="text" name="type"  value="update" />
<input type="submit" name="提交"/>
</form>
<br/>

模糊查询
<form action="test" method="post">
<input type="text" name="name"  value="j" />
<input type="submit" name="提交"/>
</form>
<br/>

查询任务
<form action="task" method="get">
<input type="text" name="courseId"  value="1" />
<input type="submit" name="提交"/>
</form>
<br/>
添加任务
<form action="task" method="post" id="loginForm">
<input type="text" name="name"  value="哈哈哈" />
<input type="text" name="time"  value="哈哈哈" />
<input type="text" name="detail"  value="1" />
<input type="text" name="courseId"  value="哈哈哈" />
<input type="text" name="type"  value="add" />
<input type="submit" name="提交"/>
</form>
<br/>

根据Id删除任务
<form action="task" method="post">
<input type="text" name="id"  value="7" />
<input type="text" name="type"  value="delete" />
<input type="submit" name="提交"/>
</form>
<br/>
获取课程评论
<form action="comment" method="get" id="loginForm">
<input type="text" name="courseId"  value="1" />
<input type="submit" name="提交"/>
</form>
<br/>
获取任务评论
<form action="comment" method="get" id="loginForm">
<input type="text" name="taskId"  value="1" />
<input type="submit" name="提交"/>
</form>
<br/>

添加课程评论
<form action="comment" method="post" id="loginForm">
<input type="text" name="userId"  value="1" />
<input type="text" name="detail"  value="哈哈哈" />
<input type="text" name="courseId"  value="1" />
<input type="submit" name="提交"/>
</form>

添加任务评论
<form action="comment" method="post" id="loginForm">
<input type="text" name="userId"  value="1" />
<input type="text" name="detail"  value="哈哈哈" />
<input type="text" name="taskId"  value="1" />
<input type="submit" name="提交"/>
</form>
<br/>

修改图片
<form action="test" method="get" id="loginForm">

<input type="submit" name="提交"/>
</form>
<br/>
<br/>

</body>
</html>