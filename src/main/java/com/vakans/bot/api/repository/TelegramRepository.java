package com.vakans.bot.api.repository;

import com.vakans.bot.api.domain.model.Telegram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelegramRepository extends JpaRepository<Telegram, Long> {
}
