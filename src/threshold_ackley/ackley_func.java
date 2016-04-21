package threshold_ackley;

public class ackley_func {

	public static double ackley_func(double[] x)
	{
			double sum1 = 0.0;
			double sum2 = 0.0;
			
			for (int i = 0 ; i < x.length ; i ++) {
				sum1 += Math.pow(x[i], 2);
				sum2 += (Math.cos(2*Math.PI*x[i]));
			}
			return -20.0*Math.exp(-0.2*Math.sqrt(sum1 / ((double )x.length)))  
					- Math.exp(sum2 /((double )x.length))+ 20 + Math.exp(1.0);
	}
	
	
}
