package kr.co.green.chatting.controller;

import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
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
	
	public ChatController(ChatService chatService,SimpMessagingTemplate messagingTemplate) {
		this.chatService = chatService;
		this.messagingTemplate = messagingTemplate;
	}
	@GetMapping("/page")
	public String chatPage() {
	    return "chat";  
	}
	
	// 1️. WebSocket을 통해 메시지 전송
	@MessageMapping("/sendMessage")
    public void sendMessage(ChatMessageDTO chatMessageDTO) {
        chatService.saveChatMessage(chatMessageDTO);
        messagingTemplate.convertAndSend("/chatroom/" + chatMessageDTO.getRoomId(), chatMessageDTO);
    }
	
	// 2️. 특정 사용자의 채팅 내역 조회
	@GetMapping("/history/user/{user}")
	@ResponseBody
	public List<ChatMessageDTO> getChatHistoryByUser(@PathVariable String user) {
        return chatService.getMessages(user);
    }
	
	// 3️. 특정 채팅방의 모든 메시지 조회
    @GetMapping("/history/room/{roomId}")
    @ResponseBody
    public List<ChatMessageDTO> getChatHistoryByRoom(@PathVariable int roomId) {
        return chatService.getMessagesByRoomId(roomId);
    }
    @PostMapping("/sendMessage")
    public ChatMessageDTO sendMessageAjax(@RequestBody ChatMessageDTO chatMessageDTO) {
        chatService.saveChatMessage(chatMessageDTO);
        return chatMessageDTO;
    }
}
