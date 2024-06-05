package com.itwill.spring1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //Getter,Setter,ToString,EqualsAndHashCode, RequiredArgsConstructor
@NoArgsConstructor //기본생성자  
@AllArgsConstructor //모든필드를 초기화할수 있는 아규먼트를 갖는 생성자
@Builder 
public class UserDto {
	private String username;
	private Integer age;	  //int타입은 비어있으면Integer.parseInt()할때 오류발생.
	
}
