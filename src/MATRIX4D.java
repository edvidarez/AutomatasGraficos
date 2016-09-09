
public class MATRIX4D {
    public float M4D[][] ;//= new float [4][4]; 
    static float M1[][]= new float [3][3];
    static float M2[][]= new float [3][3];
    static float M3[][]= new float [3][3];
    static float M4[][]= new float [3][3];
	
    static float M5[][]= new float [3][3];
    static float M6[][]= new float [3][3];
    static float M7[][]= new float [3][3];
    static float M8[][]= new float [3][3];

    static float M9[][]= new float [3][3];
    static float M10[][]= new float [3][3]; 
    static float M11[][]= new float [3][3];
    static float M12[][]= new float [3][3];
	
    static float M13[][]= new float [3][3];
    static float M14[][]= new float [3][3];
    static float M15[][]= new float [3][3];
    static float M16[][]= new float [3][3];
    
 
    public MATRIX4D()
    {
    	M4D= new float [4][4];
    }
    public MATRIX4D(MATRIX4D M)
    {
     	
    }
    public MATRIX4D(float x00,float x01,float x02,float x03,
    				float x10,float x11,float x12,float x13,
    				float x20,float x21,float x22,float x23,
    				float x30,float x31,float x32,float x33)
    {
    	M4D= new float [4][4];
    	M4D[0][0]=x00;
    	M4D[0][1]=x01;
    	M4D[0][2]=x02;
    	M4D[0][3]=x03;
    	
    	M4D[1][0]=x10;
    	M4D[1][1]=x11;
    	M4D[1][2]=x12;
    	M4D[1][3]=x13;
    	
    	M4D[2][0]=x20;
    	M4D[2][1]=x21;
    	M4D[2][2]=x22;
    	M4D[2][3]=x23;
    	
    	M4D[3][0]=x30;
    	M4D[3][1]=x31;
    	M4D[3][2]=x32;
    	M4D[3][3]=x33;
    }
    static MATRIX4D Identity()
    {
        MATRIX4D M = new MATRIX4D();
        int i,j;
        for(i=0;i<4;i++)
        {
            for(j=0;j<4;j++)
            {
                M.M4D[i][j]=0;
                if(i==j)
                    M.M4D[i][j]=1;
            }
        }
        return M;
    }
    public static  MATRIX4D Zero()
    {
        MATRIX4D M = new MATRIX4D();
        int i,j;
        for(i=0;i<4;i++)
        {
            for(j=0;j<4;j++)
            { 
                M.M4D[i][j]=0f;
            }
        }
        return M;
    }
    static MATRIX4D RotationX(float theta)
    {
        MATRIX4D M =  MATRIX4D.Zero();
        M.M4D[0][0]=M.M4D[3][3]=1;
        M.M4D[1][1]=M.M4D[2][2]=(float) Math.cos(theta);
        M.M4D[1][2]= (float)-Math.sin(theta);
        M.M4D[2][1]= (float)Math.sin(theta);
        
        return M;
    }
    static MATRIX4D RotationY(float theta)
    {
        MATRIX4D M =  MATRIX4D.Zero();
        M.M4D[1][1]=M.M4D[3][3]=1;
        M.M4D[0][0]=M.M4D[2][2]=(float) Math.cos(theta);
        M.M4D[2][0]= (float)-Math.sin(theta);
        M.M4D[0][2]= (float)Math.sin(theta);
        return M;
    }
    static MATRIX4D RotationZ(float theta)
    {
        MATRIX4D M = MATRIX4D.Identity();
        M.M4D[0][0]=M.M4D[1][1]=(float) Math.cos(theta);
        M.M4D[0][1]= (float)-Math.sin(theta);
        M.M4D[1][0]= (float)Math.sin(theta);
        return M;
    }
    static MATRIX4D Transpose(MATRIX4D M)
    {
        MATRIX4D MM = new MATRIX4D();
        int i,j;
        for(i=0;i<4;i++)
        {
            for(j=0;j<4;j++)
            {
                MM.M4D[j][i]=M.M4D[i][j];
            }
        }
        return MM;
    }
   static MATRIX4D Adj(MATRIX4D M)
    {
    	MATRIX4D MM = new MATRIX4D();
    	MM.M4D[0][0]=Det3x3(M1);
    	MM.M4D[0][1]=-Det3x3(M5);
    	MM.M4D[0][2]=Det3x3(M9);
    	MM.M4D[0][3]=-Det3x3(M13);
    	
    	MM.M4D[1][0]=-Det3x3(M2);
    	MM.M4D[1][1]=Det3x3(M6);
    	MM.M4D[1][2]=-Det3x3(M10);
    	MM.M4D[1][3]=Det3x3(M14);
    	
    	MM.M4D[2][0]=Det3x3(M3);
    	MM.M4D[2][1]=-Det3x3(M7);
    	MM.M4D[2][2]=Det3x3(M11);
    	MM.M4D[2][3]=-Det3x3(M15);
    	
    	MM.M4D[3][0]=-Det3x3(M4);
    	MM.M4D[3][1]=Det3x3(M8);
    	MM.M4D[3][2]=-Det3x3(M12);
    	MM.M4D[3][3]=Det3x3(M16);
    	return MM;
    }
    public static void Print(MATRIX4D M)
    {
    	int i,j,n=4;
    	for(i=0;i<n;i++)
    	{
    		for(j=0;j<n;j++)
    		{
    			System.out.print(M.M4D[i][j]+" ");
    		}
    		System.out.println();
    	}
    }
    static MATRIX4D Inverse(MATRIX4D M)
    {
    	return Mul(Transpose(Adj(M)),1.0f/Det(M));
    } 
    
    static MATRIX4D Scaling(float sx, float sy,float sz)
    {
        MATRIX4D M = MATRIX4D.Zero();
        M.M4D[0][0]=sx;
        M.M4D[1][1]=sy;
        M.M4D[2][2]=sz;
        return M;
    }
    
    static MATRIX4D Translation(float tx,float ty,float tz)
    {
        MATRIX4D M = MATRIX4D.Identity();
        M.M4D[0][3]=tx;
        M.M4D[1][3]=ty;
        M.M4D[2][3]=tz;
        return M;
    }
    static MATRIX4D Sum(MATRIX4D A,MATRIX4D B)
    {
    	MATRIX4D M = new MATRIX4D();
    	int i,j;
    	for(i=0;i<4;i++)
    	{
    		for(j=0;j<4;j++)
    		{
    			M.M4D[i][j]=A.M4D[i][j]+B.M4D[i][j];
    		}
    	}
    	return M;
    }
    static float Det3x3(float a[][])
    {
    	
    	float x1=1,x2=1;
    	int i;
    	for(i=0;i<3;i++)
    	{
    		x1+=(a[i][0]	*	a[(i+1)%3]  [1]		 *	a[(i+2)%3]  [2]);
    		x2+=(a[2-i][0]	*	a[2-(i+1)%3]  [1]		 *	a[2-(i+2)%3]  [2]);
    	}
    	return x1-x2;
    }
     static float Det(MATRIX4D M)
    {
    	float det=0;
    	int n=4,i,j,k,p;
    	
    	
    	int r,q;
    	for(k=0;k<n;k++)
    	{
    		
    		for(p=0;p<n;p++)
    		{
    			r=0;
		    	for(i=0;i<n;i++)
		    	{
		    		q=0;
		    		if(i==k)
		    			continue;
		    		for(j=0;j<n;j++)
		    		{
		    			if(j==p)
		    				continue;
		    			if(k==0 && p==0)
		    				M1[r][q]=M.M4D[i][j];
		    			if(k==1 && p==0)
		    				M2[r][q]=M.M4D[i][j];
		    			if(k==2 && p==0)
		    				M3[r][q]=M.M4D[i][j];
		    			if(k==3 && p==0)
		    				M4[r][q]=M.M4D[i][j];
		    			
		    			if(k==0 && p==1)
		    				M5[r][q]=M.M4D[i][j];
		    			if(k==1 && p==1)
		    				M6[r][q]=M.M4D[i][j];
		    			if(k==2 && p==1)
		    				M7[r][q]=M.M4D[i][j];
		    			if(k==3 && p==1)
		    				M8[r][q]=M.M4D[i][j];
		    			
		    			if(k==0 && p==2)
		    				M9[r][q]=M.M4D[i][j];
		    			if(k==1 && p==2)
		    				M10[r][q]=M.M4D[i][j];
		    			if(k==2 && p==2)
		    				M11[r][q]=M.M4D[i][j];
		    			if(k==3 && p==2)
		    				M12[r][q]=M.M4D[i][j];
		    			
		    			if(k==0 && p==3)
		    				M13[r][q]=M.M4D[i][j];
		    			if(k==1 && p==3)
		    				M14[r][q]=M.M4D[i][j];
		    			if(k==2 && p==3)
		    				M15[r][q]=M.M4D[i][j];
		    			if(k==3 && p==3)
		    				M16[r][q]=M.M4D[i][j];
		    			
		    			q++;
		    		}
		    		r++;
		    	}
		    	
    		}
    	}
    	det=M.M4D[0][0]*Det3x3(M1)-M.M4D[1][0]*Det3x3(M2)+M.M4D[2][0]*Det3x3(M3)-M.M4D[3][0]*Det3x3(M4);
    	return det;
    }
    static MATRIX4D Mul(MATRIX4D A,MATRIX4D B)
    {
        MATRIX4D M= new MATRIX4D();
        int i,j;
        for(i=0;i<4;i++)
        {
        	for(j=0;j<4;j++)
        	{
        		for(int k=0;k<4;k++)
        		{
        			M.M4D[i][j]+=A.M4D[i][k]*B.M4D[k][j];
        		}
        	}
        }
        return M;
    }
    static MATRIX4D Mul(MATRIX4D A,float x)
    {
    	MATRIX4D M = new MATRIX4D();
    	int i,j;
    	for(i=0;i<4;i++)
    		for(j=0;j<4;j++)
    		{
    			M.M4D[i][j]=A.M4D[i][j]*x;
    		}
    	return M;
    }
    
}