package com.betting.backend.games.sudoku.web;

import com.betting.backend.games.sudoku.model.SudokuBoard;
import com.betting.backend.games.sudoku.service.SudokuService;
import com.betting.backend.games.sudoku.web.dto.SudokuBoardDto;
import com.betting.backend.games.sudoku.web.dto.SudokuMoveRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sudoku")
/**
 *
 * This is the controller that talks with the frontend for the Sudoku app
 */
public class SudokuController {

    private final SudokuService service = new SudokuService(); // swap to DI later
    /**
     * This function generates a Data transfer object to create a new Puzzle
     * This function is a Get request to this URL
     * @param holes
     * @return
     */
    @GetMapping("/generate")
    public SudokuBoardDto generate(@RequestParam(defaultValue = "45") int holes) {
        SudokuBoard b = service.newPuzzle(holes);
        return new SudokuBoardDto(b.getBoard(), b.getFixedBoard());
    }
    /**
     * This function validates the users solution by transfering a Board Data Transfer Obejct
     * this function posts this board to the url that will eventually be validated
     * @param dto
     * @return
     */
    @PostMapping("/validate")
    public ResponseEntity<?> validate(@RequestBody SudokuBoardDto dto) {
        SudokuBoard b = fromDto(dto);
        return ResponseEntity.ok(service.validateBoard(b));
    }
    /**
     * This function generates a Data transfer object from the solve.
     * This returns a post request to the following URL
     * @param dto
     * @return
     */
    @PostMapping("/solve")
    public ResponseEntity<SudokuBoardDto> solve(@RequestBody SudokuBoardDto dto) {
        SudokuBoard b = fromDto(dto);
        boolean ok = service.solve(b);
        if (!ok) return ResponseEntity.unprocessableEntity().build();
        return ResponseEntity.ok(new SudokuBoardDto(b.getBoard(), b.getFixedBoard()));
    }

    @PostMapping("/move")
    public ResponseEntity<?> move(@RequestBody SudokuMoveRequest req, @RequestBody SudokuBoardDto dto) {
        // If you keep board client-side, prefer sending entire board; otherwise use a sessionId.
        SudokuBoard b = fromDto(dto);
        boolean ok = service.tryMove(b, req.row(), req.col(), req.value());
        return ResponseEntity.ok(ok);
    }

    private SudokuBoard fromDto(SudokuBoardDto dto) {
        SudokuBoard b = new SudokuBoard();
        b.setBoard(dto.board());
        b.setFixedBoard(dto.fixed());
        return b;
    }
}
