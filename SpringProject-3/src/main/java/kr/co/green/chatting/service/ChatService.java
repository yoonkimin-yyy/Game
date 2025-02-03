package kr.co.green.chatting.service;

import java.util.List;

import kr.co.green.chatmessage.dto.ChatMessageDTO;

public interface ChatService {

	public void saveChatMessage(ChatMessageDTO chatMessageDTO);
	List<ChatMessageDTO> getMessages(String user);
	List<ChatMessageDTO> getMessagesByRoomId(int roomId);
	List<String> getChatRoomsByUserId(String userId);
}
