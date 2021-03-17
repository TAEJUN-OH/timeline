package com.example.timelineservice.timeline.controller;

import com.example.timelineservice.timeline.domain.Follow;
import com.example.timelineservice.timeline.dto.FollowDto;
import com.example.timelineservice.timeline.request.FollowRequest;
import com.example.timelineservice.timeline.response.Result;
import com.example.timelineservice.timeline.service.FollowService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
public class FollowController {

    private final FollowService followService;

    /**
     * 회원 팔로우 v1
     * @param request
     * @return followId
     */
    @PostMapping("/follow")
    @ApiOperation(value = "회원 팔로우", notes = "회원을 팔로우 하는 API ")
    public ResponseEntity<?> follow(@RequestBody @Valid FollowRequest request) {
        followService.follow(request.getMemberId(), request.getFollowMemberId());
        return ResponseEntity.noContent().build();
    }


    /**
     * 팔로워 회원조회 v1
     * @param followerId
     * @return FollowDto
     */
    @GetMapping("/follower/{followerId}")
    @ApiOperation(value = "팔로워 조회", notes = "팔로워를 조회하는 API")
    public Result follower(@PathVariable("followerId") Long followerId) {
        List<Follow> findFollower = followService.follower(followerId);
        List<FollowDto> collect = findFollower.stream()
                .map(m -> new FollowDto(m.getId(), m.getMember().getName() , m.getMember().getId() , m.getFollowMember().getId()))
                .collect(Collectors.toList());
        return new Result(collect);
    }


    /**
     * 팔로잉 회원조회 v1
     * @param memberId
     * @return FollowDto
     */
    @GetMapping("/following/{memberId}")
    @ApiOperation(value = "팔로잉 회원 조회", notes = "팔로잉 회원(내가 팔로우한 회원)을 조회하는 API")
    public Result following(@PathVariable("memberId") Long memberId) {
        List<Follow> findFollowing = followService.following(memberId);
        List<FollowDto> collect = findFollowing.stream()
                .map(m -> new FollowDto(m.getId(), m.getFollowMember().getName() , m.getMember().getId() , m.getFollowMember().getId()))
                .collect(Collectors.toList());
        return new Result(collect);
    }

    /**
     * 회원 언팔로우 v1
     * @param followId
     */
    @DeleteMapping("/follow/{followId}")
    @ApiOperation(value = "회원 언팔로우", notes = "회원을 언팔로우 하는 API")
    public ResponseEntity<?> unfollow(@PathVariable("followId") Long followId) {
        followService.unfollow(followId);
        return ResponseEntity.noContent().build();
    }
}
