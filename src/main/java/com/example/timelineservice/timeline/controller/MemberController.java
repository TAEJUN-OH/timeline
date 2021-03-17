package com.example.timelineservice.timeline.controller;


import com.example.timelineservice.timeline.domain.Member;
import com.example.timelineservice.timeline.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    /**
     * 회원 등록 V1
     * @param request(name , email)
     * @return memberId
     */
    @PostMapping("/api/v1/members")
    public CreateMemberResponse createMember(@RequestBody @Valid CreateMemberRequest request) {
        Member member = Member.createMember(request.getName(), request.getEmail());
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    // URL 에 v1은 API 버전관리를 위함.
    //파라미터로 엔티티를 받지 않은 이유 : 엔티티와 API 스펙을 명확하게 분리할 수 있다.
    //엔티티가 변해도 API 스펙이 변하지 않는다.
    @Data
    static class CreateMemberRequest { //클라이언트에서 넘어온 요청 파라미터

        //회원 등록할 정보들
        private String name;
        private String email;
    }

    @Data
    static class CreateMemberResponse { //클라이언트로 보내줄 데이터
        private Long memberId;

        public CreateMemberResponse(Long memberId) {
            this.memberId = memberId;
        }
    }


    /**
     * 회원 수정 V1
     * @param memberId
     * @param request(name , email)
     * @return UpdateMemberResponse(memberId , name , email)
     */
    @PostMapping("/api/v1/members/{memberId}")
    public UpdateMemberResponse updateMember(@PathVariable("memberId") Long memberId,
                                             @RequestBody @Valid UpdateMemberRequest request) {
        memberService.update(memberId, request.getName() , request.getEmail());
        Member findMember = memberService.findOne(memberId);
        return new UpdateMemberResponse(findMember.getId(), findMember.getName() , findMember.getEmail());
    }

    @Data
    static class UpdateMemberRequest {
        private String name;
        private String email;
    }

    @Data
    @AllArgsConstructor
    class UpdateMemberResponse {
        private Long memberId;
        private String name;
        private String email;
    }


    /**
     * 회원 조회 V1
     * @param memberId
     * @return MemberDto
     */
    @GetMapping("/api/v1/members/{memberId}")
    public MemberDto findMember(@PathVariable("memberId") Long memberId) {
        Member member = memberService.findOne(memberId);
        return new MemberDto(member.getId(), member.getName(), member.getEmail() , member.getDelYn());
    }


    /**
     * 회원 전체조회 V1
     * @return
     */
    @GetMapping("/api/v1/members")
    public Result members() {

        List<Member> findMembers = memberService.findMembers();

        //엔티티 -> DTO 변환
        List<MemberDto> collect = findMembers.stream()
                .map(m -> new MemberDto(m.getId(), m.getName() , m.getEmail() , m.getDelYn()))
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
    class MemberDto {
        private Long id;
        private String name;
        private String email;
        private String delYn;
    }

    /**
     * 회원삭제
     * @param memberId
     */
    @DeleteMapping("/api/v1/members/{memberId}")
    public void deleteMember (@PathVariable("memberId") Long memberId) {
        memberService.delete(memberId);
    }

}
