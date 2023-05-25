package com.itwill.spring2.repository;

import java.util.List;

import com.itwill.spring2.domain.Reply;

public interface ReplyRepository {
    
    List<Reply> selectByPostId(long postId); // read
    
    int insert(Reply reply); // create
    
    int update(Reply reply); // update
    
    int delete(long id); // delete (댓글 id)
    
    // 포스트에 달린 댓글의 개수를 리턴하는 메서드
    long selectReplyCountWithPostId(long postId);
    
    Reply selectById(long id); // 댓글 id 주면 댓글 한 개 리턴
}
