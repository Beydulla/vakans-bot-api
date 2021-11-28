package com.vakans.bot.api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Telegram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "expired_at")
    private LocalDateTime expiredAt;

    @Column(name = "chat_id")
    private long chatId;
    @Column(name = "confirmation_key")
    private String confirmationKey;
    private byte confirmed;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "telegram", cascade =  CascadeType.ALL )
    private User user;
}
