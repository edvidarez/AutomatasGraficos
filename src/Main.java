
import java.applet.Applet;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class Main {
        public static JPanel cckbox ;
        public static ButtonGroup group;
        public static JRadioButton det,notdet;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Automatas Pad=new Automatas();
               // Pad.setDoubleBuffered(true);
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
                  JButton in = new JButton("Ingresar Palabra");
                  in.addActionListener(new ActionListener() {
                
                    public void actionPerformed(ActionEvent e) {
                       //revisar que el automata esta completamente lleno
                       boolean error=false;
                       int alfaSize= Pad.alfabeto.size();
                       int estadosini=0;
                       int estadosfin=0;
                       Shape estado_actual = null;
                        for(Shape S:Pad.Document)
                        {
                            if(S.type=="circle")
                            {
                               
                                if(S.estadofinal)
                                {
                                    estadosfin++;
                                }
                                if(S.estadoinicial)
                                {
                                    estadosini++;
                                    estado_actual = S;
                                }
                                 if(S.caminos!=alfaSize)
                                {
                                    if(notdet.isSelected())
                                        continue;
                                   JOptionPane.showMessageDialog(Pad, "No esta completo el Autómata", "ERROR",JOptionPane.ERROR_MESSAGE);
                                   error=true;
                                }
                            }
                            
                        }
                        String palabra;
                        if(estadosini==0)
                        {
                           
                            error=true;
                            JOptionPane.showMessageDialog(Pad, "No se definio un estado inicial", "ERROR",JOptionPane.ERROR_MESSAGE); 
                        }
                          if(estadosini>1)
                        {
                            error=true;
                            JOptionPane.showMessageDialog(Pad, "No pueden existir dos estados iniciales", "ERROR",JOptionPane.ERROR_MESSAGE);
                        }
                        if(estadosfin==0)
                        {
                            error=true;
                            JOptionPane.showMessageDialog(Pad, "No se definio un estado final", "ERROR",JOptionPane.ERROR_MESSAGE);        
                        }
                      
                        
                        if(!error &&estadosini==1 )
                        {
                            palabra = JOptionPane.showInputDialog("Ingresa la palabra:");
                            int i;
                            boolean bandera1=false;
                            for(i=0;i<palabra.length();i++)
                            {
                                
                               for(int j=0;j<estado_actual.aristas.size();j++) 
                               {
                                   bandera1=false;
                                    String a_split[] = estado_actual.aristas.get(j).split(",");
                                    for(String a_s:a_split)
                                    {
                                        if(a_s.charAt(0)==(palabra.charAt(i)))
                                        {
                                            System.out.println("Cambio de estado");
                                            bandera1=true;
                                            break;
                                        }
                                        
                                    }
                                    if(bandera1)
                                    {
                                        if( estado_actual.aristas_shape.get(j).fin==null)
                                        {
                                             estado_actual=estado_actual.aristas_shape.get(j).ini;
                                        }
                                        else
                                        estado_actual=estado_actual.aristas_shape.get(j).fin;
                                        j=0;
                                        break;
                                    }
                                    
                                }
                            }
                            if(estado_actual.estadofinal)
                            {
                                JOptionPane.showMessageDialog(Pad,"Termina en un estado Final");
                            }
                            else{
                                JOptionPane.showMessageDialog(Pad,"No termina en estado final", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                       //revisar si el automata termino en un estado final
                    }
                });
                  JFrame mainFrame = new JFrame();
                  mainFrame.setTitle("Automatas2D");
                  mainFrame.setSize(900,800);
                  mainFrame.setLocationRelativeTo(null);
                  mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                  
                  
                 
                  Pad.setLayout(new GridLayout(2,1 ));
                  JPanel controlPanel = new JPanel();
                  Pad.setDoubleBuffered(true);
                  controlPanel.setLayout(new FlowLayout());
                  controlPanel.add(okButton);
                  controlPanel.add(estadoini);
                  controlPanel.add(estadofin);
                  controlPanel.add(arista);
                  controlPanel.add(delete);
                  controlPanel.add(in);
                  cckbox = new JPanel();
                  cckbox.setLayout(new GridLayout(2,2));
                  group = new ButtonGroup();
                  det = new JRadioButton("Determinista");det.setSelected(true);
                  notdet = new JRadioButton("No Determinista");
                  
                  
                  det.addActionListener(new ActionListener() {
                
                    public void actionPerformed(ActionEvent e) {
                        Pad.repaint();
                        
                    }
                    });
                  notdet.addActionListener(new ActionListener() {
                
                    public void actionPerformed(ActionEvent e) {
                        Pad.repaint();
                        
                    }
                    });
                  group.add(det );
                  group.add(notdet );
                  cckbox.add(det);
                  cckbox.add(notdet);
                  controlPanel.add(cckbox);
                  Pad.add(controlPanel);
                  //Pad.pack();
                  
                  mainFrame.add(Pad);
                  mainFrame.setVisible(true); 
		// JButton okButton = new JButton("OK");        
	}
        

}
