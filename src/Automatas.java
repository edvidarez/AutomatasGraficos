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
public class Automatas extends JPanel
implements MouseListener, MouseMotionListener{
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
        boolean determinista =false;
        ArrayList<letra> alfabeto = new ArrayList<letra>();
	boolean onRotation=false,logo=false,esperandoClick=false;
	static String archivo="Guardado";
	 FileInputStream FileInS;
     FileOutputStream FileOptS;
     ObjectInputStream ObjectInS;            
     ObjectOutputStream ObjectOptS;
	public Automatas()
	{
	
	
	addMouseListener(this);
        
	addMouseMotionListener(this);
	
	
	Document= new ArrayList<Shape>();
	
	

	}

	
public void paint(Graphics g){
    
	super.paint(g);
	Dimension height = getSize();
	
	for(Shape S:Document){
		S.Draw(g,S.isActive,onRotation,alfabeto.size());
	}
	
        
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
public letra isInAlfabet(String s)
{
    for(letra a : alfabeto){
        if( s.equals(a.letra))
        {
            return a;
        }
    }
    return null;
}
public boolean isInState(String s,Shape c)
{
     
     for(int x=0;x<c.aristas.size();x++) 
     {
        System.out.println(c.aristas.get(x));
        String a_split[] = c.aristas.get(x).split(",");
        for(String a_s:a_split)
        {
            if(s.equals(a_s))
                return true;
        }
    }
    return false;
}
public void removeletter(String l)
{
    for(int i=0;i<alfabeto.size();i++)
    {
        if(alfabeto.get(i).letra.equals(l))
        {
            System.out.println("se encontro letra a eliminar ");
            alfabeto.get(i).count--;
            System.out.println("count = "+alfabeto.get(i).count);
            if(alfabeto.get(i).count==0)
            {
                System.out.println("Se elimino"+alfabeto.get(i).letra);
                alfabeto.remove(i);
                return;
            }
        }
        System.out.println("letra: "+l+" "+alfabeto.get(i).letra);
    }
     System.out.println("NO se encontro letra a eliminar");
}
public void mouseClicked(MouseEvent e) {
		int i=0;
		boolean flag=false;
                
		for(Shape S:Document){
			i++;
			if(S.HitTest(e.getX(), e.getY())){
                            
                        if(esperandoClick)
                        {
                            boolean ERROR =false;
                            System.out.println("linea entre estados");
                            esperandoClick = false; 
                           String simbol = JOptionPane.showInputDialog("Ingresa el simbolo o simbolos separados por coma(sin espacios)");
                           if(simbol==null)
                               break;
                           String simbols[] = simbol.split(",");
                           String finalLabel = "";
                            for(String s : simbols){
                                    if(isInAlfabet(s)==null)
                                    {
                                        alfabeto.add(new letra(s));
                                        System.out.println("se añadio: "+s);
                                    }
                                    else
                                    {
                                        isInAlfabet(s).count++;
                                        System.out.println(s+"Ya existe en el alfabeto");
                                    }
                                    if(!isInState(s,ActiveShape) || Main.notdet.isSelected())
                                    {
                                        if(finalLabel.length()>0)
                                        {
                                            finalLabel+=",";
                                        }
                                        finalLabel+=s;
                                        ActiveShape.caminos++;
                                    }
                                    else
                                    {
                                        System.out.println("Error se quiere agregar un mismo simbolo para dos distintos estados");
                                        ERROR=true;
                                        JOptionPane.showMessageDialog(null, "Error se quiere agregar un mismo simbolo para dos distintos estados");
                                    }
                            }
                            
                            
                            if(S==ActiveShape &&!ERROR)
                            {
                                 AristaRetorno ar = new AristaRetorno(ActiveShape,finalLabel); 
                                 ActiveShape.aristas.add(finalLabel);
                                 ar.a=finalLabel;
                                 ActiveShape.aristas_shape.add(ar);
                                 Document.add(ar);
                            }
                            else
                            {
                                if(ActiveShape!=null &&!ERROR)
                                {
                                Arista l = new Arista(ActiveShape,S,finalLabel);
                                ActiveShape.aristas.add(finalLabel);
                                l.a=finalLabel;
                                 ActiveShape.aristas_shape.add(l);
                                Document.add(l);
                                }
                            }
                            if(ActiveShape!=null)
				ActiveShape.isActive=false;
                            ActiveShape=null;
                           
                            
                            break;
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
		//System.out.println("cllick "+Main.det.isSelected());
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
                if(e.getButton() == MouseEvent.BUTTON3)
                System.out.println("right click");
//                Circle c=new Circle();
//			c.x0=Last2x;
//			c.y0=Last2y;
//			c.r=(int)Math.sqrt(Math.pow((25), 2)+Math.pow((25), 2));
//			Document.add(c);
//                        repaint();
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
		
		
		
		case't':
		case'T':
		{
			if(ActiveShape!=null)
			{
				ActiveShape.Resize(lastX,lastY);
			}
			break;
		}
		
		
		}
		repaint();
	}
}

