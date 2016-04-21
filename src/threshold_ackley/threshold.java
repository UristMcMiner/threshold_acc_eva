package threshold_ackley;

import java.util.Arrays;

public class threshold {
	public static void main(String[] args) {
		for(int k = 0; k<5; k++)
			algo();
		//bakanntes globales Minimum ausgeben:
		double[]x = {0,0,0,0,0,0};
		System.out.println(ackley_func.ackley_func(x));
	}
		public static void algo()
		{
			//double[] c = {0.01, 0.01, 0.01, 0.01, 0.01, 0.01};
			double[] c = {1, 1, 1, 1, 1, 1};
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
			double stepsize = 0.001;
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
					System.out.println(fj + ";"+fi);
					if(del1 > 0) System.out.println("true");
					if( del1 > 0 )
						c = deviation(c, max, min, stepsize);
					if( del1 < 0.01 || del1 > -0.01){
						c = deviation(c, max, min, stepsize);
					}
					
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
			int i = (int) (Math.random()*5);
			//for(int i = 0; i < input.length; i++)
			//{
				result[i] = input[i] + (Math.random()*2-1)*u;
				if(result[i]>max)result[i]=min;
				if(result[i]<min)result[i]=max;
			//}
			return result;
		}
		
}
