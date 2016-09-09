
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SketchPadFrame Pad=new SketchPadFrame();
		MATRIX4D a= new MATRIX4D(1,-2,-3,-1,0,1,2,0,0,2,3,1,3,-2,0,1);
		System.out.println(MATRIX4D.Det(a));
		MATRIX4D.Print(a);
		MATRIX4D.Print(MATRIX4D.Inverse(a));
		MATRIX4D.Print(MATRIX4D.Mul( MATRIX4D.Inverse(a),a));
		System.out.println(VECTOR4D.dot(new VECTOR4D(11,1,1,1  ), new VECTOR4D(50/12.0f,20,30,30)));
		
		Pad.show();
		
	}

}
