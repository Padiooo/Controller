package controller;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Observer;

import controller.EnumDirection;
import model.IModel;
import view.IView;

public class Controller {

	private final IView view;
	private final IModel model;

	public Controller(final IView view, final IModel model) {
		super();
		this.model = model;
		this.view = view;
	}

	public void keyListened() {
		KeyEvent key = view.getKey();
		int value = key.getKeyCode();
		switch (value) {

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

		for (int i = 0; i < model.getMap().getEnnemy().length(); i++) {
			if (model.getMap().getEnnemy().get(i).getX() == x
					&& model.getMap().getEnnemy().get(i).getY() == y) {
				model.getMap().getPlayer().die();
			}

		}

		// Obstacle

		for (int i = 0; i < model.getMap().getObstacle().length(); i++) {
			if (model.getMap().getObstacle().get(i).getX() == x
					&& model.getMap().getObstacle().get(i).getY() == y) {
				moveBackPlayer();
			}
		}

		// Treasure

		for (int i = 0; i < model.getMap().getTreasure().length(); i++) {
			if (model.getMap().getTreasure().get(i).getX() == x
					&& model.getMap().getTreasure().get(i).getY() == y) {
				model.getMap().getPlayer().lootTreasure(model.getMap().getTreasure().getGold());
			}
		}

		// Crystal

		if (model.getMap().getCrystal().getX() == x
				&& model.getMap().getCrystal.getY() == y) {
			model.getMap().getPlayer().lootCrystal();
			model.getMap().getCrystal() = null;
		}

		// Door

		if (model.getMap().getDoor().getX() == x
				&& model.getMap().getDoor().getY() == y
				&& mode.getMap()x.getDoor().getState() == false) {
			model.getMap().getPlayer().die();
		}
	}

	public void moveBackPlayer() {
		
		EnumDirection direction;
		
		switch (model.getMap().getPlayer().getDirection()) {
		case UP : 
			direction = EnumDirection.DOWN;
			model.getMap().getPlayer().setDirection(direction)
			break;
		case LEFT : 
			direction = EnumDirection.RIGHT;
			model.getMap().getPlayer().setDirection(direction)
			break;
		case DOWN : 
			direction = EnumDirection.UP;
			model.getMap().getPlayer().setDirection(direction)
			break;
		case RIGHT : 
			direction = EnumDirection.LEFT;
			model.getMap().getPlayer().setDirection(direction)
			break;

		default:
			break;
		}
		model.getMap().getPlayer().move();
		
	}

	public void moveEnnemy() {
		int x = model.getMap().getPlayer().getX();
		int y = model.getMap().getPlayer().getY();

		for (int j = 0; j < model.getMap().getEnnemy().length(); j++) {

			int xTemp = model.getMap().getennemy().get(j).getX();
			int yTemp = model.getMap().getennemy().get(j).getY();

			model.getMap().getEnnemy().get(j).move(x, y);

			// Ennemy

			for (int i = 0; i < model.getMap().getEnnemy().length(); i++) {
				if (model.getMap().getEnnemy().get(i) != model.getMap().getEnnemy().get(j)) {
					if (model.getMap().getEnnemy().get(i).getX() == model.getMap().getEnnemy().get(j).getX()
							&& model.getMap().getEnnemy().get(i).getY() == model.getMap().getEnnemy().get(j).getY()) {
						moveBackEnnemy(j, xTemp, yTemp);
					}
				}

			}

			// Obstacle

			for (int i = 0; i < model.getMap().getObstacle().length(); i++) {
				if (model.getMap().getObstacle().get(i).getX() == model.getMap().getEnnemy().get(j).getX()
						&& model.getMap().getObstacle().get(i).getY() == model.getMap().getEnnemy().get(j).getY()) {
					moveBackEnnemy(j, xTemp, yTemp);
				}
			}

			// Treasure

			for (int i = 0; i < model.getMap().getTreasure().length(); i++) {
				if (model.getMap().getTreasure().get(i).getX() == model.getMap().getEnnemy().get(j).getX()
						&& model.getMap().getTreasure().get(i).getY() == model.getMap().getEnnemy().get(j).getY()) {
					moveBackEnnemy(j, xTemp, yTemp);
				}

			}

			// Crystal

			if (model.getMap().getCrystal().getX() == model.getMap().getEnnemy().get(j).getX()
					&& model.getMap().getCrystal().getY() == model.getMap().getEnnemy().get(j).getY()) {
				moveBackEnnemy(j, xTemp, yTemp);
			}

			// Door

			if (model.getMap().getDoor().getX() == model.getMap().getEnnemy().get(j).getX()
					&& model.getMap().getDoor().getY() == model.getMap().getEnnemy().get(j).getY()) {
				moveBackEnnemy(j, xTemp, yTemp);
			}
		}
	}

	public void moveBackEnnemy(int i, int x, int y) {
		model.getMap().getEnnemy().get(i).setX(x);
		model.getMap().getEnnemy().get(i).setY(y);
	}
}
