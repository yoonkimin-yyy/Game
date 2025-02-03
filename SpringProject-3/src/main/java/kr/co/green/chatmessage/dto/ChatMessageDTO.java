package kr.co.green.chatmessage.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageDTO {
	
	private int messageId; // 메세지 번호
	private int roomId; // 채팅방 번호
	private String sender; // 보내는 사람		
	private String receiver; // 받는 사람
	private String content; // 메세지 내용
	private LocalDateTime timeStamp; // 메세지 전송 시간
	


}
