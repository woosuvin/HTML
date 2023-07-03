package com.itwill.spring3.repository.member;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.itwill.spring3.repository.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
@Entity
@Table(name = "MEMBERS")
@SequenceGenerator(name = "MEMBERS_SEQ_GEN", sequenceName = "MEMBERS_SEQ", allocationSize = 1) //sequenceName -> 실제 DB에 만들어져 있는 이름
// Member IS-A Userdetails
// Spring security는 로크인 처리를 위해서 UserDetails 객체를 사용하기 때문에
// 회원 정보 엔터티는 UserDetails 인터페이스를 구현해야 함.
public class Member extends BaseTimeEntity implements UserDetails {
    
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBERS_SEQ_GEN") // IDENTITY -> maria DB, mySQL에서 사용
    private Long id;
    
    @Column(nullable = false, unique = true) // NOT NULL, UNIQUE 제약 조건
    private String username;
    
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private String email;
    
    @Column(nullable = false)
    private Role role;
    
    @Builder
    private Member(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = Role.USER; // 회원가입 사용자 권한의 기본값은 USER
    }
    
    // UserDetails 인터페이스의 추상 메서드들을 구현:
    // 리턴 타입 Collection -> list 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // ROLE_USER 권한을 가짐.
        return Arrays.asList(new SimpleGrantedAuthority(role.getKey()));
    }

    @Override
    public boolean isAccountNonExpired() { // 계정이 만료되지 않았나요? 메서드 이름 is~ -> 리턴타입 boolean
        return true; // 계정(account)이 non_expired(만료되지 않음). 휴먼계정 만드는 메서드
    }

    @Override
    public boolean isAccountNonLocked() { // 계정 잠김
        return true; // 계정이 non-lock(잠기지 않음). false면 로그인 안됨. 비밀번호 n번 틀렸을 때 쓰는 메서드.
    }

    @Override
    public boolean isCredentialsNonExpired() { // 비밀번호 만료 여부
        return true; // 비밀번호가 non-expired. 만료되지 않음. 비밀번호 6개월마다 변경하라고 하는 메서드
    }

    @Override
    public boolean isEnabled() {
        return true; // 사용자 상세정보(UserDetails)가 활성화(enable). false는 회원탈퇴. 탈퇴하면 DB에서 바로 삭제 안시킴.
    }
}
