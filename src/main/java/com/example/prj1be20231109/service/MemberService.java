package com.example.prj1be20231109.service;

import com.example.prj1be20231109.domain.Member;
import com.example.prj1be20231109.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    // 의존성 주입:
    //데이터베이스 작업을 위해 MemberMapper를 사용합니다.
    private final MemberMapper mapper;
    // add: 새로운 멤버를 추가합니다.
    public boolean add(Member member) {
        return mapper.insert(member) == 1;
    }
    // getId 및 getEmail: 주어진 ID 또는 이메일을 가진 멤버가 이미 존재하는지 확인합니다.
    public String getId(String id) {
        return mapper.selectId(id);
    }

    public String getEmail(String email) {
        return mapper.selectEmail(email);
    }
    // validate: 멤버 객체를 유효성 검사합니다.
    public boolean validate(Member member) {
        if (member == null) {
            return false;
        }

        if (member.getEmail().isBlank()) {
            return false;
        }

        if (member.getPassword().isBlank()) {
            return false;
        }

        if (member.getId().isBlank()) {
            return false;
        }
        return true;
    }
    // list: 모든 멤버의 목록을 가져옵니다.
    public List<Member> list() {
        return mapper.selectAll();
    }

    // 멤버 정보 가져오기 및 삭제:
    //getMember 메서드는 특정 멤버의 정보를 가져옵니다.
    public Member getMember(String id) {
        return mapper.selectById(id);
    }
    // deleteMember 메서드는 멤버를 삭제합니다.
    public boolean deleteMember(String id) {
        return mapper.deleteById(id) == 1;
    }
}
