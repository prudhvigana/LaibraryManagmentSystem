package com.backendMarch.librarymanagementsysytem.Controller;

import com.backendMarch.librarymanagementsysytem.DTO.IssueBookRequestDto;
import com.backendMarch.librarymanagementsysytem.DTO.IssueBookResponceDto;
import com.backendMarch.librarymanagementsysytem.Service.TransanctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transantion")
public class TransanctionController {

    @Autowired
    TransanctionService transanctionService;

    @PostMapping("/issue")
    public ResponseEntity issueBook(@RequestBody IssueBookRequestDto issueBookRequestDto) {

        IssueBookResponceDto issueBookResponseDto;
        try{
            issueBookResponseDto = transanctionService.issueBook(issueBookRequestDto);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(issueBookResponseDto, HttpStatus.ACCEPTED);
    }
    @GetMapping("/get")
    public String getAllTxns(@RequestParam("cardId") int cardId){
        return transanctionService.getAllTxns(cardId);
    }

}
