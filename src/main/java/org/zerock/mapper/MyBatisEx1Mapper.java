package org.zerock.mapper;

import org.zerock.domain.Ex1Critetia;

public interface MyBatisEx1Mapper {

	public int select1(Ex1Critetia cri);
	
	public int select2(Ex1Critetia cri);
	
	public int select3(Ex1Critetia cri);
	
	public int select4(Ex1Critetia cri);
	
	public int select5(Ex1Critetia cri);
	
	// forEach -list
	public int select6(Ex1Critetia cri);
	
	//forEach - map
	public int select7(Ex1Critetia cri);
	
	// trim suffix
	public int select8(Ex1Critetia cri);
	
	// trim suffixOverrides, 마지막 and 생략~~
	public int select9(Ex1Critetia cri);
}
