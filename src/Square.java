import java.awt.Color;
import java.awt.Graphics;

public class Square extends Shape {
	private static final long serialVersionUID = 1L;
	VECTOR4D Rec[]= new VECTOR4D[4];
	VECTOR4D Rect[]= new VECTOR4D[4];
	public int x1;
	public int y1;
	int mx,my;
	int Mx,My;

	void menymax()
	{
		System.out.println(x0 +" = "+ (int)Rect[2].v[0]);
		System.out.println(x1 +" = "+ (int)Rect[1].v[0]);
		if((int)Rect[2].v[0]>=(int)Rect[1].v[0])
		{
			mx=(int)Rect[1].v[0];
			Mx=(int)Rect[2].v[0];
		}
		else
		{
			mx=(int)Rect[2].v[0];
			Mx=(int)Rect[1].v[0];
		}	
		if((int)Rect[0].v[1]>=(int) Rect[1].v[1])
		{
			my=(int) Rect[1].v[1];
			My=(int)Rect[0].v[1];
		}
		else
		{
			my=(int)Rect[0].v[1];
			My=(int) Rect[1].v[1];
		}
	}
	public void Draw(Graphics c,boolean isActive,boolean onRotation)
	{

		Rec[0]= new VECTOR4D(1,1,0,1);
		Rec[1]= new VECTOR4D(1,-1,0,1);
		Rec[2]= new VECTOR4D(-1,-1,0,1);
		Rec[3]= new VECTOR4D(-1,1,0,1);
		
		MATRIX4D S= MATRIX4D.Scaling((x1-x0)/2, (y1-y0)/2, 0.0f);
		MATRIX4D T= MATRIX4D.Translation(x0+(x1-x0)/2, y0+(y1-y0)/2, 0);
		//MATRIX4D R=MATRIX4D.RotationY(s_fTime);
		MATRIX4D C=S;
		MATRIX4D Rz = MATRIX4D.RotationZ(alfa);
		if(onRotation)
		{
			Rz = MATRIX4D.RotationZ(alfa+=0.001f);
			
		}
		C=MATRIX4D.Mul(S, Rz);
		//System.out.println();
		//C=MATRIX4D.Mul(C, Rz);
		C=MATRIX4D.Sum(C, T);
		
		int i;
		for(i=0;i<4;i++)
		{
			Rect[i]=VECTOR4D.Mul(C,Rec[i]);
		}
		if(isActive==true)
		{
			c.setColor(Color.blue);
			c.fillOval(x0+(x1-x0)/2  -3, y0+(y1-y0)/2  -3, 6, 6);
			c.fillOval((int)Rect[2].v[0] -3,(int)Rect[2].v[1] -3, 6, 6);
		}
		else
			c.setColor(Color.black);
		
	
		
		
		c.drawLine((int)Rect[0].v[0],(int)Rect[0].v[1],(int)Rect[1].v[0],(int) Rect[1].v[1]);
		//System.out.println("("+Rect[0].v[0]+","+Rect[0].v[1]+") , ("+Rect[1].v[0]+","+Rect[1].v[1]+")");
		c.drawLine((int)Rect[1].v[0],(int)Rect[1].v[1],(int)Rect[2].v[0],(int) Rect[2].v[1]);
		c.drawLine((int)Rect[2].v[0],(int)Rect[2].v[1],(int)Rect[3].v[0],(int) Rect[3].v[1]);
		c.drawLine((int)Rect[3].v[0],(int)Rect[3].v[1],(int)Rect[0].v[0],(int) Rect[0].v[1]);
		
	}
	public void Move(int x, int y)
	{
		int dx,dy;
		dx=x1-x0;
		dy=y1-y0;
		x0=x-dx/2;
		x1=x0+dx;
		y0=y-dy/2;
		y1=y0+dy;
	}
	public boolean HitTest(int x, int y)
	{
		menymax();
		if(mx<=x && x<=Mx && my<=y && y<=My)
			return true;
		return false;
	}

	public void Resize(int x,int y)
	{
		x1=x;
		y1=y;
	}
}
