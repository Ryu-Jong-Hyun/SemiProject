package semi.one.paging;

public class PagingService {

	public ListObject listPaging(ListObject oldLo) {
		int no = oldLo.getNo();
		int dataCnt = oldLo.getDataCnt();
		
		int pageCnt = (dataCnt-1)/3+1;//페이지 전체수
		
		ListObject newLo = new ListObject();
		int firstPage = 5*((no-1)/5)+1;
		System.out.println(firstPage);
		int lastPage = firstPage+4;
		if(pageCnt <= lastPage) {
			lastPage = pageCnt;
		}
		int idx = (no-1)*3+1;
		
		newLo.setDataCnt(dataCnt);
		newLo.setPageCnt(pageCnt);
		newLo.setNo(no);
		newLo.setFirstPage(firstPage);
		newLo.setLastPage(lastPage);
		newLo.setIdx(idx);
		
		return newLo;
	}
	
	

}
