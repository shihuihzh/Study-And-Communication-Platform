package com.study.platform.util;
import java.io.Serializable;
import java.util.LinkedList;

import org.apache.commons.lang.ArrayUtils;

/**
 * @ClassName: PageController 
 * @Description: TODO(分页控制器) 
 * @author 黄展豪
 * @date 2014-3-24 下午5:15:24 
 * @version V1.0
 *
 */
/*需要知道的条件：
 1)总记录数	totalRows
 2)每一页显示的记录数	pageSize
 3)当前页	currPage

 计算出来的数据：
 总页数 totalPages：
 if(totalRows%pageSize==0){
 totalPages = totalRows/pageSize;
 }else{
 totalPages = totalRows/pageSize+1;
 }
 开始行startRow：
 startRow = (currPage-1)*pageSize;
 结束行endRow：
 endRow = currPage*pageSize;
 上一页prePage：
 prePage = currPage-1;
 下一页nextPage：
 nextPage = currPage+1;
 是否有上一页：
 是否有下一页：*/
public class PageController implements Serializable {

	// 总行数
	long totalRowsAmount;
	// 每页行数
	long pageSize = 10;
	// 总页数
	long totalPages;
	// 当前页码
	long currentPage = 1;
	// 下一页
	long nextPage;
	// 上一页
	long previousPage;
	// 是否有下一页
	boolean hasNext;
	// 是否有上一页
	boolean hasPrevious;

	// 当前页开始行
	long pageStartRow;

	// 当前页要显示的行数
	long pageEndRow;

	// 生成页码表
	LinkedList<PageNumber> pageNumbers = new LinkedList<PageNumber>();;

	public PageController(long pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 构造函数。
	 * 
	 * @param totalRows
	 *            总行数
	 * @param currentPage
	 *            当前页码
	 */

	public PageController(long totalRows, long currentPage) {
		setPageController(totalRows, currentPage);
		
	}

	public PageController(long totalRows, long currentPage, long pageSize) {
		this.pageSize = pageSize;
		this.setPageController(totalRows, currentPage);
	}

	public void setPageController(long totalRows, long currentPage) {

		setTotalRowsAmount(totalRows);
		setCurrentPage(currentPage);
	}

	/**
	 * 设置总行数和总页数。
	 * 
	 * @param i
	 *            总行数。
	 */
	private void setTotalRowsAmount(long rows) {

		if (rows < 0) {
			totalRowsAmount = 0;
		} else {
			totalRowsAmount = rows;
		}

		if (totalRowsAmount % pageSize == 0) {
			totalPages = totalRowsAmount / pageSize;
		} else {
			totalPages = totalRowsAmount / pageSize + 1;
		}
	}

	/**
	 * 设置当前页数。
	 * 
	 * @param i
	 */
	public void setCurrentPage(long curPage) {

		if (totalPages > 0) {
			// 计算当前页码
			if (curPage <= 0) {
				currentPage = 1;
			} else if (curPage > totalPages) {
				currentPage = totalPages;
			} else {
				currentPage = curPage;
			}

			// 计算是否有上一页,是否有下一页
			if (currentPage <= 1) {
				hasPrevious = false;
			} else {
				hasPrevious = true;
			}

			if (currentPage >= totalPages) {
				hasNext = false;
			} else {
				hasNext = true;
			}

			// 计算上一页和下一页
			nextPage = currentPage + 1;
			previousPage = currentPage - 1;

			// 计算开始行和结束行
			if (currentPage != totalPages) {
				pageStartRow = (currentPage - 1) * pageSize;
				// 记录索引从0开始
				pageEndRow = currentPage * pageSize;
			} else {
				pageStartRow = (currentPage - 1) * pageSize;
				// 记录索引从0开始
				pageEndRow = totalRowsAmount;// - pageStartRow;
			}
		} else {
			hasPrevious = false;
			hasNext = false;
			nextPage = 0;
			previousPage = 0;
			pageStartRow = 0;
			pageEndRow = 0;
			currentPage = 0;
		}
	}

	public long getCurrentPage() {
		return currentPage;
	}

	public boolean isHasNext() {
		return hasNext;
	}

	public boolean isHasPrevious() {
		return hasPrevious;
	}

	public long getNextPage() {
		return nextPage;
	}

	public long getPageSize() {
		return pageSize;
	}

	public long getPreviousPage() {
		return previousPage;
	}

	public long getTotalPages() {
		return totalPages;
	}

	public long getTotalRowsAmount() {
		return totalRowsAmount;
	}

	public long getPageStartRow() {
		return pageStartRow;
	}

	public long getPageEndRow() {
		return pageEndRow;
	}
	
	public LinkedList<PageNumber> getPageNumbers() {
		return pageNumbers;
	}

	public void setPageNumbers(LinkedList<PageNumber> pageNumbers) {
		this.pageNumbers = pageNumbers;
	}

	public String description() {
		String description = "Total:" + this.getTotalRowsAmount() + " items "
				+ this.getTotalPages() + " pages,Current page:"
				+ this.currentPage + " Previous " + this.hasPrevious + " Next:"
				+ this.hasNext + " start row:" + this.pageStartRow
				+ " end row:" + this.pageEndRow;
		return description;
	}

	/**
	 * 生成页码列表
	 * 
	 * @param initDisplayPageNumber
	 *            最多显示页码
	 */
	public void generatePageNumber(long initDisplayPageNumber) {
		
		if (this.totalRowsAmount <= 0) {
			pageNumbers.clear();
			return;
		}
		
		pageNumbers.clear();
		
		
		int mid = (int) (initDisplayPageNumber / 2);

		long curPage = this.currentPage;

		if (this.totalPages > 0 && this.totalPages <= initDisplayPageNumber) { // 若初始显示页数大于总页数，全部显示
			for (long i = 1; i <= this.totalPages; i++) {
				pageNumbers.addLast(new PageNumber(i, i == curPage, false));
			}
		} else {
			for (long i = 1; i <= mid; i++) {
				pageNumbers.addLast(new PageNumber(i, i == curPage, false));
			}
			
			/*if(curPage < initDisplayPageNumber + mid ) {	// 若当前页码小于初始页码加上中间值，不出现中间的省略
				boolean hasEnd = true;
				long max = initDisplayPageNumber + curPage -1;
				
				if(max >= totalPages) {	// 可以显示到最后一页的页码了，不出现最后的省略
					max = totalPages;
					hasEnd = false;
				}
				for(int i = mid+1; i <= max; i++) {
					pageNumbers.addLast(new PageNumber(i, i == curPage, false));
				}
				
				if(hasEnd) {
					pageNumbers.addLast(new PageNumber(this.nextPage, false, true));
				}
			} else {	// 若当前页码大于初始页码加上中间值，出现中间省略
				boolean hasEnd = true;
				pageNumbers.addLast(new PageNumber(this.previousPage, false, true));
				
				long max = initDisplayPageNumber + curPage -1;
				if(max >= totalPages) { // 可以显示到最后一页的页码了，不出现最后的省略
					max = totalPages;
					hasEnd = false;
				}
				
				for(int i = (int) (curPage - mid); i <= max; i++) {
					pageNumbers.addLast(new PageNumber(i, i == curPage, false));
				}
				
				if(hasEnd) {
					pageNumbers.addLast(new PageNumber(this.nextPage, false, true));
				}
				
			}*/
			
			boolean hasEnd = true;
			
			long max = initDisplayPageNumber + curPage -1;
			if(max >= totalPages) { // 可以显示到最后一页的页码了，不出现最后的省略
				max = totalPages;
				hasEnd = false;
			}
			
			if(curPage < initDisplayPageNumber + mid ){ // 若当前页码小于初始页码加上中间值，不出现中间的省略
				for(int i = mid+1; i <= max; i++) {
					pageNumbers.addLast(new PageNumber(i, i == curPage, false));
				}
			} else {	// 若当前页码大于初始页码加上中间值，出现中间省略
				pageNumbers.addLast(new PageNumber(this.previousPage, false, true));			
				for(int i = (int) (curPage - mid); i <= max; i++) {
					pageNumbers.addLast(new PageNumber(i, i == curPage, false));
				}
			}
			
			if(hasEnd) {
				pageNumbers.addLast(new PageNumber(this.nextPage, false, true));
			}
			
		}

	}

	public class PageNumber {
		private long number;
		private boolean isSelect;
		private boolean isBlank;

		public boolean isBlank() {
			return isBlank;
		}

		public void setBlank(boolean isBlank) {
			this.isBlank = isBlank;
		}

		public long getNumber() {
			return number;
		}

		public void setNumber(long number) {
			this.number = number;
		}

		public boolean isSelect() {
			return isSelect;
		}

		public void setSelect(boolean isSelect) {
			this.isSelect = isSelect;
		}

		public PageNumber() {
			// TODO Auto-generated constructor stub
		}

		public PageNumber(long number, boolean isSelect, boolean isBlank) {
			super();
			this.number = number;
			this.isSelect = isSelect;
			this.isBlank = isBlank;
		}	
	}
	
	
	public static void main(String[] args) {
		/*long maxRow = 35;
		PageController pc = new PageController(maxRow, 1, 5);
		System.out.println("TotalPages==>" + pc.getTotalPages());
		System.out.println("CurrentPage==>" + pc.getCurrentPage());
		
		for(int i = 1; i <= pc.getTotalPages(); i++) {
			pc.setPageController(maxRow, i);
			pc.generatePageNumber(5);
			
			LinkedList<PageNumber> numbers = pc.getPageNumbers();
			for(PageNumber number : numbers) {
				if(number.isBlank) {
					System.out.print("... ");
				} else if(number.isSelect) {
					System.out.print(number.getNumber() + "(sel) ");
				} else {
					System.out.print(number.getNumber() + " ");
				}
			}		
			
			System.out.println();
			
		}*/
		char[] str = "da167952b02ec442eef19fa13abea5b1".toCharArray();
		ArrayUtils.reverse(str);
		System.out.println(new String(str));
		
	}
}
