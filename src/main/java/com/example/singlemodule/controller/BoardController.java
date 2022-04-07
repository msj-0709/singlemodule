package com.example.singlemodule.controller;

//깃허브 브랜치 문서정 테스트//////
import com.example.singlemodule.constant.UserStatusType;
import com.example.singlemodule.model.BoardResponse;
import com.example.singlemodule.model.CreateBoardRequest;
import com.example.singlemodule.repository.BoardRepository;
import com.example.singlemodule.repository.UserInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping({"/board"})
@RestController
@Slf4j
public class BoardController {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private BoardRepository boardRepository;

    @DeleteMapping({"/{boardId}"})
    public void delete(@PathVariable("boardId") String id) {
        boardRepository.findById(id)
                .ifPresent(boardRepository::delete);
    }

    @PostMapping
    public void createBoard(@RequestBody @Valid CreateBoardRequest request) {

        if (0L == this.userInfoRepository.countByUserInfoIdAndStatus(request.getUserInfoId(), UserStatusType.NORMAL))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not exists normal user.");
    }

    @GetMapping({"/{boardId}"})
    public BoardResponse info(@PathVariable("boardId") String id) {
        return null;
    }

    @GetMapping
    public List<BoardResponse> listByUser(
            @RequestParam(value = "search-writer", required = false) String writerUserInfoId) {
        return (List<BoardResponse>) this.boardRepository
                .findByUserInfoId(writerUserInfoId).stream().map(board -> BoardResponse.builder()
                        .boardId(board.getBoardId()).title(board.getTitle()).contents(board.getContents()).build())
                .collect(Collectors.toList());
    }

    public List<BoardResponse> list(@RequestParam(value = "page", defaultValue = "0") int page,
                                    @RequestParam(value = "size", defaultValue = "10") int size,
                                    @RequestParam(value = "search-writer", required = false) String writerUserInfoId) {
        return (List<BoardResponse>) this.boardRepository.findByUserInfoId(writerUserInfoId).stream()
                .map(board -> BoardResponse.builder().boardId(board.getBoardId()).title(board.getTitle())
                        .contents(board.getContents()).build())

                .collect(Collectors.toList());
    }
}
