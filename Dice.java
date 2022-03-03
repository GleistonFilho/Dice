import java.util.Random;

public class Dice {
	private float[] probabillitiesFaces;
	public float predictedMean;
	
	public Dice(float[] probabillitiesFaces) {
		this.probabillitiesFaces = probabillitiesFaces;
		this.predictedMean = 0;
		float total = 0;
		for(float p : this.probabillitiesFaces) {
			total += p;
		}
		for(int i=0; i<this.probabillitiesFaces.length; i++) {
			this.probabillitiesFaces[i] /= total;
			this.predictedMean += probabillitiesFaces[i]*(1 + i);
		}

	}
	
	public static Dice balanced(int sides) {
		float[] pFaces = new float[sides];
		for(int i=0; i<sides; i++) {
			pFaces[i] = 1;
		}
		return new Dice(pFaces);
	}
	
	public static Dice loaded(int sides) {
		Random random = new Random();
		float[] pFaces = new float[sides];
		for(int i=0; i<sides; i++) {
			pFaces[i] = random.nextInt(1000);
		}
		return new Dice(pFaces);
	}
	
	public static Dice highLoaded(int sides) {
		float[] pFaces = new float[sides];
		for(int i=0; i<sides; i++) {
			pFaces[i] = i+1;
		}
		return new Dice(pFaces);
	}
	
	public static Dice lowLoaded(int sides) {
		float[] pFaces = new float[sides];
		for(int i=0; i<sides; i++) {
			pFaces[i] = sides-i-1;
		}
		return new Dice(pFaces);
	}
	
	public int roll() {
		Random random = new Random();
		float value = random.nextFloat();
		float pTotal = 0;
		for(int i=0; i<this.probabillitiesFaces.length; i++) {
			pTotal += this.probabillitiesFaces[i];
			if(value <= pTotal) {
				return i + 1;
			}
		}
		return this.probabillitiesFaces.length;
	}
	
	public int[] rollTimes(int times) {
		int[] rolls = new int[times];
		for(int i=0; i<times; i++) {
			rolls[i] = this.roll();
		}
		return rolls;
	}
	
	public String toString() {
		String str = "Number of faces: "+this.probabillitiesFaces.length+";\n";
		str += "Probabillity for each face: [ ";
		for(float p : this.probabillitiesFaces) {
			str += p+" ";
		}
		str += "];\n";
		str += "Predicted mean: "+this.predictedMean+";";
		return str;
	}
}
