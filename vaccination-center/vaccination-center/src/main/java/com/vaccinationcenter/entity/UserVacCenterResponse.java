package com.vaccinationcenter.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVacCenterResponse {

    private VacCenter vacCenter;
    private List<User> userList;
}
