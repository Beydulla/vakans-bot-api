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

    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;

    private long chatId;
    private String confirmationKey;
    private boolean confirmed;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "telegram", cascade =  CascadeType.ALL )
    private User user;
}
