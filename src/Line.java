import java.awt.*;
public class Line extends Shape {
	private static final long serialVersionUID = 1L;
public int x1;
public int y1;

public void Draw(Graphics c,boolean isActive,boolean onRotation){
	
	if(isActive==true)
	{
		c.setColor(Color.blue);
		c.fillOval(x0-3, y0-3, 6, 6);
	}
	else
		c.setColor(Color.black);
        
       // c.drawArc(x0,  y0, (int)Math.sqrt((x0*x0)+(x1*x1)),  (int)Math.sqrt((y0*y0)+(y1*y1)),  45,  45);
     //  c.drawArc(x0, y0, 400, 150, 0, 180);
  //    c.setColor(Color.cyan);
 //   c.fillArc(x1, y1, 150, 100, 0, 180);
  //  c.setColor(Color.black);
  //  c.drawArc(x0, y1, 150, (int) Math.sqrt((y1-y0)*(y1-y0)), 0, -180  );
	c.drawLine(x0,y0,x1,y1);
      //  System.out.println("c.drawArc("+x0+","+  y0 +","+ 100+","+  (y1-y0)+","+  45 +","+  45 +");");
      System.out.println("y: "+ (int)Math.sqrt((y0*y0)+(y1*y1)));
      System.out.println("y0:"+y0+"   y1:"+y1);
}
public void Move(int x,int y){
	int dx,dy;
	dx=x1-x0;
	dy=y1-y0;
	x0=x;
	x1=x0+dx;
	y0=y;
	y1=y0+dy;
}
public boolean HitTest(int x,int y)
{
	int dx=x1-x0;
	int dy=y1-y0;
	int adx=Math.abs(dx);
	int ady=Math.abs(dy);
	x-=x0;
	y-=y0;
	double nx,ny;
            nx=dx/Math.sqrt(dx*dx+dy*dy);
            ny=dy/Math.sqrt(dx*dx+dy*dy);
            double p=nx*x+ny*y;
            double px,py;
            px=nx*p;
            py=ny*p;
            double rx=x-px;
            double ry=y-py;
            double r=Math.sqrt(rx*rx+ry*ry);
            
	if(r<10){
                double u;
                if(adx>ady){
                    u=px/(double)dx;
                }
                else{
                    u=py/(double)dy;
                }
                if(u>=0&&u<=1){
                    return true;
                }
            }
	return false;
}
public void Resize(int x,int y)
{
	x1=x;
	y1=y;
}
}
