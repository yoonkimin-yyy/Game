package kr.co.green.Chat.dto;

import java.awt.TrayIcon.MessageType;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageDTO {

	
		
		private String roomId;
		private String sender;
		private String message;
		private LocalDateTime timestamp; // 채팅시간
		private MessageType type; // 메세지 유형
		
		public enum MessageType{
			enter, // 입장 메세지
			chat,  // 일반 메세지
			leave // 퇴장메세지
		}
		
}
