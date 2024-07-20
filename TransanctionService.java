package com.backendMarch.librarymanagementsysytem.Service;

import com.backendMarch.librarymanagementsysytem.DTO.IssueBookRequestDto;
import com.backendMarch.librarymanagementsysytem.DTO.IssueBookResponceDto;
import com.backendMarch.librarymanagementsysytem.Entity.Book;
import com.backendMarch.librarymanagementsysytem.Entity.LibraryCard;
import com.backendMarch.librarymanagementsysytem.Entity.Transaction;
import com.backendMarch.librarymanagementsysytem.Enum.CardStatus;
import com.backendMarch.librarymanagementsysytem.Enum.TransanctionStatus;
import com.backendMarch.librarymanagementsysytem.Repository.BookRepository;
import com.backendMarch.librarymanagementsysytem.Repository.CardRepository;
import com.backendMarch.librarymanagementsysytem.Repository.TransanctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransanctionService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    TransanctionRepository transanctionRepository;

    @Autowired
    private JavaMailSender emailSender;

    public IssueBookResponceDto issueBook(IssueBookRequestDto issueBookRequestDto) throws Exception {

        // Create Transaction Object
        Transaction transaction = new Transaction();
        transaction.setTransactionNumber(String.valueOf(UUID.randomUUID()));
        transaction.setIssueOperation(true);

        //1 step
        LibraryCard card;
        try {
            card = cardRepository.findById(issueBookRequestDto.getCardId()).get();
        }
        catch (Exception e) {
            transaction.setTransactionStatus(TransanctionStatus.FAILED);
            transaction.setMessage("Invalid card id");
            transanctionRepository.save(transaction);
            throw new Exception("Invalid card id");
        }

        Book book;
        try {
            book = bookRepository.findById(issueBookRequestDto.getBookId()).get();
        }
        catch (Exception e) {
            transaction.setTransactionStatus(TransanctionStatus.FAILED);
            transaction.setMessage("Invalid book id");
            transanctionRepository.save(transaction);
            throw new Exception("Invalid book id");
        }

        //both book ans card valid
        transaction.setBook(book);
        transaction.setCard(card);

        if(card.getStatus() != CardStatus.ACTIVATED) {
            transaction.setTransactionStatus(TransanctionStatus.FAILED);
            transaction.setMessage("Your card is not activated");
            transanctionRepository.save(transaction);
            throw new Exception("Your card is not activated");
        }

        if(book.isIssued() == true) {
            transaction.setTransactionStatus(TransanctionStatus.FAILED);
            transaction.setMessage("Sorry! Book is already issued.");
            transanctionRepository.save(transaction);
            throw new Exception("Sorry! Book is already issued");
        }

        //I can issue the book
        transaction.setTransactionStatus(TransanctionStatus.SUCCESS);
        transaction.setMessage("Transanction was successfull");

        book.setIssued(true);
        book.setCard(card);
        book.getTransactions().add(transaction);
        card.getTransactionList().add(transaction);
        card.getBooksIssued().add(book);

        cardRepository.save(card);   // will save book and tranaction also

        // Prepare Response Dto
        IssueBookResponceDto issueBookResponseDto = new IssueBookResponceDto();
        issueBookResponseDto.setTransanctionId(transaction.getTransactionNumber());
        issueBookResponseDto.setTransactionStatus(TransanctionStatus.SUCCESS);
        issueBookResponseDto.setBookName(book.getTitle());

        // send an email
        String text = "Congrats !!." + card.getStudent().getName()+ "You have been issued "+book.getTitle()+" book.";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("backendavengers@gmail.com");
        message.setTo(card.getStudent().getEmail());
        message.setSubject("Issue Book Notification");
        message.setText(text);
        emailSender.send(message);

        return issueBookResponseDto;
    }

    public String getAllTxns(int cardId){
        List<Transaction> transactionList = transanctionRepository.getAllSuccessfullTxnsWithCardNo(cardId);
        String ans = "";
        for(Transaction t: transactionList){
            ans += t.getTransactionNumber();
            ans += "\n";
        }

        return ans;
    }
}
