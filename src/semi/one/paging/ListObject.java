package semi.one.paging;


public class ListObject {
	
	private int dataCnt;//데이터 총 갯수
	private int pageCnt;//페이지 총 갯수
	private int no;//현재 페이지넘버
	private int firstPage;//현재 첫페이지
	private int lastPage;//현재 마지막 페이지
	private int idx; //현재페이지의 첫 데이터 컬럼넘버
	private int x; //한페이지에 몇개
	private int y; //이전과 다음사이에 몇개의 페이지가 들어있는지
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getFirstPage() {
		return firstPage;
	}
	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}
	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getDataCnt() {
		return dataCnt;
	}
	public void setDataCnt(int dataCnt) {
		this.dataCnt = dataCnt;
	}
	public int getPageCnt() {
		return pageCnt;
	}
	public void setPageCnt(int pageCnt) {
		this.pageCnt = pageCnt;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
}
