
package kr.co.green.chatting.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.green.chatmessage.dto.ChatMessageDTO;
import kr.co.green.chatting.service.ChatService;

@Controller
@RequestMapping("/chat")
public class ChatController {

	private final ChatService chatService;
	private final SimpMessagingTemplate messagingTemplate;

	public ChatController(ChatService chatService, SimpMessagingTemplate messagingTemplate) {
		this.chatService = chatService;
		this.messagingTemplate = messagingTemplate;
	}

	@GetMapping("/page")
	public String chatPage(Model model) {
		List<String> allUsers = chatService.getAllUserIds();
		
		model.addAttribute("chatRooms", allUsers);
		
		return "chat/chat";
	}
	@PostMapping("/joinRoom")
	@ResponseBody
	public ResponseEntity<?> joinChatRoom(@RequestBody ChatMessageDTO request){
		Integer roomId = chatService.findOrCreateRoom(request.getSender(), request.getReceiver());
		
		Map<String, Object> response = new HashMap<>();
		if (roomId != null) {  
	        response.put("success", true);
	        response.put("roomId", roomId);
	        return ResponseEntity.ok(response);
	    } else {
	        response.put("success", false);
	        response.put("message", "채팅방 생성 실패");
	        return ResponseEntity.badRequest().body(response);
	    }
	}

	// 1️. WebSocket을 통해 메시지 전송
	@MessageMapping("/sendMessage")
	@SendTo("/chatroom/{roodId}")
	public void sendMessage(ChatMessageDTO chatMessageDTO) {
		chatService.saveChatMessage(chatMessageDTO);
		messagingTemplate.convertAndSend("/chatroom/"+chatMessageDTO.getRoomId(), chatMessageDTO);
	}

	// 3️. 특정 채팅방의 모든 메시지 조회
	@GetMapping("/history/room/{roomId}")
	@ResponseBody
	public List<ChatMessageDTO> getChatHistoryByRoom(@PathVariable("roomId") int roomId) {
		return chatService.getMessagesByRoomId(roomId);
	}

}

