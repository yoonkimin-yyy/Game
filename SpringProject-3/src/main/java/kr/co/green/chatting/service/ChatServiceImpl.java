package kr.co.green.chatting.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.green.chatmapper.ChatMapper;
import kr.co.green.chatmessage.dto.ChatMessageDTO;

@Service
public class ChatServiceImpl implements ChatService {

	private final ChatMapper chatMapper;
	
	
	public ChatServiceImpl(ChatMapper chatMapper) {
		this.chatMapper = chatMapper;
	}
	
	@Override
	public void saveChatMessage(ChatMessageDTO chatMessageDTO) {
		chatMapper.saveMessage(chatMessageDTO);
	}
	@Override
	public List<ChatMessageDTO> getMessages(String user){
		return chatMapper.getMessageByUser(user);
	}
	@Override
	public List<ChatMessageDTO> getMessagesByRoomId(int roomId){
		return chatMapper.getMessageByRoomId(roomId);
	}
	@Override
	public List<String> getAllUserIds(){
		return chatMapper.getAllUserIds();
	}
	@Override
	@Transactional
	public Integer findOrCreateRoom(String sender, String receiver) {
		Integer roomId = chatMapper.findRoom(sender, receiver);
		if(roomId != null) {
			return roomId;
		}
		// 채팅방 새로 생성
		ChatMessageDTO newChatMessgeDTO = new ChatMessageDTO();
		chatMapper.createChatRoom(newChatMessgeDTO);
		chatMapper.insertRoomUser(newChatMessgeDTO.getRoomId(), receiver);
		chatMapper.insertRoomUser(newChatMessgeDTO.getRoomId(), sender);
		
		return newChatMessgeDTO.getRoomId();
	}
}
