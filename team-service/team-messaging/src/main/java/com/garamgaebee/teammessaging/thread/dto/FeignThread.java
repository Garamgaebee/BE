package com.garamgaebee.teammessaging.thread.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class FeignThread {
    String content;
    int likeCount;
    int commentCount;
    LocalDateTime date;
}
