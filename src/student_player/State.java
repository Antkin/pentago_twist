package student_player;

import java.util.ArrayList;
import java.util.List;

import pentago_twist.PentagoBoardState;
import pentago_twist.PentagoMove;

/*
 * Defines a possible state in our search tree. Holds a PentagoBoardState as well as other useful information like visit count and other useful MCTS info
 */
public class State {
	private Object boardObject;
	private PentagoBoardState boardState;
	private int visitCount;
	private double winScore;
	
	public State(PentagoBoardState inputState) {
		boardObject = inputState.clone();
		boardState = (PentagoBoardState) boardObject;
	}
	
	public State(State state) {
		this.boardObject = state.getBoard();
		boardState = (PentagoBoardState) boardObject;
		
		this.visitCount = state.getNumVisits();
		this.winScore = state.getWinScore();
	}
	
	public int getOpponentPlayer() {
		return 1 - boardState.getTurnPlayer();
	}
	
	
	public PentagoBoardState getBoard() {
		return (PentagoBoardState) boardState.clone();
	}
	
	void setBoard(Object boardObject) {
		this.boardObject = boardObject;
		boardState = (PentagoBoardState) this.boardObject;
	}
	
	public int getNumVisits() {
		return visitCount;
	}
	
	public void setNumVisits(int numVisits) {
		visitCount = numVisits;
	}
	
	double getWinScore() {
		return winScore;
	}
	
	void setWinScore(double winScore) {
		this.winScore = winScore;
	}
	
	//Returns all possible states given the current state
	public List<State> getAllPossibleStates(){
		List<State> possibleStates = new ArrayList<>();
		List<PentagoMove> possibleMoves = boardState.getAllLegalMoves();
		
		//Compute all possible states from the list of possible moves
		possibleMoves.forEach(move -> {
			State possibleState = new State(boardState);
			possibleState.boardState.processMove(move);
			possibleStates.add(possibleState);
		});
		
		return possibleStates;
	}
	
	void incrementNumVisits() {
		this.visitCount++;
	}
	
	void addScore(double scoreToAdd) {
		this.winScore += scoreToAdd;
	}
	
	void randomMove() {
		List<PentagoMove> possibleMoves = boardState.getAllLegalMoves();
		int numPossibilities = possibleMoves.size();
		int randNum = (int) (Math.random() * numPossibilities);
		boardState.processMove(possibleMoves.get(randNum));
	}
}
