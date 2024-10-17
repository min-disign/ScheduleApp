package com.example.scheduleapp;

import com.example.scheduleapp.entity.Schedule;
import com.example.scheduleapp.entity.Comment;
import com.example.scheduleapp.service.ScheduleService;
import com.example.scheduleapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class App implements CommandLineRunner {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private CommentService commentService;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Schedule schedule1 = new Schedule();
        schedule1.setUsername("user1");
        schedule1.setTitle("제목 1");
        schedule1.setContent("내용 1");
        scheduleService.saveSchedule(schedule1);

        Schedule schedule2 = new Schedule();
        schedule2.setUsername("user2");
        schedule2.setTitle("제목 2");
        schedule2.setContent("내용 2");
        scheduleService.saveSchedule(schedule2);

        Comment comment1 = new Comment();
        comment1.setContent("댓글 1");
        comment1.setUsername("commenter1");
        comment1.setSchedule(schedule1);
        commentService.saveComment(comment1);

        Comment comment2 = new Comment();
        comment2.setContent("댓글 2");
        comment2.setUsername("commenter2");
        comment2.setSchedule(schedule1);
        commentService.saveComment(comment2);

        // 저장된 일정 조회
        List<Schedule> schedules = scheduleService.getAllSchedules();
        for (Schedule schedule : schedules) {
            System.out.println("일정: " + schedule.getTitle() + " - 작성자: " + schedule.getUsername());
        }

        // 저장된 댓글 조회
        List<Comment> comments = commentService.getCommentsByScheduleId(schedule1.getId());
        for (Comment comment : comments) {
            System.out.println("댓글: " + comment.getContent() + " - 작성자: " + comment.getUsername());
        }
    }
}
