package kr.co.green.chatmapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


import kr.co.green.chatmessage.dto.ChatMessageDTO;

@Mapper
public interface ChatMapper {

	void saveMessage(ChatMessageDTO chatMessageDTO);
	
	List<ChatMessageDTO> getMessageByUser( String user);
	
	List<ChatMessageDTO> getMessageByRoomId( int roomId);
	
	List<String> getChatRoomsByUserId(String userId);
}
