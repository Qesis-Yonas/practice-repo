package com.vaccinationcenter.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrintAllTogether {

    private List<UserVacCenterResponse> userVacCenterResponseList;
}
