package com.example.one.model.request;

import com.example.one.model.entity.Member;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record JoinMember(
        @NotBlank(message = "This is required.")
        @Min(6)@Max(20)
        String userId,

        @NotBlank(message = "This is required.")
        @Min(8)@Max(20)
        String password,

        @NotBlank(message = "This is required.")
        @Min(2)@Max(20)
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
