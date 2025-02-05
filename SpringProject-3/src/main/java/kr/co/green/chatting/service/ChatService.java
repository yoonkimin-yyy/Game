package kr.co.green.chatting.service;

import java.util.List;
import java.util.Optional;

import kr.co.green.chatmessage.dto.ChatMessageDTO;


public interface ChatService {

	public void saveChatMessage(ChatMessageDTO chatMessageDTO);
	List<ChatMessageDTO> getMessages(String user);
	List<ChatMessageDTO> getMessagesByRoomId(int roomId);
	
	List<String> getAllUserIds();
	Integer findOrCreateRoom(String sender, String receiver);
}
