<%@ page language="java" contentType="text/html; charset=UTF-8" 	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="/common/HeadTag.jsp" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript">  
function Open(td){
	let url = "Chat.do?room="+$(td).text();
	console.log(url);
	window.open(url,'채팅창','width=1300,height=600,location=no,status=no,scrollbars=yes');	
}


function Check(el){
	var $tr = $(el).closest('tr');
	$tr.closest('tbody').find('tr:first').before($tr);
	
	$("#checking").html("<i class='fas fa-star'></i>");
}

//웹소켓 변수 선언
var wsocket;

$(function() {	
	connect();
	
	$('#confirm').click(function() {	
			
		send("rname", $("#roomname").val());
		send("making", $("#user").val());
		
        var room = "";
        room += "<tr>";
        room += "<td id='myid'>" + $("#user").val() + "</td>";
        room += "<td style='cursor:pointer;' onclick='Open(this)'>" + $("#roomname").val() + "</td>";
        room += "<td onclick='Check(this);' style='cursor:pointer;' id='checking'><i class='far fa-star'></i></td>";
        room += "</tr>";
		$('#tbody').append(room);
	});
	
});

function connect() { //입장 버튼 클릭시 작동 함수(웹소켓 생성)
	wsocket = new WebSocket("ws://192.168.6.19:8090/EmpManager_Chat/Chat-ws.do");

	//해당 함수 정의
	wsocket.onopen = onOpen;
	wsocket.onmessage = onMessage;
	wsocket.onclose = onClose;
}
function onOpen(){

}

function send(roomData, message) {
	let data = {rname : $("#roomname").val(),
			master : $("#user").val(),
			roomData : roomData};	
	wsocket.send(JSON.stringify(data));
}

function onMessage(evt) { // "message" 이름의 MessageEvent 이벤트가 발생하면 처리할 핸들러
	var data = JSON.parse(evt.data);
	console.log(data.rname);
	console.log(data.master);
	appendMessage(data);
}

	function appendMessage(data) {
		console.log("나 들어오니" + data);
	if(data.auth == "memberInfo"){
			$("#tbody").append(
					"<tr><td id='myid'>" + data.master + "</td><td style='cursor:pointer;' onclick='Open(this)'>" 
			        + data.rname + "</td><td onclick='Check(this);' style='cursor:pointer;' id='checking'><i class='far fa-star'></i></td></tr>");
		} 
	
	}
function onClose(){

	
}	
/* 	function MemberList(members){
		console.log(members);
		$("#memberbox").empty();
		$.each(members, function(index, element){
			if(element == $("#user").val())
				$("#memberbox").append("<span style='margin-left: 140px; font-size:18px;'>"+element+"<span><hr>");
			else
				$("#memberbox").append("<span style='margin-left: 140px; font-size:18px;'>"+element+"<span><hr>");
		})
	} */
</script>
<style>
.btn_options {
  color:inherit;
  font-family:inherit;
  font-size: inherit;
  background: white;
  padding: 0.3rem 3.4rem;
  border: 3px solid black;
  margin-right: 2.6rem;
  box-shadow: 0 0 0 black;
  transition: all 0.2s;
}

.btn_options:last-child {
  margin: 0;
}

.btn_options:hover {
  box-shadow: 0.4rem 0.4rem 0 black;
  transform: translate(-0.4rem, -0.4rem);
}

.btn_options:active {
  box-shadow: 0 0 0 black;
  transform: translate(0, 0);
}

.options {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
</style>
<body id="page-top">
<c:set var="userid" value="${sessionScope.userid}"/>
	<!-- Top -->
	<c:import url="/common/Top.jsp" />
	<div id="wrapper">
		<!-- Left Menu -->
		<c:import url="/common/Left.jsp" />

		<div id="content-wrapper">

			<!-- !! Content !! -->
			<div class="container-fluid">
			<input type="hidden" id="user" value="${userid}">
				<div class="card mb-3">
					<div class="card-header">
						<i class="fas fa-comments"></i> 실시간 채팅
						<button type="button" data-toggle="modal" data-target="#myModal" style="float:right;">방 만들기</button>
					</div>
					<!-- 모달 창 -->
					<div class="modal fade" id="myModal" role="dialog">
					    <div class="modal-dialog">
					    
					      <!-- Modal content-->
					      <div class="modal-content"  style="text-align: center;">
					        <div class="modal-header">
					        </div>
					        <div class="modal-body">
					        <h4>채팅방 개설</h4>
					        <hr>
					          <br>
					          <input type="text" id="roomname" placeholder="채팅방 이름" style="width: 400px; height: 50px; text-align: center;">
					          <br>
					        </div>
					        <div class="modal-footer">
					          <button type="button" class="btn_options" id="confirm" data-dismiss="modal">완료</button>
					          <button type="button" class="btn_options" data-dismiss="modal">취소</button>
					        </div>
					      </div>
					      
					    </div>
					  </div>
  					
					<div class="card-body">
						<div class="row">
						<div class="col-md-12">
						<div id="makeRoom" style="text-align:center;">
						  <table class="table table-bordered">
					    <thead>
					      <tr>
					        <th>방장</th>
					        <th style="width:70%;">채팅방 이름</th>
					        <th>즐겨찾기</th>
					      </tr>
					    </thead>
					    <tbody id="tbody">
					      <tr>
					        <td>정은</td>
					        <td>다정이 덕질방</td>
					        <td onclick="Check(this);" style="cursor:pointer;" id="checking"><i class="far fa-star"></i></td>
					      </tr>
					    </tbody>
					  </table>
						</div>
						</div>
						</div>
					</div>
				</div>
			</div>

			<!-- Bottom -->
			<c:import url="/common/Bottom.jsp" />
		</div>
	</div>
</body>

</html>