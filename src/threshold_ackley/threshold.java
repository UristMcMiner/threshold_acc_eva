package threshold_ackley;

public class threshold {
		public static void algo()
		{
			double[] c = {1, 1, 1, 1, 1, 1};
			int itr = 0;	//Iteration
			int gitr = 20;  //globale Iteration
			double old = 99999;
			double thresh = 2;
			double[] c_best = c;
			int max = 40;
			int min = -40;
			int limit = 20;
			double del1 = 33; //KA
			double u = 0.5; //KA
			double fi = fitness(c);
			double fj = 0;
			double acc = Math.pow(10,  -1);
			double eps = 0.01;
			double del2 = 0;
			
			while(itr<gitr)
			{
				itr++;
				int iitr = 0; //inner Iteration
				while(iitr < limit || del1 > thresh)
				{
					iitr ++;
					for(int i = 0; i < c.length; i++)
					{
						c[i] = c[i] + (max-min)* Math.pow((2*u-1), i);
					}
					fj = fitness(c);
					del1 = fi - fj;
				}
				if(del1 < thresh)
					fi = fj;
				if(thresh < thrtol)
					del2 = (neww - old )/old;
				if(abs(del2) < acc)
					c_best = c;
				else
				{
					old = neww;
					thresh = thresh * (1 - eps);
				}
				
						
			}
		}
		
		public static double fitness(double c[])
		{
			return 1;
			
		}
}
