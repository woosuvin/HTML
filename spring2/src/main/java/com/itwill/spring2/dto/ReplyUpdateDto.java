package com.itwill.spring2.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReplyUpdateDto {
    private String replyText; // 변수 이름 js에서 보낸 key 값과 동일하게
}
