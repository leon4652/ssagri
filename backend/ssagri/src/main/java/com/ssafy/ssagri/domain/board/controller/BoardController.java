package com.ssafy.ssagri.domain.board.controller;

import com.ssafy.ssagri.domain.auction.dto.AuctionProductAllDTO;
import com.ssafy.ssagri.domain.auction.repository.AuctionCustomRepository;
import com.ssafy.ssagri.domain.board.dto.BoardCreateDto;
import com.ssafy.ssagri.domain.board.dto.BoardDto;
import com.ssafy.ssagri.domain.board.repository.BoardRopository;
import com.ssafy.ssagri.domain.board.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/board")
@RequiredArgsConstructor
@Api(tags = {"게시판에 대한 API"})
@CrossOrigin("*")
public class BoardController {

    final private BoardService boardService;


    // 모든 게시판 출력
    @GetMapping(value = "/all-list")
    @ApiOperation("모든 게시판 출력")
    public List<BoardDto> allList(){

        return boardService.boardList();

    }

    // 게시판 등록
    @PostMapping(value = "/regist")
    @ApiOperation("게시판 등록")
    public void regist(@RequestBody BoardCreateDto boardCreateDto){
        boardService.regist(boardCreateDto);

    }


    }