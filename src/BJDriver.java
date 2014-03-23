
public class BJDriver {

	public static void main(String[] args) {
		BJView v1 = new BJView();
		v1.setVisible(true);
		BJController c1 = new BJController(v1);
	}
}
