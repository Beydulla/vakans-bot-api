package com.vakans.bot.api.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Getter
public enum ChatActionType {
    START,
    TAG,
    COMPANY,
    MINIMUM_SALARY,
    MAXIMUM_SALARY,
    FINISH
}
