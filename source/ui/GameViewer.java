package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.EventListener;

import common.GameConstants;
import gameEngine.GameResult;
import gameEngine.Move;

import javax.swing.*;

public class GameViewer extends JFrame {
	final static int FRAME_WIDTH = 600;
	final static int FRAME_HEIGHT = 500;
	
	private GameResult gameResult;
	private BoardPanel boardPanel = new BoardPanel();
    private common.Color[][] board = new common.Color[GameConstants.NUM_ROWS][GameConstants.NUM_COLUMNS];
    private int[] count = new int[GameConstants.NUM_COLUMNS];
	
	public GameViewer(GameResult result)
	{
		this.gameResult = result;
		add(boardPanel);
		this.setTitle("Connect Four");
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void viewGame() {
		while (!gameResult.moveList.isEmpty()) {
			addMoveToBoard(gameResult.moveList.remove());
            boardPanel.setBoard(board);
            boardPanel.repaint();

            try {
                Thread.sleep(500);
            }
            catch(Exception e)
            {

            }
		}
	}

    private void addMoveToBoard(Move move) {
        int row = GameConstants.NUM_ROWS - count[move.column] - 1;
        board[row][move.column] = move.getPlayerColor();
        count[move.column]++;
    }
}