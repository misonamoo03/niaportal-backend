package com.misonamoo.niaportal.controller;

import com.misonamoo.niaportal.common.util.ApiResponseMessage;
import com.misonamoo.niaportal.domain.Board;
import com.misonamoo.niaportal.common.domain.CustomResponseMessage;
import com.misonamoo.niaportal.domain.BoardContent;
import com.misonamoo.niaportal.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 게시판 컨트롤러
 * @author Yohan
 */
@RestController
@RequestMapping("/board")
@Api(tags = "게시판 API")
public class BoardController extends ApiResponseMessage {

    private static final Logger log = LoggerFactory.getLogger(BoardController.class);

    @Autowired
    BoardService boardService;

    /**
     * HttpStatus에 따른 사용자 분기 처리
     * @param code
     * @return
     */
    public CustomResponseMessage setResponseMessage(int code) {
        CustomResponseMessage crm = new CustomResponseMessage();

        if (code == 100) {
            crm.setCode(100);
            crm.setMessage("필수인 변수값이 존재하지 않습니다.");
        }
        if (code == 101) {
            crm.setCode(101);
            crm.setMessage("게시판 번호 없음");
        }
        if (code == 400) {
            crm.setCode(400);
            crm.setMessage("서버 요청에 실패하였습니다.");
        }
        if (code == 500) {
            crm.setCode(500);
            crm.setMessage("서버측에서 오류가 발생하였습니다.");
        }

        crm.setCode(200);
        crm.setMessage("요청 성공");

        return crm;
    }

    /**
     * 목록 리턴.
     * @param page, pageSize
     * @return
     */
    @ApiOperation(value = "목록 조회 + 페이징처리", notes = "게시판 목록을 페이징 처리와 함께 조회할 수 있습니다.")
    @GetMapping("/list")
    public Map<String, Object> getBoardwithPagination(@RequestParam(defaultValue = "10") int pageSize,
                                                      @RequestParam(defaultValue = "0") int page,
                                                      HttpServletResponse res) {
        log.info("getBoardwithPagination ㅡ 호출");
        System.out.println("page : " + page + " /pageSize : " + pageSize);

        Map<String, Object> resultMap = new HashMap<>();
        List list = boardService.getBoardwithPagination(page, pageSize);
        CustomResponseMessage crm = setResponseMessage(res.getStatus());

        resultMap.put("code", crm.getCode());
        resultMap.put("message", crm.getMessage());
        resultMap.put("result",list);
        return resultMap;
    }
/*
    // 총 목록 개수
    public ResponseEntity<Integer> getBoardTotalCount() throws Exception {
        log.info("selectBoardTotalCount ㅡ 호출");
        return new ResponseEntity<Integer>(boardService.getBoardTotalCount(), HttpStatus.OK);
    }
*/

    /**
     * 상세 정보 리턴.
     * @param boardContentNo
     * @return
     */
    @ApiOperation(value = "상세 조회", notes = "게시물 번호에 해당하는 상세 정보를 조회할 수 있습니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "boardContentNo", value = "게시물 번호", example = "1")
    })
    @GetMapping("/detail/{boardContentNo}")
    public Map<String, Object> getBoard(@PathVariable Long boardContentNo, HttpServletResponse res) {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        CustomResponseMessage crm = setResponseMessage(res.getStatus());
        resultMap.put("code", crm.getCode());
        resultMap.put("message", crm.getMessage());
        resultMap.put("result", boardService.getBoard(boardContentNo));
        return resultMap;
    }

    /**
     * 등록 처리.
     * @param boardContent
     * @return
     */
    @ApiOperation(value = "등록 처리", notes = "신규 게시물 저장이 가능합니다.")
            @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "사용자 번호", example = "1"),
    @ApiImplicitParam(name = "boardNo", value = "게시판 번호", example = "게시판 번호"),
    @ApiImplicitParam(name = "title", value = "제목", example = "제목"),
    @ApiImplicitParam(name = "content", value = "내용", example = "내용")
    })
    @PostMapping("/insert")
    public Long insertBoard(BoardContent boardContent) {
       boardService.insertBoard(boardContent);
       return boardContent.getBoardContentNo();
    }

    /**
     * 수정 처리.
     * @param boardContent
     * @return
     */
    @ApiOperation(value = "등록 처리", notes = "기존 게시물 업데이트가 가능합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "boardContentNo", value = "게시물 번호", example = "1"),
            @ApiImplicitParam(name = "boardTitle", value = "제목", example = "테스트 제목"),
            @ApiImplicitParam(name = "boardContent", value = "내용", example = "테스트 내용")
    })
    @PostMapping("/update")
    public Long updateBoard(BoardContent boardContent) {
        boardService.updateBoard(boardContent);
        return boardContent.getBoardContentNo();
    }

    /**
     * 삭제 처리.
     * @param boardContentNo
     * @return
     */
    @ApiOperation(value = "삭제 처리", notes = "게시물 번호에 해당하는 정보를 삭제합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "boardContentNo", value = "게시물 번호", example = "1")
    })
    @PostMapping("/delete/{boardContentNo}")
    public boolean deleteBoard(@PathVariable Long boardContentNo) {
        Board board = boardService.getBoard(boardContentNo);
        if (board == null) {
            return false;
        }
        boardService.deleteBoard(boardContentNo);
        return true;
    }
}
