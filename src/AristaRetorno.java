/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Edmundo
 */
import java.awt.*;
public class AristaRetorno extends Shape {
private static final long serialVersionUID = 1L;
public int x1;
public int y1;
public Shape ini;
public Shape fin;
public int r =25;
public String a = "String";
AristaRetorno(Shape ini,String a)
{   
    this.ini = ini;
    x0=this.ini.x0;
    y0= this.ini.y0-50;
    this.a=a;
   
    
}
 private void drawArrowLine(Graphics g, int x1, int y1, int x2, int y2, int d, int h){
        int dx = x2 - x1, dy = y2 - y1;
        double D = Math.sqrt(dx*dx + dy*dy);
        double xm = D - d, xn = xm, ym = h, yn = -h, x;
        double sin = dy/D, cos = dx/D;

        x = xm*cos - ym*sin + x1;
        ym = xm*sin + ym*cos + y1;
        xm = x;

        x = xn*cos - yn*sin + x1;
        yn = xn*sin + yn*cos + y1;
        xn = x;

        int[] xpoints = {x2, (int) xm, (int) xn};
        int[] ypoints = {y2, (int) ym, (int) yn};

        g.drawLine(x1, y1, x2, y2);
        g.fillPolygon(xpoints, ypoints, 3);
     }
public void Draw(Graphics c,boolean isActive,boolean onRotation,int caminos_totales){
	
	if(isActive==true)
	{
		c.setColor(Color.blue);
		c.fillOval(x0-3, y0-3, 6, 6);
	}
	else
		c.setColor(Color.black);
        
          c.drawArc(x0-r, y0-r,50, 50, 0, 180);
          
        double dy =((double)this.y0-(double)this.ini.y0);
        double dx =( (double)this.ini.x0-(double)(this.x0+r));
        double D = Math.sqrt(dx*dx + dy*dy);
        double ang = Math.atan(dy/dx);        
      //  System.out.println(this.ini.x0+"nuevo "+(this.ini.x0+Math.cos(ang)*25));
        // double sin = dy/D, cos = dx/D;
        int difx = (int)(Math.cos(ang)*25);
        int dify = (int)(Math.sin(ang)*25);
        System.out.println(ang);
        int direcx,direcy ;
        direcx=direcy=1;
        if(this.ini.x0<x0+r)
        {
            direcx = 1;
        }
        else
        {
            direcx= -1;
        }
          c.drawLine(x0+r,y0,ini.x0+difx*direcx,ini.y0-dify*direcx);
          drawArrowLine(c,x0-r,y0,ini.x0-difx*direcx,ini.y0-dify*direcx,15,5);
          
        
        c.drawString(a, x0-(2*a.length()), y0-26);
       }
public void Move(int x, int y){
	x0=x;
	y0=y;
}
public boolean HitTest(int x, int y)
{
	x-=x0;
	y-=y0;
	if(x*x+y*y <25*25)
		return true;
	return false;
}
}
