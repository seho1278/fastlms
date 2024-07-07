package com.zerobase.fastlms.member.enity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Member {

    @Id
    private String userId;

    private String userName;
    private String password;
    private String phone;

    private LocalDateTime regDt;

    // 이메일 인증 여부 확인
    private boolean emailAuthYn;
    // 이메일 인증키
    private String emailAuthKey;
}
