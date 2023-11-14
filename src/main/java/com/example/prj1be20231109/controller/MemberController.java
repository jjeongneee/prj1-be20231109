package com.example.prj1be20231109.controller;

import com.example.prj1be20231109.domain.Member;
import com.example.prj1be20231109.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService service;

    // POST /api/member/signup: 멤버 등록을 처리합니다.
    // 멤버를 유효성 검사하고 유효하다면 시스템에 추가합니다.
    // 적절한 상태 코드로 응답합니다.

    // 멤버 유효성 검사:
    //
    //MemberService 클래스의 validate 메서드를 사용하여 특정 작업 전에 멤버 객체를 유효성 검사합니다.
    @PostMapping("signup")
    public ResponseEntity signup(@RequestBody Member member) {
        if (service.validate(member)) {
            if (service.add(member)) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.internalServerError().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    // GET /api/member/check (파라미터: id): 주어진 ID의 멤버가 존재하는지 확인합니다.
    @GetMapping(value = "check", params = "id")
    public ResponseEntity checkId(String id) {
        if (service.getId(id) == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().build();
        }
    }

    // GET /api/member/check (파라미터: email): 주어진 이메일을 가진 멤버가 존재하는지 확인합니다.
    @GetMapping(value = "check", params = "email")
    public ResponseEntity checkEmail(String email) {
        if (service.getEmail(email) == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().build();
        }
    }

    @GetMapping(value = "check", params = "nickName")
    public ResponseEntity checkNickName(String nickName) {
        if (service.getNickName(nickName) == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().build();
        }
    }

    // GET /api/member/list: 모든 멤버의 목록을 가져옵니다.
    @GetMapping("list")
    public List<Member> list() {
        return service.list();
    }

    // GET /api/member: 특정 멤버에 대한 정보를 가져옵니다.
    @GetMapping
    public ResponseEntity<Member> view(String id) {
        // TODO : 로그인 했는지? -> 안했으면 401
        // TODO : 자기 정보인지? -> 아니면 403

        Member member = service.getMember(id);

        return ResponseEntity.ok(member);
    }

    // DELETE /api/member: 멤버를 삭제합니다.
//    회원을 보거나 삭제하기 전에 인증 및 권한 확인을 위한 플레이스홀더가 있습니다. 이를 구현해야 합니다.
    @DeleteMapping
    public ResponseEntity delete(String id) {
        // TODO : 로그인 했는지? -> 안했으면 401
        // TODO : 자기 정보인지? -> 아니면 403

        if (service.deleteMember(id)) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.internalServerError().build();
    }

    @PutMapping("edit")
    public ResponseEntity edit(@RequestBody Member member) {
        // TODO: 로그인 했는지? 자기 정보인지?

        if (service.update(member)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody Member member, WebRequest request) {
        System.out.println("member = " + member);

        if (service.login(member, request)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
