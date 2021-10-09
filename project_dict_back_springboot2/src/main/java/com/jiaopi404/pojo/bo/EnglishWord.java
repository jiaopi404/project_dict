package com.jiaopi404.pojo.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * The type Dictionary.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EnglishWord implements Serializable {
    private static final long serialVersionUID = -7519499518887676885L;

    /**
     * The Word.
     */
    String word;

    /**
     * The Exp.
     */
    String exp; // explain
}
