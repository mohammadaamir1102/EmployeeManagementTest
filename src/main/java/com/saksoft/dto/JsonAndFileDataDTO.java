package com.saksoft.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class JsonAndFileDataDTO {
    private Long id;
    private String name;
    private String email;
    private String phoneNo;
}
