package com.itwill.spring3.repository.reply;

import com.itwill.spring3.dto.reply.ReplyUpdateDto;
import com.itwill.spring3.repository.BaseTimeEntity;
import com.itwill.spring3.repository.post.Post;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
@ToString(exclude = {"post"})
@Entity
@Table(name = "REPLIES")
@SequenceGenerator(name = "REPLIES_SEQ_GEN", sequenceName = "REPLIES_SEQ", allocationSize = 1)
public class Reply extends BaseTimeEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REPLIES_SEQ_GEN")
    private long id; // Primary Key
    
    @ManyToOne(fetch = FetchType.LAZY) 
    // 한 개의 포스트에 여러개의 댓글이 달려 있을 수 있음.
    // EAGER(기본값): 즉시 로딩, LAZY: 지연 로딩.
    private Post post; // Foreign key, 관계를 맺고 있는 entity
    
    @Column(nullable = false)
    private String replyText; // 댓글 내용
    
    @Column(nullable = false)
    private String writer; // 댓글 작성자
    
    public Reply update(ReplyUpdateDto dto) {
        this.id = dto.getId();
        this.replyText = dto.getReplyText();
        return this;
    }
}
