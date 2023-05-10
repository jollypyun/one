package com.example.one.model.request;

import com.example.one.model.entity.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record JoinMember(
        @Size(min = 6, max = 20)
        String userId,

        @Size(min = 8, max = 20)
        String password,

        @Size(min = 2, max = 20)
        String nickname,

        @NotBlank(message = "This is required.")
        String name
) {
        public Member to() {
                return Member.builder()
                        .userId(this.userId)
                        .password(this.password)
                        .nickname(this.nickname)
                        .name(this.name)
                        .createAt(LocalDateTime.now())
                        .build();
        }
}
