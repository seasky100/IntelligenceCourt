<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试</title>
</head>
<body>
    测试页面
    <button id="xx">da</button>
    <script src="js/jquery-3.4.1.min.js"></script>
    <script>
       $(window).on('load',function(){
           $.ajax({
               type: "post",
               url: "/test",
               dataType: "json",
               success: function(result){
                   console.log(result)
               }
           });
       })
    </script>
</body>
</html>
