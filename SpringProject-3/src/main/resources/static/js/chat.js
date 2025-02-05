// ✅ 채팅 관련 DOM 요소 가져오기
const chatForm = document.getElementById('chatForm');
const chatInput = document.getElementById('chatInput');
const chatMessages = document.getElementById('chatMessages');
const chatContainer = document.getElementById('chatContainer');

// ✅ 현재 로그인한 사용자 ID 가져오기 (Thymeleaf에서 설정한 값 사용)
let currentUserId = document.getElementById("currentUserId").value; 
let roomId = 0;
let receiver = null;

/**
 * ✅ 메시지 추가 함수
 */
let count = 0;
function addMessage(msg,historyFlag) {
    const li = document.createElement('li');
	const isMyMessage = msg.sender === currentUserId; // 내가 보낸 메세지인지 확인
	li.classList.add(isMyMessage ? "my-message" : "other-message");
	
	if(isMyMessage) {
		if(historyFlag||count === 0) {
			count++;
			li.innerHTML = `<strong>${msg.sender}:</strong> ${msg.content}`;
		} else {
			count = 0;
			return;
		}
	} else {
		li.innerHTML = `<strong>${msg.sender}:</strong> ${msg.content}`;
	}
    chatMessages.appendChild(li);
    chatContainer.scrollTop = chatContainer.scrollHeight; // 스크롤 아래로 이동
}

/**
 * ✅ 폼 제출 이벤트 처리 (메시지 전송)
 */
chatForm.addEventListener('submit', function (e) {
    e.preventDefault();

    let messageContent = chatInput.value.trim();
    if (!messageContent) return;
    
    if (roomId ===0) {
        console.error("채팅 상대방이 선택되지 않았습니다.");
        return;
    }

    let chatMessage = {
        sender: currentUserId, 
        receiver: receiver, 
        content: messageContent,
		roomId : roomId
    };

    // ✅ WebSocket을 통해 메시지 전송
    sendMessage(chatMessage);

    // ✅ 내가 보낸 메시지를 화면에 추가
    //addMessage(`<b>나:</b> ${messageContent}`);
	addMessage(chatMessage);

    // ✅ 입력 필드 비우기
    chatInput.value = "";
});

/**
 * ✅ 채팅방 클릭 이벤트 처리 (채팅방 목록에서 선택 시)
 */
document.querySelectorAll('.chatroom-item').forEach(item => {
    item.addEventListener('click', function () {
        receiver = item.getAttribute('data-user-id');
        console.log(`현재 로그인한 유저 (sender): ${currentUserId}`);
        console.log(`채팅 상대방 (receiver): ${receiver}`);
		
		// 1. ajax 추가, success일 때 joinRoom을 호출
		fetch('/chat/joinRoom',{
			method:'POST',
			headers: {
				"Content-Type": `application/json`
			},
			body:JSON.stringify({
				sender:currentUserId,
				receiver :receiver 
			})
		})
		.then(response => response.json())
		.then(data => {
			if(data.success){
				roomId = data.roomId;
				joinRoom(data.roomId,currentUserId,receiver);
			}else{
				console.log("채팅방 입장실패",data.message);
			}
		})
		.catch(error => console.log("error발생", error));
    });
});