let stompClient = null;

/**
 * ✅ WebSocket 연결 및 특정 채팅방 참여
 */
function joinRoom(roomId,currentUserId,receiver) {
    console.log(`채팅 상대방: ${roomId}`);

    if (stompClient && stompClient.connected) {
        console.log("이미 WebSocket이 연결되어 있음");
        return;
    }

    let socket = new SockJS('/chat-socket');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function () {
        console.log("✅ WebSocket 연결 성공");

        // ✅ 특정 상대방과의 채팅 구독
        stompClient.subscribe(`/chatroom/${roomId}`, function (message) {
            let msg = JSON.parse(message.body);
			addMessage(msg);
        });

        // ✅ 이전 채팅 내역 불러오기
         loadChatHistory(roomId,currentUserId,receiver);

    }, function (error) {
        console.error("❌ WebSocket 연결 실패: ", error);
    });
}

/**
 * ✅ 특정 사용자와의 채팅 내역 불러오기 (AJAX 요청)
 */
function loadChatHistory(roomId,userId,receiver) {
    $.get(`/chat/history/room/${roomId}`, function (messages) {
        chatMessages.innerHTML = ""; // 기존 메시지 초기화
        messages.forEach(msg => {
            addMessage(msg,true);
        });
    }).fail(function () {
        console.error("채팅 내역을 불러오는 데 실패했습니다.");
    });
}

/**
 * ✅ 메시지 전송 (WebSocket)
 */
function sendMessage(chatMessage) {
    if (!stompClient || !stompClient.connected) {
        console.error("WebSocket이 연결되지 않았습니다.");
        return;
    }
    stompClient.send("/chat/app/sendMessage", {}, JSON.stringify(chatMessage));
	
}