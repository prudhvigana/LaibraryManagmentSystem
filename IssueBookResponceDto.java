package com.backendMarch.librarymanagementsysytem.DTO;

import com.backendMarch.librarymanagementsysytem.Enum.TransanctionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class IssueBookResponceDto {

    private String transanctionId;

    private String bookName;

    private TransanctionStatus transactionStatus;  //@Enumerated nahi likana hai kyuki sql sql me nahi java me store ho raha ye dto or java enumerated samajta hai

}
