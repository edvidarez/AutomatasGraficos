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
import java.awt.geom.AffineTransform;
public class Arista extends Shape {
private static final long serialVersionUID = 1L;
public int x1;
public int y1;
public Shape ini;
public Shape fin;
public  String a = "String";
Arista(Shape ini,Shape fin,String a)
{   
    this.ini = ini;
    this.fin = fin;
    x0=this.ini.x0;
    y0= this.ini.y0;
    x1= this.fin.x0;
    y1=this.fin.y0;
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
public void Draw(Graphics c,boolean isActive,boolean onRotation){
	 x0=this.ini.x0;
        y0= this.ini.y0;
        x1= this.fin.x0;
        y1=this.fin.y0;
	if(isActive==true)
	{
		c.setColor(Color.blue);
		
	}
	else
		c.setColor(Color.black);
        
       // c.drawArc(x0,  y0, (int)Math.sqrt((x0*x0)+(x1*x1)),  (int)Math.sqrt((y0*y0)+(y1*y1)),  45,  45);
     //  c.drawArc(x0, y0, 400, 150, 0, 180);
  //    c.setColor(Color.cyan);
 //   c.fillArc(x1, y1, 150, 100, 0, 180);
  //  c.setColor(Color.black);
  //  c.drawArc(x0, y1, 150, (int) Math.sqrt((y1-y0)*(y1-y0)), 0, -180  );
  
        double dy =((double)this.fin.y0-(double)this.ini.y0);
        double dx =( (double)this.fin.x0-(double)this.ini.x0);
                double D = Math.sqrt(dx*dx + dy*dy);
        double ang = Math.atan(dy/dx);        
      //  System.out.println(this.ini.x0+"nuevo "+(this.ini.x0+Math.cos(ang)*25));
        // double sin = dy/D, cos = dx/D;
        int difx = (int)(Math.cos(ang)*25);
        int dify = (int)(Math.sin(ang)*25);
        System.out.println(ang);
        int direcx,direcy ;
        direcx=direcy=1;
        if(this.ini.x0<this.fin.x0)
        {
            direcx = 1;
        }
        else
        {
            direcx= -1;
        }
//        if(this.ini.y0<this.fin.y0)
//        {
//            direcy = -1;
//        }
//        else
//        {
//            direcy= 1;
//        }

      //  c.drawArc(x0-25, y0-75,50, 50, 0, 180);
	//c.drawLine(this.ini.x0+difx*direcx,this.ini.y0+dify*direcx,this.fin.x0-difx*direcx,this.fin.y0-dify*direcx);
        drawArrowLine(c, this.ini.x0+difx*direcx, this.ini.y0+dify*direcx, this.fin.x0-difx*direcx, this.fin.y0-dify*direcx, 15, 5);
       
        c.drawString(a, this.fin.x0-difx*direcx-(10*a.length()*direcx)-10, this.fin.y0-dify*direcx-5);
        
//        Graphics2D g2d = (Graphics2D) c;
// 
//        AffineTransform at = new AffineTransform();
//        at.rotate(Math.PI / 2);
//        g2d.setTransform(at);
//        g2d.drawString("Hello World", -200, 50);
//        g2d.setTransform(at);
//        g2d.drawString("O", 400, 400);

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
            
	if(r<5){
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
//public void Move(int x,int y){
//	int dx,dy;
//	dx=x1-x0;
//	dy=y1-y0;
//	x0=x;
//	x1=x0+dx;
//	y0=y;
//	y1=y0+dy;
////}
//public boolean HitTest(int x,int y)
//{
//	int dx=x1-x0;
//	int dy=y1-y0;
//	int adx=Math.abs(dx);
//	int ady=Math.abs(dy);
//	x-=x0;
//	y-=y0;
//	double nx,ny;
//            nx=dx/Math.sqrt(dx*dx+dy*dy);
//            ny=dy/Math.sqrt(dx*dx+dy*dy);
//            double p=nx*x+ny*y;
//            double px,py;
//            px=nx*p;
//            py=ny*p;
//            double rx=x-px;
//            double ry=y-py;
//            double r=Math.sqrt(rx*rx+ry*ry);
//            
//	if(r<10){
//                double u;
//                if(adx>ady){
//                    u=px/(double)dx;
//                }
//                else{
//                    u=py/(double)dy;
//                }
//                if(u>=0&&u<=1){
//                    return true;
//                }
//            }
//	return false;
//}
//public void Resize(int x,int y)
//{
//	x1=x;
//	y1=y;
//}
}
