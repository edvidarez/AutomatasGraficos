import java.awt.*;
import java.io.Serializable;
public class Shape implements Serializable{

	private static final long serialVersionUID = -221835367838094699L;
	public int x0,y0;
	public float alfa=0.0f;
	public boolean isActive=false;
	public void Draw(Graphics c,boolean isActive,boolean rotation){}
	public void Move(int x, int y){}
	public boolean HitTest(int x, int y)
	{return false;}
	public void Resize(int n){};
	public void Resize(int x,int y){};
        public String type = "";
}
