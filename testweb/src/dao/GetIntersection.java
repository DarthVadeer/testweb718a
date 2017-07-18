package dao;

import java.util.List;

public class GetIntersection {
	
	//多个list去取交集
	public List getIntersection(List l1,List l2,List l3, List l4){
		
		l1.retainAll(l2);
		l1.retainAll(l3);
		l1.retainAll(l4);
		
		return l1;
		
	}

}
