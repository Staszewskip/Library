package com.crud.library.service;

import com.crud.library.domain.BookCopy;
import com.crud.library.domain.BorrowRecord;
import com.crud.library.domain.User;
import com.crud.library.domain.dto.BorrowRecordDTO;
import com.crud.library.domain.dto.UserDTO;
import com.crud.library.exception.BookCopyNotFoundException;
import com.crud.library.exception.BorrowRecordNotFoundException;
import com.crud.library.exception.UserNotFoundException;
import com.crud.library.mapper.LibraryMapper;
import com.crud.library.repository.BookCopyRepository;
import com.crud.library.repository.BorrowRecordRepository;
import com.crud.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static com.crud.library.domain.BookCopyStatus.AVAILABLE;
import static com.crud.library.domain.BookCopyStatus.RENTED;

@Service
@Transactional
@RequiredArgsConstructor
public class DbService {

    private final BorrowRecordRepository borrowRecordRepository;
    private final UserRepository userRepository;
    private final BookCopyRepository bookCopyRepository;
    private final LibraryMapper libraryMapper;

    public User saveUser(UserDTO userDTO) {
        User user = libraryMapper.mapToUser(userDTO);
        userRepository.save(user);
        return user;
    }

    public List<UserDTO> showAllUsers() {
        List<User> userList = userRepository.findAll();
        return libraryMapper.mapToUserDTOList(userList);
    }


    public List<BorrowRecordDTO> showAllBorrowsByUser(Long userId) throws UserNotFoundException {
        List<BorrowRecord> borrowRecordList = borrowRecordRepository.findByUserId(userId).orElseThrow(
                UserNotFoundException::new);
        return libraryMapper.mapToBorrowRecordDTOList(borrowRecordList);
    }

    public BorrowRecord borrowBook(Long userId, Long bookCopyId) throws UserNotFoundException, BookCopyNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(
                UserNotFoundException::new);
        BookCopy bookCopy = bookCopyRepository.findById(bookCopyId).orElseThrow(
                BookCopyNotFoundException::new);

        BorrowRecord borrowRecord = new BorrowRecord(user, bookCopy);
        borrowRecordRepository.save(borrowRecord);
        bookCopy.setStatus(RENTED);
        bookCopyRepository.save(bookCopy);
        return borrowRecord;
    }

    public void returnBook(Long borrowRecordId) throws BorrowRecordNotFoundException {
        BorrowRecord borrowRecord = borrowRecordRepository.findById(borrowRecordId).orElseThrow(
                BorrowRecordNotFoundException::new);

        borrowRecord.setReturnDate(LocalDate.now());
        borrowRecord.setReturned(true);
        borrowRecordRepository.save(borrowRecord);

        BookCopy bookCopy = borrowRecord.getBookCopy();
        bookCopy.setStatus(AVAILABLE);
        bookCopyRepository.save(bookCopy);
    }

    public void deleteUser(Long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
    }

    public UserDTO updateUser(final UserDTO userDTO) throws UserNotFoundException {
        User user = userRepository.findById(userDTO.getUserId()).orElseThrow(UserNotFoundException::new);
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        userRepository.save(user);
        return libraryMapper.mapToUserDTO(user);
    }

}
