package industries.owlet.snake;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Random;

public class Snake extends Frame{
	public Panel MainSnakePanel;
	public LinkedList<Panel> SnakeDataLinkedList = new LinkedList<Panel>();
	
	SnakeMovingThread MovingThread = new SnakeMovingThread(this);
	
	public Panel FoodPanel;
	public void initFood(){
		Random CoordRandom = new Random();
		int CoordFoodX = (CoordRandom.nextInt(34) + 1)*20;
		int CoordFoodY = (CoordRandom.nextInt(34) + 1)*20;
		
		FoodPanel = new Panel();
		FoodPanel.setBackground(Color.GREEN);
		FoodPanel.setBounds(CoordFoodX, CoordFoodY, 20, 20);
		
		MainSnakePanel.add(FoodPanel);
	}
	
	private void addListeners(){
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				int newDirection = e.getKeyCode();  // 获得用户键盘方向
				int currentDirection = MovingThread.getDirection();  // 蛇当前的移动方向
				if((newDirection+currentDirection) % 2 != 0){
					MovingThread.setDirection(newDirection);
				}
			}
		});
	}
	
	private void initSnakePanel(){
		MainSnakePanel = new Panel();
		MainSnakePanel.setBackground(Color.DARK_GRAY);
		MainSnakePanel.setLayout(null);
		this.add(MainSnakePanel);
	}
	
	private void initSnakeBody(){
		Panel SnakeBodyPanelInitial = new Panel();
		SnakeBodyPanelInitial.setBounds(0, 0, 20, 20);
		SnakeBodyPanelInitial.setBackground(Color.WHITE);
		SnakeBodyPanelInitial.setLayout(null);
		MainSnakePanel.add(SnakeBodyPanelInitial);
		SnakeDataLinkedList.add(SnakeBodyPanelInitial);
		
		for(int i = 0; i < 7; i++)
		{
			Panel SnakeBodyPanel = new Panel();
			SnakeBodyPanel.setBackground(Color.WHITE);
			SnakeBodyPanel.setLayout(null);
			int CoordX = SnakeDataLinkedList.getLast().getX();
			SnakeBodyPanel.setBounds(CoordX + 20, 0, 20, 20);
			MainSnakePanel.add(SnakeBodyPanel);
			SnakeDataLinkedList.add(SnakeBodyPanel);
		}
	}
	
	public Snake() {
		// TODO Auto-generated constructor stub
		initSnakePanel();
		initSnakeBody();
		addListeners();
		initFood();
		this.setSize(800, 800);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		MovingThread.start();
	}
}
