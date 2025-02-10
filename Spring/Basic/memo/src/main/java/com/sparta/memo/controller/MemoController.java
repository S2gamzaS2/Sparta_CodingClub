package com.sparta.memo.controller;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.entity.Memo;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MemoController {

    private final Map<Long, Memo> memoList = new HashMap<>();

    /**
     * 메모 생성
     * @param memoRequestDto
     * @return
     */
    @PostMapping("/memos")
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto memoRequestDto) {

        // 1) RequestDto -> Entity
        Memo memo = new Memo(memoRequestDto);

        // 2) Memo의 Max ID Check
        Long maxId = memoList.size() > 0 ? Collections.max(memoList.keySet()) + 1 : 1;
        memo.setId(maxId);

        // 3) DB 저장
        memoList.put(memo.getId(), memo);

        // 4) Entity -> ResponseDto
        MemoResponseDto memoResponseDto = new MemoResponseDto(memo);

        return memoResponseDto;
    }


    /**
     * 메모 조회
     * @return
     */
    @GetMapping("/memos")
    public List<MemoResponseDto> getMemos() {

        // 1) Map -> List
        List<MemoResponseDto> responseList = memoList.values().stream()
                .map(MemoResponseDto::new).toList();

        return responseList;
    }


    /**
     * 메모 수정
     * @param id
     * @param memoRequestDto
     * @return
     */
    @PutMapping("/memos/{id}")
    public int updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto memoRequestDto) {

        // 1) 해당 메모가 DB에 존재하는지 확인
        if(memoList.containsKey(id)) {
            // 2) 해당 메모 가져오기
            Memo memo = memoList.get(id);

            // 3) 메모 수정
            memo.update(memoRequestDto);

            return 1;

        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }


    /**
     * 메모 삭제
     * @param id
     * @return
     */
    @DeleteMapping("/memos/{id}")
    public int deleteMemo(@PathVariable Long id) {

        // 1) 해당 메모가 DB에 존재하는지 확인
        if(memoList.containsKey(id)) {
            // 2) 해당 메모 삭제;
            memoList.remove(id);
            return 1;
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }
}
