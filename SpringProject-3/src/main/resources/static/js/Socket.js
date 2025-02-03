let stompClient = null;
let currentRoomId = null;

function connectWebSocket() {
    let socket = new SockJS('/chat-socket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function () {
        console.log("WebSocket 연결 성공");
    });
}

function joinRoom(roomId) {
    if (currentRoomId) {
        stompClient.unsubscribe(currentRoomId);
    }
    currentRoomId = "/chatroom/" + roomId;
    
    $.get("/chat/history/room/" + roomId, function(messages) {
        $("#chatMessages").empty();
        messages.forEach(msg => {
            $("#chatMessages").append(`<li><b>${msg.sender}:</b> ${msg.content}</li>`);
        });
    });

    stompClient.subscribe(currentRoomId, function (message) {
        let msg = JSON.parse(message.body);
        $("#chatMessages").append(`<li><b>${msg.sender}:</b> ${msg.content}</li>`);
    });
}

	$("#chatForm").submit(function (e) {
   			 e.preventDefault();
    		let messageContent = $("#chatInput").val();

    if (messageContent.trim() && currentRoomId) {
        let chatMessage = { roomId: currentRoomId.replace("/chatroom/", ""), sender: "사용자", content: messageContent };
        
        $.ajax({
            type: "POST",
            url: "/chat/sendMessage",
            contentType: "application/json",
            data: JSON.stringify(chatMessage),
            success: function(response) {
                $("#chatMessages").append(`<li><b>${response.sender}:</b> ${response.content}</li>`);
                $("#chatInput").val("");
            }
        });
    }
});

connectWebSocket();