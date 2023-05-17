package com.example.one.model.request;

import com.example.one.model.entity.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record JoinMember(
        @Size(min = 6, max = 20, message = "ID 잘못 입력")
        @NotBlank(message = "id은 필수 항목입니다.")
        @Pattern(regexp = "^(?=.*?[a-z])(?=.*?[0-9]).{6,20}$")
        String userId,

        @Size(min = 8, max = 20, message = "PW 잘못 입력")
        @NotBlank(message = "pw은 필수 항목입니다.")
        @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$")
        String password,

        @Size(min = 2, max = 20, message = "nickname 잘못 입력")
        @NotBlank(message = "nickname은 필수 항목입니다.")
        @Pattern(regexp = "^[a-zA-Z0-9]{2,20}$")
        String nickname,

        @NotBlank(message = "name은 필수 항목입니다.")
        @Pattern(regexp = "^[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]{2,10}$")
        String name
) {
        public Member to(String newPassword) {
                return Member.builder()
                        .userId(this.userId)
                        .password(newPassword)
                        .nickname(this.nickname)
                        .name(this.name)
                        .createAt(LocalDateTime.now())
                        .build();
        }
}
