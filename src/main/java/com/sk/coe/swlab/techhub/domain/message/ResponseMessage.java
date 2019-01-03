package com.sk.coe.swlab.techhub.domain.message;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ResponseMessage<T> {

    private Boolean success;

    private T body;

}
