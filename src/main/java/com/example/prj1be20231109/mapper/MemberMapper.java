package com.example.prj1be20231109.mapper;

import com.example.prj1be20231109.domain.Member;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

// MyBatis SQL 쿼리:
//멤버를 삽입, 선택 및 삭제하기 위한 SQL 쿼리를 정의합니다.
@Mapper
public interface MemberMapper {

    @Insert("""
            INSERT INTO member (id, password, email)
            VALUES (#{id}, #{password}, #{email})
            """)
    int insert(Member member);

    @Select("""
            SELECT id FROM member
            WHERE id = #{id}
            """)
    String selectId(String id);

    @Select("""
            SELECT email FROM member
            WHERE email = #{email}
            """)
    String selectEmail(String email);

    @Select("""
            SELECT id, password, email, inserted
            FROM member
            ORDER BY inserted DESC 
            """)
    List<Member> selectAll();

    @Select("""
            SELECT *
            FROM member
            WHERE id = #{id}
            """)
    Member selectById(String id);

    @Delete("""
            DELETE FROM member
            WHERE id = #{id}
            """)
    int deleteById(String id);
}
