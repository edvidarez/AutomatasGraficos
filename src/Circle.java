import java.awt.Color;
import java.awt.Graphics;
public class Circle extends Shape {
private static final long serialVersionUID = 1L;
public int r;
public String label;
Circle()
{
   type = "circle";
}

public void Draw(Graphics c,boolean isActive,boolean onRotation){
	 
	if(isActive==true)
	{
		c.setColor(Color.blue);
		c.fillOval(x0-5, y0-5, 10, 10);
	}
	else
		c.setColor(Color.black);
	c.drawOval(x0-r, y0-r, 2*r, 2*r);
        c.drawString(label, x0-7, y0+6);
}
public void Move(int x, int y){
	x0=x;
	y0=y;
}
public boolean HitTest(int x, int y)
{
	x-=x0;
	y-=y0;
	if(x*x+y*y <r*r)
		return true;
	return false;
}

public void Resize(int x,int y)
{
	r=(int)Math.sqrt(Math.pow((x0-x), 2)+Math.pow((y0-y), 2));
}
}
