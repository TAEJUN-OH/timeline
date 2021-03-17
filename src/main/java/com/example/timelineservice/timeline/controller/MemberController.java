package com.example.timelineservice.timeline.controller;


import com.example.timelineservice.timeline.domain.Member;
import com.example.timelineservice.timeline.dto.MemberDto;
import com.example.timelineservice.timeline.request.MemberRequest;
import com.example.timelineservice.timeline.response.Result;
import com.example.timelineservice.timeline.service.MemberService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1") // URL 에 v1은 API 버전관리를 위함.
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원 등록 v1
     * @param request(name , email)
     */
    @PostMapping("/members")
    @ApiOperation(value = "회원 등록", notes = "회원을 등록하는 API ")
    public ResponseEntity<?> createMember(@RequestBody @Valid MemberRequest request) {
        Member member = Member.createMember(request.getName(), request.getEmail());
        memberService.join(member);
        return ResponseEntity.noContent().build();
    }


    /**
     * 회원 수정 v1
     * @param memberId
     * @param request(name , email)
     */
    @PostMapping("/members/{memberId}")
    @ApiOperation(value = "회원 정보 수정", notes = "회원 정보를 수정하는 API ")
    public ResponseEntity<?> updateMember(@PathVariable("memberId") Long memberId,
                                             @RequestBody @Valid MemberRequest request) {
        memberService.update(memberId, request.getName() , request.getEmail());
        return ResponseEntity.noContent().build();
    }


    /**
     * 회원 조회 v1
     * @param memberId
     * @return MemberDto
     */
    @GetMapping("/members/{memberId}")
    @ApiOperation(value = "회원 조회", notes = "회원을 조회하는 API ")
    public MemberDto findMember(@PathVariable("memberId") Long memberId) {
        Member member = memberService.findOne(memberId);
        return new MemberDto(member.getId(), member.getName(), member.getEmail() , member.getDelYn());
    }


    /**
     * 회원 전체조회 v1
     * @return MemberDto List
     */
    @GetMapping("/members")
    @ApiOperation(value = "회원전체 리스트 조회", notes = "회원전체 리스트 조회하는 API ")
    public Result members() {

        List<Member> findMembers = memberService.findMembers();
        List<MemberDto> collect = findMembers.stream()
                .map(m -> new MemberDto(m.getId(), m.getName() , m.getEmail() , m.getDelYn()))
                .collect(Collectors.toList());
        return new Result(collect);
    }


    /**
     * 회원삭제 v1
     * @param memberId
     */
    @DeleteMapping("/members/{memberId}")
    @ApiOperation(value = "회원 삭제", notes = "회원 삭제 API")
    public ResponseEntity<?> deleteMember (@PathVariable("memberId") Long memberId) {
        memberService.delete(memberId);
        return ResponseEntity.noContent().build();
    }

}
