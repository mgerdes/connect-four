package gameEngine;

import java.util.*;
import competitors.*;
import common.*;

@SuppressWarnings("serial")
public class GameManager {
	private static GameManager singleton;

	private GameManager()
	{

	}

	private final Player player1 = new ExamplePlayer1();
	private final Player player2 = new Superbad();
	
	private Player nextPlayer = player1; //--Player 1 goes first
	
	private Board board = Board.getInstance();
	private LinkedList<Move> moveList = new LinkedList<Move>();

	public static GameManager getInstance()
	{
		if (singleton == null)
		{
			singleton = new GameManager();
		}

		return singleton;
	}

	public Color getPlayerColor(Player player) throws UnknownPlayerException
	{
		if (player == player1)
		{
			return Color.BLACK;
		}

		if (player == player2)
		{
			return Color.RED;
		}

		throw new UnknownPlayerException();
	}
	
	protected void applyMove(Move move, Player player, int turnNumber) throws UnknownPlayerException, InvalidMoveException
	{
		move.setPlayerColor(getPlayerColor(player));
		board.applyMove(turnNumber, move);
		printMoveToConsole(move);
		moveList.add(move);
	}

	protected Player getNextPlayer()
	{
		Player toReturn = nextPlayer;
		nextPlayer = getOtherPlayer(nextPlayer);
		return toReturn;
	}
	
	protected LinkedList<Move> getMoveList()
	{
		return moveList;
	}

	private Player getOtherPlayer(Player player)
	{
		if (player == player1)
		{
			return player2;
		}

		return player1;
	}

	private void printMoveToConsole(Move move)
			throws UnknownPlayerException {
		System.out.println(move.getPlayerColor() + " moves in col " + move.column);
		System.out.println(board);
	}
}