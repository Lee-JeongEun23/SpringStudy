<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<c:import url="/common/HeadTag.jsp" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
<link
	href="https://fonts.googleapis.com/css?family=Gothic+A1|Hi+Melody|Jua|Nanum+Pen+Script&display=swap"
	rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Nanum+Brush+Script&display=swap" rel="stylesheet">
<script type="text/javascript">
	//웹소켓 변수 선언
	var wsocket;

	$(function() {
		connect();
		
		$("#exitBtn").hide();
		
		$("#inputBox").hide();
		
		$('#message').keypress(function(event) {
			var keycode = (event.keyCode ? event.keyCode : event.which);
			if (keycode == '13') {
				send("message",$('#message').val());
			}
			event.stopPropagation();
		});

		$('#sendBtn').click(function() {
			send("message",$('#message').val());
		});
		$('#exitBtn').click(function() {
			disconnect();
		});

		$("#font").change(function() {
			$("#chatMessageArea").attr("style", "font-family:"+$("#font option:selected").val()+"\"");
		});

		  $("#smile").click(function(){
	          var input = $( "#message" );
	          input.val( input.val() + "😃"  );
	       });   

	       $("#heart").click(function(){
	          var input = $( "#message" );
	          input.val( input.val() + "😍"  );
	       });   

	       $("#cry").click(function(){
		          var input = $( "#message" );
		          input.val( input.val() + "😭"  );
		       });  

	       $("#angry").click(function(){
		          var input = $( "#message" );
		          input.val( input.val() + "😠"  );
		       });  

	       $("#scary").click(function(){
		          var input = $( "#message" );
		          input.val( input.val() + "😱"  );
		       });  

	       $("#smile2").click(function(){
		          var input = $( "#message" );
		          input.val( input.val() + "😁"  );
		       });

	       $("#wink").click(function(){
		          var input = $( "#message" );
		          input.val( input.val() + "😝"  );
		       });

	       $("#yummy").click(function(){
		          var input = $( "#message" );
		          input.val( input.val() + "😋"  );
		       });

	       $("#sad").click(function(){
		          var input = $( "#message" );
		          input.val( input.val() + "😢"  );
		       });

	       $("#annoy").click(function(){
		          var input = $( "#message" );
		          input.val( input.val() + "😤"  );
		       });

	       $("#sick").click(function(){
		          var input = $( "#message" );
		          input.val( input.val() + "😷"  );
		       });

	       $("#wish").click(function(){
		          var input = $( "#message" );
		          input.val( input.val() + "🙏"  );
		       });
	       
	       $("#emoBtn").click(function(){
	          $("#emojiBox").toggle();
	       });

	})
	
	function connect() { //입장 버튼 클릭시 작동 함수(웹소켓 생성)
		wsocket = new WebSocket("ws://192.168.6.19:8090/EmpManager_Chat/Chat-ws.do");

		//해당 함수 정의
		wsocket.onopen = onOpen;
		wsocket.onmessage = onMessage;
		wsocket.onclose = onClose;
	}
	
	function disconnect() { //나가기 버튼 클릭 시
		wsocket.close();
	}
	
	function onOpen(evt) {
		$("#enterBtn").hide();
		/* $("#nickname").attr("disabled", true); */
		$("#exitBtn").show();
		$("#inputBox").show();
		send("join",$("#user").val());
		
		
	}

	function onMessage(evt) { // "message" 이름의 MessageEvent 이벤트가 발생하면 처리할 핸들러
		var data = JSON.parse(evt.data);
		console.log()
		console.log(data.sender);
		console.log(data.message);
		appendMessage(data);
	}

	function onClose(evt) {
		$("#enterBtn").show();
		/* $("#nickname").val(""); */
		$("#chatMessageArea").empty();
		/* $("#user").attr("disabled", false); */
		$("#exitBtn").hide();
		$("#inputBox").hide();
	}

	function send(cmd, message) {
		let data = { message : message
						, sender : $("#user").val()
						, cmd : cmd
						, rname : $("#thename").text()};
		wsocket.send(JSON.stringify(data));
		$("#message").val("");
	}

/* 	function memberList(memberArray){
	console.log(memberArray);
		} */
	
		function appendMessage(data) {
			console.log(data);
			if (data.auth == "my") {
				$("#chatMessageArea").append(
						"<span id='msgmyname'><b><i class='far fa-grin-alt'></i> "
								+ data.sender + "&nbsp;&nbsp;&nbsp;</b></span>" + "<br>"
								+ "<span id='msgmy'><b>&nbsp;&nbsp;" + data.message
								+ "&nbsp;&nbsp;&nbsp;</b></span>" + "<br>");

			} else if(data.auth == "memberInfo"){
				$("#chatMessageArea").append(
						"<span id='msgsystem'><b>"
								 + data.message
								+ "&nbsp;&nbsp;&nbsp;</b></span>" + "<br>");
				MemberList(data.member);
			} else {
				$("#chatMessageArea").append(
						"<span id='msgothername'><b>&nbsp;&nbsp;&nbsp;<i class='fas fa-grin-alt'></i> "
								+ data.sender + "</b></span>" + "<br>"
								+ "<span id='msgother'><b>&nbsp;&nbsp;&nbsp;"
								+ data.message + "&nbsp;&nbsp;</b></span>" + "<br>");
			}

			var chatAreaHeight = $("#chatArea").height();
			var maxScroll = $("#chatMessageArea").height() - chatAreaHeight;
			$("#chatArea").scrollTop(maxScroll);
		}
		
		
		function MemberList(members){
			console.log(members);
			$("#memberbox").empty();
			$.each(members, function(index, element){
				if(element == $("#user").val())
					$("#memberbox").append("<span style='margin-left: 140px; font-size:18px;'>"+element+"<span><hr>");
				else
					$("#memberbox").append("<span style='margin-left: 140px; font-size:18px;'>"+element+"<span><hr>");
			})
		}
	
</script>
</head>
<style>
#chatArea {
	height: 320px;
	overflow-y: auto;
	border: 2px solid black;
}

#memberArea {
	height: 320px;
	border: 2px solid black;
}

#msgmyname {
	float: right;
	clear: both;
}

#msgmy {
	color: #537979;	
	position: relative;
    display: inline-block;
    float: right;
    clear: both;
	font-size: 18px;
	background: linear-gradient(
        to right,
        rgba(205, 220, 57, 0.3) 0%,
        rgba(205, 220, 57, 0.4) 60%,
        rgba(205, 220, 57, 0.4) 60%,
        rgba(205, 220, 57, 0.6) 85%,
        rgba(205, 220, 57, 0.8) 100%);
  }
#msgother {
	color: #e67300;
	position: relative;
    display: inline-block;
    float: left;
    clear: both;
	font-size: 18px;
	background: linear-gradient(
            to left,
            rgba(253, 200, 48, 0.3) 0%,
            rgba(253, 200, 48, 0.4) 60%,
            rgba(253, 200, 48, 0.4) 60%,
            rgba(253, 200, 48, 0.6) 85%,
            rgba(253, 200, 48, 0.8) 100%);
}

#msgothername {
	float: left;
	clear: both;
}

#msgsystem {
	color: black;
	margin-left: 200px;
	cleat: both;
	
}
</style>
<body id="page-top">
<c:set var="userid" value="${sessionScope.userid}"/>
<c:set var="chatname" value="${param.room}"/>
<input type="hidden" id="user" value="${userid}">
			<!-- !! Content !! -->
			<br><br><br>
			<div class="container">
				<div class="card mb-3">
					<div class="card-header">
						<i class="fas fa-comments"></i> <span id="thename">${chatname}</span> 
						<select id="font" style="height: 30px; width: 250px; float:right;">
								<option hidden>채팅창 글꼴 설정</option>
								<option value="'Nanum Brush Script', cursive;"
									style="font-family: 'Nanum Brush Script', cursive;">나눔붓체</option>
								<option value="'Jua', sans-serif;" style="font-family: 'Jua', sans-serif;">주아체</option>
								<option value="'Hi Melody', cursive;"
									style="font-family: 'Hi Melody', cursive;">하이멜로디체</option>
								<option value="'Gothic A1', sans-serif;"
									style="font-family: 'Gothic A1', sans-serif;">고딕체</option>
							</select>

					</div>
					<div class="card-body">
						<div class="row">
							<div class="col-md-7">
								<div class="table-responsive">
<%-- 									이름 : <input type="text" id="nickname" style="width: 250px;" value="${userid}">
									<input type="button" id="enterBtn" value="입장"> --%> <input
										type="button" id="exitBtn" value="나가기"> <br> <br>
									<h5>채팅방</h5>
									<div id="chatArea">
										<div id="chatMessageArea"></div>
									</div>
									<br />
									<div id="inputBox">
										<input type="button" id="emoBtn" value="이모티콘">
										<input type="text" id="message" style="width: 400px;">
										<input type="button" id="sendBtn" value="전송">
					                      
					                      
					                      <br>
					                      <div id="emojiBox" style="border: 1px solid gray; display:none; margin-left: 84px; width: 400px; text-align: center">
					                      <a type="button" id="smile">&#x1F603;</a>&nbsp; <a type="button" id="smile2">&#x1F601;</a>&nbsp; <a type="button" id="heart">&#x1F60D;</a> &nbsp; 
					                      <a type="button" id="yummy">&#x1F60B;</a> &nbsp; <a type="button" id="sad">&#x1F622;</a> &nbsp;
					                      <a type="button" id="cry">&#x1F62D;</a> &nbsp; <a type="button" id="annoy">&#x1F624;</a> &nbsp; <a type="button" id="angry">&#x1F620;</a> &nbsp; 
					                      <a type="button" id="scary">&#x1F631;</a> &nbsp; <a type="button" id="sick">&#x1F637;</a> &nbsp; <a type="button" id="wish">&#x1F64F;</a>
				                            </div>
									</div>
								</div>
							</div>
							<!-- <div class="col-md-1"></div> -->
							<div class="col-md-4">
								<div class="changebox">
<!-- 									<select id="font" style="height: 30px; width: 250px;">
										<option hidden>채팅창 글꼴 설정</option>
										<option value="'Nanum Brush Script', cursive;"
											style="font-family: 'Nanum Brush Script', cursive;">나눔붓체</option>
										<option value="'Jua', sans-serif;" style="font-family: 'Jua', sans-serif;">주아체</option>
										<option value="'Hi Melody', cursive;"
											style="font-family: 'Hi Melody', cursive;">하이멜로디체</option>
										<option value="'Gothic A1', sans-serif;"
											style="font-family: 'Gothic A1', sans-serif;">고딕체</option>
									</select> -->
								<br>
								<br> 
								</div>
								<h5>참여 멤버</h5>	
								
								
								<div id="memberbox" style="border:2px solid black; width: 100%; height: 320px;">
								
								
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
</body>

</html>