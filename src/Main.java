public class Main {

	public static void main(String[] args) {
		Game game = new Game();
		game.print_bases();
		
		Player player = new Player(8, game);
		
		while(!player.stuck()){
			for(int i = 0;i<100;i++){
				if(!player.stuck()){
					System.out.println("Turn " + i + ": ");
					player.Play();
					player.Draw();
				}else{
					break;
				}
			}
		}
		System.out.println("The Game is over. Your final score is: " + player.score());

	}

}
