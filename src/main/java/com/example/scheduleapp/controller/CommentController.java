package com.example.scheduleapp.controller;

import com.example.scheduleapp.entity.Comment;
import com.example.scheduleapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // 댓글 추가
    @PostMapping
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
        Comment savedComment = commentService.saveComment(comment);
        return ResponseEntity.ok(savedComment);
    }

    // 일정에 대한 댓글 조회
    @GetMapping("/schedule/{scheduleId}")
    public ResponseEntity<List<Comment>> getComments(@PathVariable Long scheduleId) {
        List<Comment> comments = commentService.getCommentsByScheduleId(scheduleId);
        return ResponseEntity.ok(comments);
    }

    // 댓글 수정
    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @RequestBody Comment updatedComment) {
        Comment comment = commentService.updateComment(id, updatedComment);
        return ResponseEntity.ok(comment);
    }

    // 댓글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
