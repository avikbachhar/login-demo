package awt.student;

public class Mymethods {
	public void Frame1(String name,String college,String DEPT,String username,String password){
		Framenext f = new Framenext(name,college,DEPT,username,password);
	} 
	public void registrationFrame(){
		RegFrame rf = new RegFrame();
	}
	public void editableFrame(String username,String password) {
		editFrame ef = new editFrame(username,password);
	}
}
