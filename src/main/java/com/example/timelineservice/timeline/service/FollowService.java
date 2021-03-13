package com.example.timelineservice.timeline.service;

import com.example.timelineservice.timeline.domain.Follow;
import com.example.timelineservice.timeline.domain.Member;
import com.example.timelineservice.timeline.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FollowService {

    private final MemberService memberService;
    private final FollowRepository followRepository;


    /**
     * 팔로우
     */
    @Transactional
    public Long follow(Long memberId , Long followMemberId) {
        Member member = memberService.findOne(memberId);
        Member followMember = memberService.findOne(followMemberId);
        validateFollow(memberId, followMemberId); //팔로우 중복 체크
        Follow follow = Follow.createFollow(member, followMember);
        followRepository.save(follow);
        return follow.getId();
    }

    public void validateFollow(Long memberId , Long followMemberId) {
        Follow follow = followRepository.findByFollow(memberId, followMemberId);
        if (follow != null) {
            throw new IllegalStateException("이미 팔로우 한 회원입니다.");
        }
    }

    /**
     * 팔로워
     */
    @Transactional
    public List<Follow> follower(Long followMemberId) {
        return followRepository.findByFollower(followMemberId);
    }


    /**
     * 팔로잉
     */
    @Transactional
    public List<Follow> following(Long memberId) {
        return followRepository.findByFollowing(memberId);
    }


    /**
     * 언팔로우
     */
    @Transactional
    public void unfollow(Long follow_id) {
        followRepository.deleteById(follow_id);
    }
}
