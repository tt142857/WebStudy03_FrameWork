package kr.or.ddit.vo;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 페이징 처리와 관련된 모든 데이터를 가진 객체
 * setTotalRecord/setCurrenPage가 호출되어야 연산 완료
 * @param <T>
 */
@Getter
@NoArgsConstructor
@ToString
public class PagingVO<T> {
	
	public PagingVO(int screenSize, int blockSize) {
		this.screenSize = screenSize;
		this.blockSize = blockSize;
	}

	private int totalRecord; // DB 조회(**)
	private int totalPage; 
	private int screenSize = 10; // 임의 결정
	private int blockSize = 5; // 임의 결정
	private int currentPage; // 사용자의 파라미터
	private int startRow;
	private int endRow;
	private int startPage;
	private int endPage;
	
	private List<T> dataList;
	
	/**
	 * 단순 키워드 검색용
	 */
	private SimpleSearchCondition simpleCondition;
	
	private T detailCondition;
	
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		this.totalPage = (totalRecord + (screenSize - 1)) / screenSize;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		
		this.endRow = screenSize * currentPage;
		this.startRow = endRow - (screenSize - 1);
		this.endPage =  ((currentPage + (blockSize - 1)) / blockSize) * blockSize;
		this.startPage = endPage - (blockSize - 1);
	}
	
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
	
	public void setSimpleCondition(SimpleSearchCondition simpleCondition) {
		this.simpleCondition = simpleCondition;
	}
	
	public void setDetailCondition(T detailCondition) {
		this.detailCondition = detailCondition;
	}
	
	private static final String PAGINGPTRN = "<a href='#' data-page='%d'>%s</a>\n";
	public String getPagingHTML() {
		endPage = endPage > totalPage ? totalPage : endPage;
		StringBuffer html = new StringBuffer();
		if(startPage > blockSize) {
			html.append(String.format(PAGINGPTRN, (startPage - blockSize), "이전"));
		}
		
		for(int page = startPage; page <= endPage; page++) {
			html.append(String.format(PAGINGPTRN, page, Integer.toString(page)));
		}
		
		if(totalPage > endPage) {
			html.append(String.format(PAGINGPTRN, (endPage + 1), "다음"));
		}
		return html.toString();
	}
	
	public String getPagingHTMLBS() {
		StringBuffer html = new StringBuffer();
		html.append("<nav aria-label=\"Page navigation example\">\n");
		html.append("	<ul class='pagination'>\n");
		
		String[] pagingHTML = getPagingHTML().split("\\n");
		for(String ph : pagingHTML) {
			html.append(String.format("<li class='page-item'>%s</li>\n", ph.replace("<a", "<a class='page-link'")));
		}

		html.append("	</ul>\n");
		html.append("</nav>\n");

		return html.toString();
	}
}
