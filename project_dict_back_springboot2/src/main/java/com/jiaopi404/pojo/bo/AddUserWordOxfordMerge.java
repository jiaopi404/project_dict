package com.jiaopi404.pojo.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddUserWordOxfordMerge implements Serializable {
    private static final long serialVersionUID = -6405871276217758758L;

    String userId;

    String wordId;
}
