package threshold_ackley;

import java.util.Random;

public class threshold_new {

	public static Random rng;
	
	public static void main(String[] args){
		
		//CONF VALS
		
		int max_cnt_no_alterations = 1000000000;
		int dimensions = 6;
		double t = 0.01;
		double t_f = 0.1;
		System.out.println("Starting threshold: " + t);
		System.out.println("Threshold scaling factor: " + t_f);
		
		//EOF VALS
		
		
		rng = new Random();
		candidate c_start = new candidate(dimensions);
		candidate c = new candidate(new double[6]);
		System.out.println("Starting config:");
		System.out.println(c.toString());
		
		//Counters
		int cnt_no_alterations = 0;
		int cnt_alterations = 0;
		int cnt_iterations = 0;
		//EOF Counters
		
		while(cnt_no_alterations < max_cnt_no_alterations){
			cnt_iterations++;
			
			c_start.copy_vals(c);
			c.alter_config();
			
			if(c_start.fitness() - c.fitness() > (-t) && c_start.fitness() != 0){
				cnt_alterations++;
				clear_screen();
				System.out.println('\n'+ "Iteration "+cnt_iterations);
				System.out.println("Better fitness found/within threshold: " + cnt_alterations);
				System.out.println("Fitness " + c.fitness());
//				System.out.println(c.toString());
				
				cnt_no_alterations = 0;
			}
			else{
				cnt_no_alterations++;
			}
			
			if(cnt_iterations % 10000 == 0){
				t = t * t_f;
			}		
		}
		System.out.println("Iterations after termination: " + cnt_iterations);
		System.out.println("Press Enter to exit.");
		try{
		System.in.read();
		}catch(Exception e){}
	}
	
	public static void clear_screen(){
		try{Runtime.getRuntime().exec("cls");}
		catch(Exception e){}
	}
	
}

class candidate {
	public double[] val;
	public int dim;
	
	public candidate(double[] val){
		this.val = val;
		this.dim = val.length;
	}
	
	public candidate(int dim){
		double[] val = new double[dim];
		
		for(int i = 0; i < dim; i++){
			val[i] = threshold_new.rng.nextDouble() * 40;
		}
		
		this.val = val;
		this.dim = dim;
	}
	public void copy_vals(candidate copy_to){
		for(int i = 0; i < dim; i++){
			copy_to.val[i] = this.val[i];
		}
	}
	
	public double fitness(){
		return ackley();
	}
	
	public double ackley(){
		double sum1 = 0.0;
		double sum2 = 0.0;
		
		for (int i = 0 ; i < this.val.length ; i ++) {
			sum1 += Math.pow(this.val[i], 2);
			sum2 += (Math.cos(2*Math.PI*this.val[i]));
		}
		return -20.0*Math.exp(-0.2*Math.sqrt(sum1 / ((double )this.val.length)))  
				- Math.exp(sum2 /((double )this.val.length))+ 20 + Math.exp(1.0);
	}
	
	public void alter_config(){
		int i = threshold_new.rng.nextInt(6);
		double old_val = this.val[i];
		while(old_val == this.val[i]){
			this.val[i] = threshold_new.rng.nextDouble() * 40;
		}
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < this.dim; i++){
			sb.append("x"+(i+1)+"["+this.val[i]+"]"+'\n');
		}
		
		return sb.toString();
	}
}
