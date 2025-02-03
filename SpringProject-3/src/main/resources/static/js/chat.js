// 채팅 관련 DOM 요소 가져오기
const chatForm = document.getElementById('chatForm');
const chatInput = document.getElementById('chatInput');
const chatMessages = document.getElementById('chatMessages');
const chatContainer = document.getElementById('chatContainer');

// 폼 제출 이벤트 처리
chatForm.addEventListener('submit', function(e) {
  e.preventDefault();
  const message = chatInput.value.trim();
  if (message !== "") {
    addMessage("나: " + message);
    chatInput.value = "";
    // 실제 채팅에서는 서버에 메시지를 전송하는 코드를 추가해야 합니다.
  }
});

// 메시지 추가 함수
function addMessage(msg) {
  const li = document.createElement('li');
  li.textContent = msg;
  chatMessages.appendChild(li);
  // 새 메시지가 추가될 때 스크롤을 맨 아래로 이동
  chatContainer.scrollTop = chatContainer.scrollHeight;
}

// 채팅방 클릭 이벤트 처리 예제
const chatroomItems = document.querySelectorAll('.chatroom-item');
chatroomItems.forEach(item => {
  item.addEventListener('click', function() {
    console.log(item.textContent + " 선택됨");
    // 여기에 채팅방 전환 및 해당 채팅방의 메시지 불러오기 등의 로직을 추가할 수 있습니다.
  });
});

// 예제: 서버로부터 받은 메시지를 시뮬레이션 (실제 구현 시 WebSocket 등 사용)
// setTimeout(() => {
//   addMessage("상대방: 안녕하세요!");
// }, 2000);