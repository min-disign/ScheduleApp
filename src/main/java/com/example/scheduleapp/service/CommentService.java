package com.example.scheduleapp.service;

import com.example.scheduleapp.entity.Comment;
import com.example.scheduleapp.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    // 댓글 저장
    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    // 댓글 조회
    public List<Comment> getCommentsByScheduleId(Long scheduleId) {
        return commentRepository.findByScheduleId(scheduleId);
    }

    // 댓글 수정
    public Comment updateComment(Long commentId, Comment updatedComment) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("Comment not found"));
        comment.setContent(updatedComment.getContent());
        comment.setUsername(updatedComment.getUsername());
        return commentRepository.save(comment);
    }

    // 댓글 삭제
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
