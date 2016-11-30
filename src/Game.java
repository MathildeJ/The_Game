import java.util.Arrays;

public class Game {
	public int[] available_cards;
	public int[] bases;
	
	public Game(){
		bases = new int[] {100, 100, 1, 1};
		available_cards = new int[98];
		for(int i=0;i<97;i++){
			available_cards[i] = i+2;
		}
	}
	
	public void remove_card(int card){
		available_cards[card-2] = 0;
	}
	
	public void print_bases(){
		System.out.print("Bases: " + Arrays.toString(bases) + "\n");
	}	
}
