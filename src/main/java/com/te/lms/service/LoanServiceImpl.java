package com.te.lms.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.te.lms.dto.FetchBookAuthorByFNnLNDTO;
import com.te.lms.entity.Book;
import com.te.lms.entity.Loan;
import com.te.lms.entity.Member;
import com.te.lms.repository.BookRepository;
import com.te.lms.repository.LoanRepository;
import com.te.lms.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LoanServiceImpl implements LoanService {

	private final LoanRepository loanRepository;
	private final MemberRepository memberRepository;
	private final BookRepository bookRepository;

	@Override
	public String saveLoan(String title, String mailId) {

		Optional<Member> memberFromDB = memberRepository.findByMailId(mailId);
		Member member = memberFromDB.get();

		Optional<Book> bookFromDB = bookRepository.findByTitle(title);
		Book book = bookFromDB.get();

		Loan loan = Loan.builder().issueDate(LocalDate.now()).returnDate(LocalDate.now().plusDays(20))
				.loanId(UUID.randomUUID().toString()).books(Arrays.asList(book)).members(Arrays.asList(member)).build();
		member.getLoans().add(loan);
		book.getLoans().add(loan);
		Loan loanFromDB = loanRepository.save(loan);

		return loan.getLoanId();
	}

	@Override
	public List<String> getLoanDetails() {
		List<Loan> loansFromDB = loanRepository.findAll();

		return loansFromDB.stream()
				.map(c -> "Member Name -->" + c.getMembers().get(0).getFirstName() + " "
						+ c.getMembers().get(0).getLastName() + " Book Title --> " + c.getBooks().get(0).getTitle())
				.collect(Collectors.toList());
	}

	@Override
	public List<String> getAuthors(String firstName, String lastName) {
		Member member = memberRepository.findByFirstNameAndLastName(firstName, lastName);
		List<Loan> loansFromDB = member.getLoans();

		return loansFromDB.stream().map(c -> "List of Authors " + c.getBooks().get(0).getAuthor().getFirstName() + " "
				+ c.getBooks().get(0).getAuthor().getLastName()).collect(Collectors.toList());

	}

	@Override
	public List getBooksAuthor(FetchBookAuthorByFNnLNDTO bookAuthorByFNnLNDTO) {
		
		Member member = memberRepository.findByFirstNameAndLastName(bookAuthorByFNnLNDTO.getFirstName(),
				bookAuthorByFNnLNDTO.getLastName());
		
		List<Loan> loansFromDB = member.getLoans();
		
		
		return loansFromDB
				.stream()
				.map(l->l.getBooks().get(0).getTitle()+" "+l.getBooks().get(0).getAuthor().getFirstName())
				.collect(Collectors.toList());
	}

}
