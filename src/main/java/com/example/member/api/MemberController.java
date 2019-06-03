package com.example.member.api;

import com.example.member.model.Member;
import com.example.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/api/members")
    public List<Member> getMembers(@RequestParam(value = "name", required = false) String name) {
        if (name != null) {
            return memberRepository.findAllByNameContains(name);
        }
        return memberRepository.findAll();
    }

    @GetMapping("/api/members/{id}")
    public Member getMember(@PathVariable("id") Long id) {
        return memberRepository.findById(id).orElseGet(Member::new);
    }

    @PostMapping("/api/members")
    public Member createMember(Member member) {
        return memberRepository.save(member);
    }
}
