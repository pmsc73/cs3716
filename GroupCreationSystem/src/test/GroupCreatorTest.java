package test;

import java.util.ArrayList;

import group.ProjectGroups;

public class GroupCreatorTest {
	public static void main(String[] args){
		testMemberDivision(29,5);
		testMemberDivision(28,5);
		testMemberDivision(26,5);
	}
	public static void testMemberDivision(int total, int groupsize){
		System.out.println("Dividing "+ total+" members into groups of " +groupsize);
		ProjectGroups x=new ProjectGroups();
		ArrayList<Integer> ints;
		ints=(ArrayList<Integer>) x.calculateGroupSizes(total, groupsize);
		int total2=0;
		for(int i=0;i<ints.size();i++){
			System.out.println("Group "+i+" has "+ ints.get(i)+ " members!");
			total2+=ints.get(i);
		}
		System.out.println("Adds up to:"+total2);
	}
}
