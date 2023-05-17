package com.example.one.model.entity;

import com.example.one.model.request.JoinMember;
import com.example.one.model.response.JoinResponse;
import com.example.one.support.common.GenerateId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "member")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "password")
    private String password;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "name")
    private String name;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    public JoinResponse of() {
        return new JoinResponse(this.userId,this.nickname, this.name);
    }
}
