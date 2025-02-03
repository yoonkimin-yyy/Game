package kr.co.green.chatting.service;

import java.util.List;
import java.util.stream.Collectors;

import kr.co.green.chatmapper.ChatMapper;
import kr.co.green.chatmessage.dto.ChatMessageDTO;

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
    public List<String> getChatRoomsByUserId(String userId) {
        List<ChatMessageDTO> messages = chatMapper.getMessageByUser(userId);
        
        // ✅ 사용자가 참여한 채팅방(roomId) 중복 제거 후 반환
        return messages.stream()
                .map(ChatMessageDTO::getSender)
                .distinct()
                .collect(Collectors.toList());
    }
}
