package semi.one.paging;

public class PagingService {

	public ListObject listPaging(ListObject oldLo) {
		int no = oldLo.getNo();
		int dataCnt = oldLo.getDataCnt();
		int x = oldLo.getX();
		int y = oldLo.getY();
		
		int pageCnt = (dataCnt-1)/x+1;//페이지 전체수
		
		ListObject newLo = new ListObject();
		int firstPage = y*((no-1)/y)+1;
		System.out.println(firstPage);
		int lastPage = firstPage+(y-1);
		if(pageCnt <= lastPage) {
			lastPage = pageCnt;
		}
		int idx = (no-1)*x+1;
		
		newLo.setDataCnt(dataCnt);
		newLo.setPageCnt(pageCnt);
		newLo.setNo(no);
		newLo.setFirstPage(firstPage);
		newLo.setLastPage(lastPage);
		newLo.setIdx(idx);
		newLo.setX(x);
		
		return newLo;
	}
	
	

}
