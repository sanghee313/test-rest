package com.example.studio.thinkground.data.dto;

import lombok.*;

import javax.persistence.Id;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ShortUrlResponseDto implements Serializable {

    private static final long serialVersionUID = -214490344996507077L;

    @Id
    private String orgUrl;

    private String shortUrl;


}
