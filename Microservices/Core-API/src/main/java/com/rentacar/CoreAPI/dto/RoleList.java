package com.rentacar.CoreAPI.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RoleList {

    private List<Role> roleList = new ArrayList<>();

}
