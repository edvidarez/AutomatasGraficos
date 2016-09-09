/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
/**
 *
 * @author Edmundo
 */
public class Automatas extends JFrame
implements MouseListener, MouseMotionListener,KeyListener{
	private static final long serialVersionUID = 6446311509618417211L;
	ArrayList<Shape> Document;
	Shape ActiveShape = null; 
	boolean bDragging=false;
	Shape Dragging;
	int lastX;
	int lastY;
	int Last2x;
	int Last2y;
        int count_circles=1;
	String File;
	boolean onRotation=false,logo=false,esperandoClick=false;
	static String archivo="Guardado";
	 FileInputStream FileInS;
     FileOutputStream FileOptS;
     ObjectInputStream ObjectInS;            
     ObjectOutputStream ObjectOptS;
	public Automatas()
	{
	setTitle("The SketchPad 2D");
	setSize(1280,720);
	addMouseListener(this);
	addMouseMotionListener(this);
	addKeyListener(this);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	Document= new ArrayList<Shape>();
	
	

	}

	
public void paint(Graphics g){
	super.paint(g);
	Dimension height = getSize();
	if(logo)
	{
	ImageIcon img= new ImageIcon(getClass().getResource("/Images/edvid.png"));
	
	g.drawImage(img.getImage(), 0, 0, height.width, height.height, null);
	}
	for(Shape S:Document){
		S.Draw(g,S.isActive,onRotation);
	}
	if(onRotation)
		repaint();
}

	public void mouseDragged(MouseEvent e) {
		if(bDragging){
			if(ActiveShape!=null)
			ActiveShape.isActive=false;
			ActiveShape=Dragging;
			ActiveShape.isActive=true;
			Dragging.Move(e.getX(), e.getY());
			repaint();	
		}
		
	}

	public void mouseMoved(MouseEvent e) {
		lastX=e.getX();
		lastY=e.getY();
	}

	public void mouseClicked(MouseEvent e) {
		int i=0;
		boolean flag=false;
		for(Shape S:Document){
			i++;
			if(S.HitTest(e.getX(), e.getY())){
                            
                        if(esperandoClick)
                        {
                            System.out.println("linea entre estados");
                            esperandoClick = false;
                            Line l=new Line();
                            l.x0=ActiveShape.x0;
                            l.y0=ActiveShape.y0;
                            l.x1=S.x0;
                            l.y1=S.y0;
                            if(ActiveShape!=null)
				ActiveShape.isActive=false;
                            ActiveShape=null;
                             Document.add(l);
                            continue;
                        }
                            
				if(ActiveShape!=null)
				ActiveShape.isActive=false;
				ActiveShape= S;
				ActiveShape.isActive=true;
				System.out.println(i);
				repaint();
				flag=true;
			}
		}
		if(!flag)
		{
		if(ActiveShape!=null)
		ActiveShape.isActive=false;
		ActiveShape=null;
		repaint();
		}
	}

	public void mouseEntered(MouseEvent arg0) {
		
	}

	public void mouseExited(MouseEvent arg0) {
		
	}

	public void mousePressed(MouseEvent e) {
		System.out.println("cllick");
		for(Shape S:Document){
			if(S.HitTest(e.getX(), e.getY())){
				bDragging=true;
				Dragging=S;
			}
		}
                
	}

	public void mouseReleased(MouseEvent e) {
		bDragging=false;
		Dragging=null;
		Last2x=e.getX();
                Last2y=e.getY();
                logo=false;
//                Circle c=new Circle();
//			c.x0=Last2x;
//			c.y0=Last2y;
//			c.r=(int)Math.sqrt(Math.pow((25), 2)+Math.pow((25), 2));
//			Document.add(c);
//                        repaint();
	}
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode());
		switch(e.getKeyCode())
		{
		case 83: // s
	          if( JOptionPane.showConfirmDialog(rootPane, "�Desea guardar este documento?")==JOptionPane.OK_OPTION)
	          {
	        	  
	               File=JOptionPane.showInputDialog("ponle un nombre:");
	               System.out.println(File);
			try {
			if(File!=null)
			{
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(File));
				for(Shape S:Document){
                    
                    try
                    {
                    oos.writeObject(S);
                    }
                    catch(Exception e1)
                    {
                    	System.out.println(e1);
                    }
                    System.out.println("se escribio: "+S);
                   
				}
				 oos.close();
			}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	          }     
	               
	                
			break;
		case 79: //o
		
            try{
            
                    File = JOptionPane.showInputDialog("Nombre de archivo");
             ObjectInputStream ois = new ObjectInputStream(new FileInputStream(File));
             Object aux = ois.readObject();
             
		          // Mientras haya objetos
		          while (aux!=null)
		          {
		              if (aux instanceof Shape)
		              {
		                  System.out.println(aux);  // Se escribe en pantalla el objeto
		                  Document.add((Shape)aux);
		              }
		              aux = ois.readObject();
		          }
          ois.close();
            }
            catch(Exception E){
                E.toString();
            }
			break;
		case 127: //supr
			
			for(Shape S:Document)
			{
				if(S.isActive==true)
				{
					System.out.println(Document.size());
					Document.remove(S);
					System.out.println(Document.size());
					ActiveShape=null;
					repaint();
					break;
				}
			}
			break;
		case 27:  //esc
			if(JOptionPane.showConfirmDialog(rootPane, "�Quieres borrar todo?")==JOptionPane.OK_OPTION)
			{
				Document.clear();
				
				break;
			}
		case 71:  // g
			if(onRotation)
			onRotation=false;
			else
			onRotation=true;
			break;
		case 40:  // ABAJO
		{
			if(ActiveShape!=null)
			{
			ActiveShape.Move(ActiveShape.x0,ActiveShape.y0+2 );
			System.out.println(ActiveShape.x0+","+ActiveShape.y0+"S");
			}
			break;
		}
		case 37: //DERECHA
		{
			if(ActiveShape!=null)
			{
			ActiveShape.Move(ActiveShape.x0-2,ActiveShape.y0);
			System.out.println(ActiveShape.x0+","+ActiveShape.y0+"D");
			}
			break;
		}
		case 38:  //ARRIBA
		{
			if(ActiveShape!=null)
			{
			ActiveShape.Move(ActiveShape.x0,ActiveShape.y0-2 );
			System.out.println(ActiveShape.x0+","+ActiveShape.y0+"W");
			}
			break;
		}	
		case 32:// ESPACIO
		{
			ActiveShape.alfa=0.0f;
			repaint();
			break;
		}
		case 39:
		{
			if(ActiveShape!=null)
			{
			ActiveShape.Move(ActiveShape.x0+2,ActiveShape.y0 );
			System.out.println(ActiveShape.x0+","+ActiveShape.y0+"D");
			}
			break;
		}
		
			
			
		}
		repaint();
	}
	public void keyReleased(KeyEvent arg0) {
		
	}
	public void keyTyped(KeyEvent e) {
		switch(e.getKeyChar()){
		case 'C':
		case 'c':{
			Circle c=new Circle();
			c.x0=Last2x;
			c.y0=Last2y;
			//c.r=(int)Math.sqrt(Math.pow((lastX-Last2x), 2)+Math.pow((lastY-Last2y), 2));
                        c.r= 25;
                        c.label = "q"+count_circles++;
			Document.add(c);
			break;
		}
		case 'l':
		case 'L':{
                    if(ActiveShape.type=="circle")
                    {
                        System.out.println("Active Type circle");
                        esperandoClick=true;
                        break;
                    }
			Line l=new Line();
			l.x0=lastX;
                        l.y0=lastY;
                        l.x1=Last2x;
                        l.y1=Last2y;
            System.out.println(l.x0+","+l.y0+"_"+l.x1+","+l.y1);
            Document.add(l);


			break;
		}
		
		
		case't':
		case'T':
		{
			if(ActiveShape!=null)
			{
				ActiveShape.Resize(lastX,lastY);
			}
			break;
		}
		case 'r':
		case 'R':
		{
			Square r = new Square();
			r.x0=Last2x;
			r.y0=Last2y;
			r.x1=lastX;
			r.y1=lastY;
			Document.add(r);
			break;
		}
		
		}
		repaint();
	}
}

