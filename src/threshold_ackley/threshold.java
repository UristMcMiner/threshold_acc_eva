package threshold_ackley;

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
			//double[] c = {0.1, 0.1, 0.1, 0.1, 0.1, 0.1};
			double[] c = {1, 1, 1, 1, 30, 6};
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
			double stepsize = 0.0001;

			while(itr<gitr)
			{	
				itr++;
				int iitr = 0; //inner Iteration
				while(iitr < limit)// && del1 > thresh)
				{
					iitr ++;
					//	neue Config erstellen
					deviation(c, max, min, stepsize);
					
					fj = ack_fitness(ackley_func.ackley_func(c));
					del1 = fi - fj;
				}
				if(del1 < 0) //<thresh
					fi = fj;

				thresh = thresh * (eps);
			}
			System.out.println(ackley_func.ackley_func(c_best));
			System.out.println("{"+c_best[0]+","+c_best[1]+","+c_best[2]+","+c_best[3]+","+c_best[4]+","+c_best[5]+"}");
		}
		
		public static int ack_fitness(double val){
			if(val==0)return 1000;
			int x = (int) ((1 - (val/30)) * 1000);
			return x;
		}
		
		public static double[] deviation(double[] input, int max, int min, double u){
			double[] result = input;
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
