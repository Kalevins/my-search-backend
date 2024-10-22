package com.kalevins.my_search_backend.models.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class IdentificationDto implements Serializable {
    private String type;
    private Long number;
}
