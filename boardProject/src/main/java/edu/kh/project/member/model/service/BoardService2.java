package edu.kh.project.member.model.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import edu.kh.project.board.model.dto.Board;

public interface BoardService2  {

	
	/** 게시글 삽입
	 * @param board
	 * @param images
	 * @param webPath
	 * @param filePath
	 * @return
	 */
	int boardInsert(Board board, List<MultipartFile> images, String webPath, String filePath) throws IllegalStateException, IOException;

	int boardUpdate(Board board, List<MultipartFile> images, String webPath, String filePath, String deleteList)throws IllegalStateException, IOException;

	int boardDelete(Map<String, Object> map);
	
}
