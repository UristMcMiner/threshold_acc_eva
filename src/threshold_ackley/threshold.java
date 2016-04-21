package threshold_ackley;

public class threshold {
	public static void main(String[] args) {
		algo();
	}
		public static void algo()
		{
			double[] c = {1, 1, 1, 1, 1, 1};
			int itr = 0;	//Iteration
			int gitr = 20;  //globale Iteration
			double old = 0;
			double thresh = 2;
			double[] c_best = c;
			int max = 40;
			int min = -40;
			int limit = 20;
			double del1 = 33; //KA
			double u = 0.5; //KA
			double fi = ack_fitness(ackley_func.ackley_func(c));
			double fj = 0;
			double acc = Math.pow(10,  -1);
			double eps = 0.01;
			double del2 = 0;
			
			while(itr<gitr)
			{
				System.out.println(itr);
				itr++;
				int iitr = 0; //inner Iteration
				while(iitr < limit && del1 > thresh)
				{
					iitr ++;
					for(int i = 0; i < c.length; i++)
					{
						c[i] = c[i] + (max-min)* Math.pow((2*u-1), i);
					}
					fj = ack_fitness(ackley_func.ackley_func(c));
					del1 = fi - fj;
					System.out.println(iitr);
				}
				if(del1 < thresh)
					fi = fj;

				thresh = thresh * (1 - eps);
			}
			System.out.println(ackley_func.ackley_func(c_best));
			System.out.println("{"+c_best[0]+","+c_best[1]+","+c_best[2]+","+c_best[3]+","+c_best[4]+","+c_best[5]+"}");
			System.out.println(ackley_func.ackley_func(c));
			System.out.println("{"+c_best[0]+","+c[1]+","+c[2]+","+c[3]+","+c[4]+","+c[5]+"}");
			
		}
		
		public static int ack_fitness(double val){
			if(val==0)return 1000;
			int x = (int) ((1 - (val/30)) * 1000);
			return x;
		}
}
