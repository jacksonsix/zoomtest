package graphattrib;

public class Listener implements IListener{
	
	public  void  process(IEvent evt) {
		
		switch(evt.getName()) {
		case "enterNode":
			System.out.println("process "+evt.getName() + " " + evt.getValue());
			break;
		case "leaveNode":
			System.out.println("process "+evt.getName() + " " + evt.getValue());
			break;
		case "enterPath":
			System.out.println("process "+evt.getName() + " " + evt.getValue());
			break;
		case "leavePath":
			System.out.println("process "+evt.getName() + " " + evt.getValue());
			break;
		default:
			    System.out.println("Evt not defined");
				break;
				
			
		}
	}

}
