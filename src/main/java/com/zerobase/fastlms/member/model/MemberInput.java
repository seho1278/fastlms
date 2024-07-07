package com.zerobase.fastlms.member.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberInput {

    private String userId;
    private String userName;
    private String password;
    private String phone;

}
