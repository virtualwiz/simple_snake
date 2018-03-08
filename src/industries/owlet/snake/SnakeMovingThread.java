package industries.owlet.snake;

import java.awt.Color;
import java.awt.Panel;
import java.util.LinkedList;

public class SnakeMovingThread extends Thread{

	private Snake Snake;
	private int direction = 39;

	public SnakeMovingThread(Snake snake) {
		// TODO Auto-generated constructor stub
		this.Snake = snake;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	@Override
	public void run() {
		while(true){
			moveOnce();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void moveOnce(){
		LinkedList<Panel> SnakeDataMoving = Snake.SnakeDataLinkedList;
		Panel SnakePanelMoving = Snake.MainSnakePanel;
		
		int CoordMovX = SnakeDataMoving.getLast().getX();
		int CoordMovY = SnakeDataMoving.getLast().getY();
		
		switch(direction){
		case 37:
			CoordMovX -= 20;
			break;
		case 38:
			CoordMovY -= 20;
			break;
		case 39:
			CoordMovX += 20;
			break;
		case 40:
			CoordMovY += 20;
			break;
		}
		
		Panel SnakeNewHead = new Panel();
		SnakeNewHead.setBackground(Color.WHITE);
		SnakeNewHead.setBounds(CoordMovX, CoordMovY, 20, 20);
		
		SnakePanelMoving.add(SnakeNewHead);
		SnakeDataMoving.add(SnakeNewHead);
		
//		BEGIN OF AUTOMATION
//		if(CoordMovX < Snake.FoodPanel.getX()){
//			direction = 39;
//		}
//		else if(CoordMovX > Snake.FoodPanel.getX()){
//			direction = 37;
//		}
//		else{
//			if(CoordMovY < Snake.FoodPanel.getY()){
//				direction = 40;
//			}
//			else if(CoordMovY > Snake.FoodPanel.getY()){
//				direction = 38;
//			}
//		}
//		END OF AUTOMATION
		
		if(CoordMovX == Snake.FoodPanel.getX() && CoordMovY == Snake.FoodPanel.getY()){
			SnakePanelMoving.remove(Snake.FoodPanel);
			Snake.initFood();
			
//			REMOVING SNAKE TAIL
//			SnakePanelMoving.remove(SnakeDataMoving.getFirst());
//			SnakeDataMoving.remove(SnakeDataMoving.getFirst());
//			END OF REMOVING SNAKE TAIL
		}
		else{
			SnakePanelMoving.remove(SnakeDataMoving.getFirst());
			SnakeDataMoving.remove(SnakeDataMoving.getFirst());
		}
		
		
	}
}
