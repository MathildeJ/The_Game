import java.util.Arrays;
import java.util.Scanner;

public class Player {
	public int nb_cards;
	public int[] hand;
	public Game game;
	public int score;
	
	public Player(int number_cards, Game game){
		this.nb_cards = number_cards;
		this.game = game;
		hand = new int[nb_cards];
		Draw();
	}
	
	public int index(int card){
		int res = -1;
		for(int i=0;i<nb_cards;i++){
			if(hand[i]==card){
				res = i;
			}
		}
		return res;
	}
	
	public void play_card(int cards, Scanner sc){
		game.print_bases();
		print_hand();
		String move = sc.nextLine();
		String[] parts = move.split(" ");
		int c = Integer.parseInt(parts[0]);
		int b = Integer.parseInt(parts[1]);
		if(legal_move(c, b)){
			System.out.println("Card " + c + " moved on Base " + b + ".");
			score++;
			cards--;
			hand[index(c)] = 0;
			game.bases[b-1] = c;
		}else{
			System.out.println("Illegal move. Try again.");
			play_card(cards, sc);
		}
	}
	
	public void Play(){
		int cards = nb_cards;
		System.out.println("It's your turn, play at least two cards.");
		Scanner sc = new Scanner(System.in);
		int i = 0;
		while(i<2){
			if(!stuck()){
				play_card(cards, sc);
				i++;
			}else{
				System.out.println("No moves left.");
				break;
			}
		}
		while(cards>=0&&!stuck()){
			System.out.println("Do you want to keep playing for this turn? (yes/no)");
			String response = sc.nextLine();
			if("yes".equals(response)){
				play_card(cards,sc);
			}else{
				break;
			}
		}
	}
	
	public void Draw(){
		for(int i=0;i<nb_cards;i++){
			if((hand[i]==0)&&(!no_cards())){
				int new_card = (int)(2 + 98*Math.random());
				while(game.available_cards[new_card-2]==0){
					new_card = (int)(2 + 98*Math.random());
				}
				System.out.println("You drew " + new_card);
				game.remove_card(new_card);
				hand[i] = new_card;
				print_hand();
			}else if(no_cards()){
				System.out.println("No more cards to draw. You have to play your remaining cards.\n");
				break;
			}
		}
	}
	
	public void print_hand(){
		System.out.print("Your hand: " + Arrays.toString(hand) + "\n");
	}
	
	public boolean no_cards(){
		boolean res = true;
		for(int i=0;i<97;i++){
			if(game.available_cards[i]!=0){
				res = false;
				break;
			}
		}
		return res;
	}
	
	public boolean legal_move(int card, int base){
		boolean res = false;
		if(index(card)!=-1&&card!=0){
			if(base==1||base==2){
				if(card<game.bases[base-1]||card==game.bases[base-1]+10){
					res = true;
				}
			}else{
				if(card>game.bases[base-1]||card==game.bases[base-1]-10){
					res = true;
				}
			}
		}
		return res;
	}
	
	public boolean stuck(){
		boolean res = true;
		for(int i=1;i<5;i++){
			for(int j=0;j<nb_cards;j++){
				int card = hand[j];
				if((card!=0)&&(legal_move(card, i))){
					res = false;
					break;
				}
			}
		}
		return res;
	}

	public int score(){
		return 98-score;
	}
}
