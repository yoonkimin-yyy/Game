package kr.co.green.chatmapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.green.chatmessage.dto.ChatMessageDTO;

@Mapper
public interface ChatMapper {

	void saveMessage(ChatMessageDTO chatMessageDTO);
	
	List<ChatMessageDTO> getMessageByUser( String userId);
	
	List<ChatMessageDTO> getMessageByRoomId( int roomId);
	
	List<String> getAllUserIds();
	
	 Integer findRoom(@Param("sender")String sender, @Param("receiver")String receiver); // 기존 채팅방 찾기
	
    void createChatRoom(ChatMessageDTO chatMessageDTO); // 새로운 채팅방 생성
	
	int getChatRoomId(); // 방금 생성한 채팅방 가져오기
	
	void insertRoomUser(@Param("roomId")int roomId,@Param("userId") String userId);
}
