package com.ecommerce.mailingservice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mail {

    private String from;
    private String to;
    private String subject;
    private String content;

}
