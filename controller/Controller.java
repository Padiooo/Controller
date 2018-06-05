package controller;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import controller.EnumDirection;
import model.*;
import view.*;

public class Controller {

	private Frame frame;
	private Model model;

	public Controller(Frame frame, Model model) {
		super();
		this.model = model;
		this.frame = frame;
		start();
	}

	public void start() {

		while (model.getMap().getPlayer().getAlive() == true && model.getMap().getPlayer().getWin() == false) {
			movePlayer();
			moveEnnemy();

			frame.getPan().setEnnemy(new ArrayList<Ennemy>( model.getMap().getEnnemy()));
			frame.getPan().setObstacle(new ArrayList<Obstacle>( model.getMap().getObstacle()));
			frame.getPan().setTreasure(new ArrayList<Treasure>( model.getMap().getTreasure()));
			frame.getPan().setDoor(model.getMap().getDoor());
			frame.getPan().setCrystal(model.getMap().getCrystal());
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			frame.getPan().setEnnemy(new ArrayList<Ennemy>( model.getMap().getEnnemy()));	 
			/*frame.getPan().repaint();*/
		}
		restart();

	}

	private void restart() {
		model.setMap(model.getMapRestart());
		this.model = new Model();
		start();
	}

	public void keyListened() {
		KeyEvent key = frame.getKey();
		int keyCode = key.getKeyCode();
		switch (keyCode) {

		case KeyEvent.VK_Z:
			model.getMap().getPlayer().setDirection(EnumDirection.UP);
			break;

		case KeyEvent.VK_Q:
			model.getMap().getPlayer().setDirection(EnumDirection.LEFT);
			break;

		case KeyEvent.VK_S:
			model.getMap().getPlayer().setDirection(EnumDirection.DOWN);
			break;

		case KeyEvent.VK_D:
			model.getMap().getPlayer().setDirection(EnumDirection.RIGHT);
			break;

		case KeyEvent.VK_SPACE:
			model.getMap().getPlayer().shoot();
			break;

		default:
			break;
		}

	}

	public void movePlayer() {
		model.getMap().getPlayer().move();
		int x = model.getMap().getPlayer().getX();
		int y = model.getMap().getPlayer().getY();

		// Ennemy

		for (Ennemy ennemy : model.getMap().getEnnemy()) {
			if (ennemy.getX() == x && ennemy.getY() == y) {
				model.getMap().getPlayer().die();
			}
		}

		// Obstacle

		for (Obstacle obstacle : model.getMap().getObstacle()) {
			if (obstacle.getX() == x && obstacle.getY() == y) {
				moveBackPlayer();
			}
		}

		// Treasure
		for (Treasure treasure : model.getMap().getTreasure()) {
			if (treasure.getX() == x && treasure.getY() == y) {
				model.getMap().getPlayer().lootTreasure(treasure.getGold());
			}
		}

		// Crystal

		if (model.getMap().getCrystal().getX() == x && model.getMap().getCrystal().getY() == y) {
			model.getMap().getPlayer().lootCrystal();
			model.getMap().getCrystal().looted();
		}

		// Door

		if (model.getMap().getDoor().getX() == x && model.getMap().getDoor().getY() == y
				&& model.getMap().getDoor().getState() == false) {
			model.getMap().getPlayer().die();
		}
	}

	public void moveBackPlayer() {

		EnumDirection direction;

		switch (model.getMap().getPlayer().getDirection()) {
		case UP:
			direction = EnumDirection.DOWN;
			model.getMap().getPlayer().setDirection(direction);
			break;
		case LEFT:
			direction = EnumDirection.RIGHT;
			model.getMap().getPlayer().setDirection(direction);
			break;
		case DOWN:
			direction = EnumDirection.UP;
			model.getMap().getPlayer().setDirection(direction);
			break;
		case RIGHT:
			direction = EnumDirection.LEFT;
			model.getMap().getPlayer().setDirection(direction);
			break;

		default:
			break;
		}
		model.getMap().getPlayer().move();

	}

	public void moveEnnemy() {
		int x = model.getMap().getPlayer().getX();
		int y = model.getMap().getPlayer().getY();

		for (Ennemy ennemy : model.getMap().getEnnemy()) {

			int xTemp = ennemy.getX();
			int yTemp = ennemy.getY();

			ennemy.move(x, y);

			// Ennemy

			for (Ennemy ennemy2 : model.getMap().getEnnemy()) {
				if (ennemy2.getUrl() != ennemy.getUrl()) {
					if (ennemy2.getX() == ennemy.getX() && ennemy2.getY() == ennemy2.getY()) {
						moveBackEnnemy(ennemy, xTemp, yTemp);
					}
				}

			}

			// Obstacle
			for (Obstacle obstacle : model.getMap().getObstacle()) {
				if (obstacle.getX() == ennemy.getX() && obstacle.getY() == ennemy.getY()) {
					moveBackEnnemy(ennemy, xTemp, yTemp);
				}
			}

			// Treasure

			for (Treasure treasure : model.getMap().getTreasure()) {
				if (treasure.getX() == ennemy.getX() && treasure.getY() == ennemy.getY()) {
					moveBackEnnemy(ennemy, xTemp, yTemp);
				}
			}

			// Crystal

			if (model.getMap().getCrystal().getX() == ennemy.getX()
					&& model.getMap().getCrystal().getY() == ennemy.getY()) {
				moveBackEnnemy(ennemy, xTemp, yTemp);
			}

			// Door

			if (model.getMap().getDoor().getX() == ennemy.getX() && model.getMap().getDoor().getY() == ennemy.getY()) {
				moveBackEnnemy(ennemy, xTemp, yTemp);
			}
		}
	}

	public void moveBackEnnemy(Ennemy ennemy, int x, int y) {
		ennemy.setX(x);
		ennemy.setY(y);
	}
}
