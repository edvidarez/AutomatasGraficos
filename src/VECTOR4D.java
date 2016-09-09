import java.io.Serializable;

public class VECTOR4D implements Serializable{

	private static final long serialVersionUID = 1L;
	public float v[]=new float[4];
	public VECTOR4D()
	{
		
	}
	public VECTOR4D(float x,float y,float z, float w)
	{
		v[0]=x;
		v[1]=y;
		v[2]=z;
		v[3]=w;
	}
	
	public static float dot(VECTOR4D x, VECTOR4D y)
	{
		float r=0;
		int i;
		for(i=0;i<4;i++)
			r+=x.v[i]*y.v[i];
		return r;
	}
	public static VECTOR4D Mul(MATRIX4D M,VECTOR4D V)
	{
		VECTOR4D R = new VECTOR4D();
		int i,j;
		for(i=0;i<4;i++)
		{
			for(j=0;j<4;j++)
			{
				R.v[i]+=V.v[j]*M.M4D[i][j];
			}
		}
		//System.out.println("mul");
		return R;
	}
}
