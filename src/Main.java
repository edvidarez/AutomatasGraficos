
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Automatas Pad=new Automatas();
             //   ejemplo e = new ejemplo();
//		MATRIX4D a= new MATRIX4D(1,-2,-3,-1,0,1,2,0,0,2,3,1,3,-2,0,1);
//		System.out.println(MATRIX4D.Det(a));
//		MATRIX4D.Print(a);
//		MATRIX4D.Print(MATRIX4D.Inverse(a));
//		MATRIX4D.Print(MATRIX4D.Mul( MATRIX4D.Inverse(a),a));
//		System.out.println(VECTOR4D.dot(new VECTOR4D(11,1,1,1  ), new VECTOR4D(50/12.0f,20,30,30)));
		//e.setVisible(true);
                JButton okButton = new JButton("Nuevo estado"); 
                  okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                       Circle c=new Circle();
			c.x0=400;
			c.y0=400;
			//c.r=(int)Math.sqrt(Math.pow((lastX-Last2x), 2)+Math.pow((lastY-Last2y), 2));
                        c.r= 25;
                        c.label = "q"+Pad.count_circles++;
			Pad.Document.add(c);
                              Pad.repaint();  
                    }          
                 });
                  JButton estadoini = new JButton("Estado Inicial"); 
                  estadoini.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                      if(Pad.ActiveShape==null)
                        {
                            JOptionPane.showMessageDialog(Pad, "Selecciona Primero un estado");
                        }
                      else
                      {
                         if(Pad.ActiveShape.type=="circle") 
                         {
                             for(Shape S:Pad.Document)
                                  {
                                          S.estadoinicial=false;
                                  }
                             Pad.ActiveShape.estadoinicial=true;
                         }
                         else
                         {
                             JOptionPane.showMessageDialog(Pad, "Selecciona Primero un estado");
                         }
                      }
                       Pad.repaint(); 
                    }          
                 });
                  JButton estadofin = new JButton("Estado Final"); 
                  estadofin.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                      if(Pad.ActiveShape==null)
                        {
                            JOptionPane.showMessageDialog(Pad, "Selecciona Primero un estado");
                        }
                      else
                      {
                         if(Pad.ActiveShape.type=="circle") 
                         {
                             Pad.ActiveShape.estadofinal=!Pad.ActiveShape.estadofinal;
                         }
                         else
                         {
                             JOptionPane.showMessageDialog(Pad, "Selecciona Primero un estado");
                         }
                      }
                     Pad.repaint(); 
                    }          
                 });
                  JButton arista = new JButton("Nueva Arista");
                  arista.addActionListener(new ActionListener() {
                
                    public void actionPerformed(ActionEvent e) {
                        if(Pad.ActiveShape==null)
                        {
                            JOptionPane.showMessageDialog(Pad, "Selecciona Primero un estado luego en 'Nueva Arista' y selecciona el estado final");
                        }
                        else
                        if(Pad.ActiveShape.type=="circle")
                        {
                            System.out.println("Active Type circle");
                            Pad.esperandoClick=true;

                        }
                        else
                        {
                             JOptionPane.showMessageDialog(Pad, "Selecciona Primero un estado luego en 'Nueva Arista' y selecciona el estado final");
                        }
                    }
                });
                  JButton delete = new JButton("Eliminar Objeto");
                  delete.addActionListener(new ActionListener() {
                
                    public void actionPerformed(ActionEvent e) {
                        if(Pad.ActiveShape==null)
                        {
                            JOptionPane.showMessageDialog(Pad, "Selecciona Primero un Objeto(Estado o Arista) para Eliminar");
                        }
                        else
                        {
                         int dialogResult = JOptionPane.showConfirmDialog(Pad, "De verdad quieres borrar este Objeto?");
                         if( dialogResult == JOptionPane.YES_OPTION)
                            for(Shape S:Pad.Document)
                                  {
                                          if(S.isActive==true)
                                          {
                                                  System.out.println(Pad.Document.size());
                                                  Pad.Document.remove(S);
                                                  System.out.println(Pad.Document.size());
                                                  Pad.ActiveShape=null;
                                                  Pad.repaint();
                                                  break;
                                          }
                                  }

                  
                        }
                    }
                });

                  Pad.setLayout(new GridLayout(2,1 ));
                  JPanel controlPanel = new JPanel();
                  controlPanel.setLayout(new FlowLayout());
                  controlPanel.add(okButton);
                  controlPanel.add(estadoini);
                  controlPanel.add(estadofin);
                  controlPanel.add(arista);
                  controlPanel.add(delete);
                  Pad.add(controlPanel);
                  //Pad.pack();
                  Pad.setVisible(true);
		// JButton okButton = new JButton("OK");        
	}
        

}
