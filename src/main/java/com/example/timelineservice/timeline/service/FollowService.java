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
     * @param memberId
     * @param followMemberId
     * @return followId
     */
    @Transactional
    public void follow(Long memberId , Long followMemberId) {
        Member member = memberService.findOne(memberId);
        Member followMember = memberService.findOne(followMemberId);
        validateFollow(memberId, followMemberId); //팔로우 중복 체크
        Follow follow = Follow.createFollow(member, followMember);
        followRepository.save(follow);
    }

    public void validateFollow(Long memberId , Long followMemberId) {
        Follow follow = followRepository.findByMemberIdAndFollowMemberId(memberId, followMemberId);
        if (follow != null) {
            throw new IllegalStateException("이미 팔로우 한 회원입니다.");
        }
    }

    /**
     * 팔로워 조회
     * @param followMemberId
     * @return followMember
     */
    @Transactional
    public List<Follow> follower(Long followMemberId) {
        Member followMember = memberService.findOne(followMemberId);
        return followRepository.findByFollowMember(followMember);
    }


    /**
     * 팔로잉 조회
     * @param memberId
     * @return Member
     */
    @Transactional
    public List<Follow> following(Long memberId) {
        Member followingMember = memberService.findOne(memberId);
        return followRepository.findByMember(followingMember);
    }

    /**
     * 언팔로우
     * @param follow_id
     */
    @Transactional
    public void unfollow(Long follow_id) {
        followRepository.deleteById(follow_id);
    }
}
