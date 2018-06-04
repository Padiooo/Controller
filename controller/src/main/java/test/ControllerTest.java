package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import controller.*;


public class ControllerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void TestControllerMovePlayerHasYAndChangeItToYPlus1() {

		int y = 10;
		int expectedY = 11;
		Player player = new Player(10, y);
		player.setDirection(EnumDirection.UP);
		ControllerMove controllerMove = new ControllerMove();
		controllerMove.movePlayer(player);
		assertEquals(player.getY(), expectedY);
	}
	
	@Test
	public void TestControllerMovePlayerHasYAndChangeItToYLess1() {

		int y = 10;
		int expectedY = 9;
		Player player = new Player(10, y);
		player.setDirection(EnumDirection.DOWN);
		ControllerMove controllerMove = new ControllerMove();
		controllerMove.movePlayer(player);
		assertEquals(player.getY(), expectedY);
	}
	
	@Test
	public void TestControllerMovePlayerHasXAndChangeItToXPlus1() {

		int x = 10;
		int expectedX = 11;
		Player player = new Player(x, 10);
		ControllerMove controllerMove = new ControllerMove();
		player.setDirection(EnumDirection.LEFT);
		controllerMove.movePlayer(player);
		assertEquals(player.getX(), expectedX);
	}
	
	@Test
	public void TestControllerMovePlayerHasXAndChangeItToXLess1() {

		int x = 10;
		int expectedX = 9;
		Player player = new Player(x, 10);
		ControllerMove controllerMove = new ControllerMove();
		player.setDirection(EnumDirection.RIGHT);
		controllerMove.movePlayer(player);
		assertEquals(player.getX(), expectedX);
	}

}

