package threshold_ackley;

public class main_fba {

	public static double lowest_value;
	public static double highest_value;
	public static double[] lowest_deviation;
	public static double[] highest_deviation;
	public static void main(String[] args) {
		int q = 1;
		int q_new = 1;
		int q_old = 1;
		
		double[] start = {1,1,1,1,1,1};
		int dim = 6;
		int t = 2000;
		int max = 40;
		int min = -40;
		double u = 0.5;
		int iterations = 50000000;
		double[] deviated = start;
		lowest_value = ackley_func.ackley_func(start);
		highest_value = lowest_value;
		lowest_deviation = start;
		highest_deviation = start;
		for(int i = 0; i < iterations; i++){
			deviated = deviation(deviated, max, min, u);
			double value = ackley_func.ackley_func(deviated);
			if(value < lowest_value){
				lowest_value = value;
				lowest_deviation = deviated;
			}
			if(value > highest_value){
				highest_value = value;
				highest_deviation = deviated;
			}
			
			//System.out.println("{"+deviated[0]+","+deviated[1]+","+deviated[2]+","+deviated[3]+","+deviated[4]+","+deviated[5]+"}");
		}
		System.out.println(lowest_value);
		System.out.println("{"+lowest_deviation[0]+","+lowest_deviation[1]+","+lowest_deviation[2]+","+lowest_deviation[3]+","+lowest_deviation[4]+","+lowest_deviation[5]+"}");
		System.out.println(highest_value);
		System.out.println("{"+highest_deviation[0]+","+highest_deviation[1]+","+highest_deviation[2]+","+highest_deviation[3]+","+highest_deviation[4]+","+highest_deviation[5]+"}");
		

	}

	public static double[] deviation(double[] input, int max, int min, double u){
		double[] result = input;
		for(int i = 0; i < input.length; i++)
		{
			result[i] = input[i] + Math.random()*0.00001;
			if(result[i]>40)result[i]=-40;
		}
		return result;
	}
	
	/*public static int ack_fitness(double val){
		if(val < )
	}*/
}
