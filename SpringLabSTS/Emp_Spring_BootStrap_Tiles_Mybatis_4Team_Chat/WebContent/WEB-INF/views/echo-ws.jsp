<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>에코</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">
   $(document).ready(function() {
      $('#sendBtn').click(function() { sendMessage(); });
   });
   
   var wsocket;
   function sendMessage() {
      wsocket = new WebSocket("ws://localhost:8090/Emp_Spring_BootStrap_Tiles_Mybatis_4Team/WEB-INF/views/echo-ws.do");
      wsocket.onmessage = onMessage;
      wsocket.onclose = onClose;
      wsocket.onopen = function() {
         wsocket.send( $("#message").val() );
      };
   }
   function onMessage(evt) {
      var data = evt.data;
      alert("서버에서 데이터 받음: " + data);
      wsocket.close();
   }
   function onClose(evt) {
      alert("연결 끊김");
   }
</script>
</head>
<body>
    <input type="text" id="message">
    <input type="button" id="sendBtn" value="전송">
</body>
</html>