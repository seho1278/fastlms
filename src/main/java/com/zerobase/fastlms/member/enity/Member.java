package com.zerobase.fastlms.member.enity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    private String userId;

    private String userName;
    private String password;
    private String phone;

    private LocalDateTime regDt;

    // 이메일 인증 여부 확인
    private boolean emailAuthYn;
    private LocalDateTime emailAuthDt;
    // 이메일 인증키
    private String emailAuthKey;
}
