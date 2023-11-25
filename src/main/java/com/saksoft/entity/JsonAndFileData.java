package com.saksoft.entity;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "JsonAndFileData")
public class JsonAndFileData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String phoneNo;
    @Lob
    private byte[] image;
    @Lob
    private byte[] pdf;

}
