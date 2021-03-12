package com.example.timelineservice.timeline.controller;

import com.example.timelineservice.timeline.domain.Follow;
import com.example.timelineservice.timeline.domain.Member;
import com.example.timelineservice.timeline.service.FollowService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    /**
     * 회원 팔로우 v1
     */
    @PostMapping("/api/v1/follow")
    public createFollowResponse follow(@RequestBody @Valid createFollowRequest request) {
        Long followId = followService.follow(request.getMemberId(), request.getFollowMemberId());
        return new createFollowResponse(followId);
    }

    @Data
    static class createFollowRequest {
        Long memberId;
        Long followMemberId;
    }

    @Data
    static class createFollowResponse {
        Long followId;

        public createFollowResponse(Long followId) {
            this.followId = followId;
        }
    }

    /**
     * 팔로워 회원 v1
     */
    @GetMapping("/api/v1/follower/{followerId}")
    public Result follower(@PathVariable("followerId") Long followerId) {
        List<Follow> findFollower = followService.follower(followerId);
        List<FollowDto> collect = findFollower.stream()
                .map(m -> new FollowDto(m.getId(), m.getFollowMember()))
                .collect(Collectors.toList());
        return new Result(collect);
    }


    @Data
    @AllArgsConstructor
    class Result<T> {
        private T data;
    }

    @Data
    @AllArgsConstructor
    class FollowDto {
        private Long followId;
        private Member member;
    }


    /**
     * 팔로잉 회원 v1
     */
    @GetMapping("/api/v1/follower/{memberId}")
    public Result following(@PathVariable("memberId") Long memberId) {
        List<Follow> findFollowing = followService.following(memberId);
        List<FollowDto> collect = findFollowing.stream()
                .map(m -> new FollowDto(m.getId(), m.getMember()))
                .collect(Collectors.toList());
        return new Result(collect);
    }


    /**
     * 회원 언팔로우 v1
     */
    @DeleteMapping("/api/v1/follow/{followId}")
    public void unfollow(@PathVariable("followId") Long followId) {
        followService.unfollow(followId);
    }
}
