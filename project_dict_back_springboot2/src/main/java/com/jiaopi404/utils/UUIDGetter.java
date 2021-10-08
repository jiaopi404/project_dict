package com.jiaopi404.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * UUID getter
 * <p>
 * 1. 获取 UUID的方法 UUID uuid = UUID.randomUUID();
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UUIDGetter {
    /**
     * The Uuid.
     */
    String uuid;

    /**
     * 返回 32 字符的唯一id
     *
     * @return the as string
     */
    public static String getAsString () {
        UUID uuid = UUID.randomUUID();
        // 中间用 短横线连接，有平台兼容问题，替换为下划线
        String uuidString = uuid.toString();
        uuidString = uuidString.replace("-", "");
        return uuidString;
    }
}
