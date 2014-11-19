/**
 * 
 */
package group;

/****
 * @author Emily
 *
 ****/
public class Controller {
		
	private GroupCreationSystem sys;
	
	public Controller(){
		sys=new GroupCreationSystem();
	}
	public void setCourseNumber(String num){
		sys.setCourseNumber(num);
	}
	public String getCourseNumber(){
		return sys.getCourseNumber();
	}
	public void setGroupSize(int size){
		sys.setGroupSize(size);
	}
	public int getGroupSize(){
		return sys.getGroupSize();
	}
	public void setDeadline(double deadline){
		sys.setDeadline(deadline);
	}
	public double getDeadline(){
		return sys.getDeadline();
	}
	public void setInstructor(String ins){
		sys.setInstructor(ins);
	}
	public String getInstructor(){
		return sys.getInstructor();
	}
	public void finalizeParameters(){
	}
	
}
