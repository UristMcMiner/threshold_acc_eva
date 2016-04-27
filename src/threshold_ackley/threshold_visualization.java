package threshold_ackley;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class threshold_visualization {
	public static void main(String[] args) {
		
		JFrame f = new JFrame();
		f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE ); 
		f.setSize( 1920, 800 ); 
		f.add( imges.dp ); 
		f.setVisible( true ); 

		Timer frameDrawer = new Timer();
		frameDrawer.schedule(new drawNewFrame(), 0, 1);
		Graphics2D liner = (Graphics2D) imges.mainImg.getGraphics();

        liner.setColor(Color.BLACK);
        BasicStroke bs = new BasicStroke(2);
        liner.setStroke(bs);
        liner.drawLine(imges.panel_one - 40, 400+41, imges.panel_one + 40, 400+41);
        liner.drawString("x1", imges.panel_one - 50, 400 - 41);
        liner.drawLine(imges.panel_one - 40, 400+41, imges.panel_one - 40, 400-41);
        liner.drawString("x2", imges.panel_one + 40 , 400 + 50);
        
        liner.drawLine(imges.panel_two - 40, 400+41, imges.panel_two + 40, 400+41);
        liner.drawString("x3", imges.panel_two - 50, 400 - 41);
        liner.drawLine(imges.panel_two - 40, 400+41, imges.panel_two - 40, 400-41);
        liner.drawString("x4", imges.panel_two + 40 , 400 + 50);
        
        liner.drawLine(imges.panel_three - 40, 400+41, imges.panel_three + 40, 400+41);
        liner.drawString("x5", imges.panel_three - 50, 400 - 41);
        liner.drawLine(imges.panel_three - 40, 400+41, imges.panel_three - 40, 400-41);
        liner.drawString("x6", imges.panel_three + 40 , 400 + 50);
        
        liner.setColor(Color.WHITE);
		
		for(int k = 0; k<5; k++)
			algo();
		//bakanntes globales Minimum ausgeben:
		double[]x = {0,0,0,0,0,0};
		System.out.println(ackley_func.ackley_func(x));
	}
		public static void algo()
		{
			Graphics2D liner = (Graphics2D) imges.mainImg.getGraphics();
	        liner.setColor(Color.WHITE);
	        BasicStroke bs = new BasicStroke(2);
	        liner.setStroke(bs);
			Graphics2D linerblack = (Graphics2D) imges.mainImg.getGraphics();
	        liner.setColor(Color.BLACK);
	        liner.setStroke(bs);
			//double[] c = {0.01, 0.01, 0.01, 0.01, 0.01, 0.01};
			double[] c = {30, 30, 30, 30, 30, 30};
			int itr = 0;	//Iteration
			int gitr = 100000;  //globale Iteration
			int limit = 100;
			double thresh = 2;
			double[] c_best = c;
			int max = 40;
			int min = -40;
			
			double del1 = 33; //Differenz
			double fi = ack_fitness(ackley_func.ackley_func(c));
			double fj = 0;
			double eps = 0.0001;
			double stepsize = 0.1;
			System.out.println("c undeviated: "+Arrays.toString(c));
			c = deviation(c, max, min, stepsize);
			System.out.println("c deviated: "+Arrays.toString(c));
			fj = ack_fitness(ackley_func.ackley_func(c));
			
			while(itr<gitr)
			{	
				itr++;
				int iitr = 0; //inner Iteration
				while(iitr < limit)// && del1 > thresh)
				{
					iitr ++;
					//	neue Config erstellen
					
					
					fj = ack_fitness(ackley_func.ackley_func(c));
					del1 = fi - fj;
					//System.out.println(fj + ";"+fi);
					//if(del1 > 0) System.out.println("true");
					if( del1 > 0 )
						c = deviation(c, max, min, stepsize);
					if( del1 < 0.01 || del1 > -0.01){
						c = deviation(c, max, min, stepsize);
					}
					liner.fillRect((int)c[0]+imges.panel_one, (int)c[1]+400, 6,6);
					liner.fillRect((int)c[2]+imges.panel_two, (int)c[3]+400, 6,6);
					liner.fillRect((int)c[4]+imges.panel_three, (int)c[5]+400,6,6);
					linerblack.fillRect(imges.panel_one - 40, 400 - 40, 80, 80);
					linerblack.fillRect(imges.panel_two - 40, 400 - 40, 80, 80);
					linerblack.fillRect(imges.panel_three - 40, 400 - 40, 80, 80);
					//System.out.println(c[5]);
					
				}
				if(del1 < 0) //<thresh
					fi = fj;

				thresh = thresh * (eps);
			}
			System.out.println(ackley_func.ackley_func(c));
			Arrays.toString(c);
		}
		
		public static int ack_fitness(double val){
			if(val==0)return 1000;
			int x = (int) ((1 - (val/30)) * 1000);
			return x;
		}
		
		public static double[] deviation(double[] input, int max, int min, double u){
			double[] result = input.clone();
			int i = (int) Math.round(Math.random()*5);
			//for(int i = 0; i < input.length; i++)
			//{
				result[i] = input[i] + (Math.random()*2-1)*u;
				if(result[i]>max)result[i]=min;
				if(result[i]<min)result[i]=max;
			//}
			return result;
		}
		
}

class DrawPanel extends JPanel 
{ 
	public BufferedImage canvas;
	  @Override 
	  protected void paintComponent( Graphics g ) 
	  { 
	    super.paintComponent( g ); 
	    Graphics2D g2 = (Graphics2D) g;
	    g2.drawImage(canvas, null, null);
	  } 
}

class drawNewFrame extends TimerTask{
	
	@Override public void run(){
		imges.dp.canvas = imges.mainImg;
		imges.dp.repaint();
	}
}

class imges{
	public static BufferedImage mainImg = new BufferedImage(1920, 800, BufferedImage.TYPE_INT_ARGB);
	public static DrawPanel dp = new DrawPanel();
	public static int panel_one = 320;
	public static int panel_two = 640;
	public static int panel_three = 960;
}
