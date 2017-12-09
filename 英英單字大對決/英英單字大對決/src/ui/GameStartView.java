package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import controller.EEFighter;
import model.Question;
import model.sprite.BasicMapBuilder;
import model.sprite.BasicMapDirector;
import model.sprite.IGameStartView;
import model.sprite.Sprite;
import model.sprite.SpriteName;

/**
 * @author Lin
 *
 */

public class GameStartView extends JFrame implements IGameStartView{

	private Label player1Lab;
	private Label player2Lab;
	private Label wordDefinitionLab;
	private GridBagConstraints gbc;
	private GameViewImp gameViewPanel;

	public GameStartView() {
		super("英英單字大對決");
		setBounds(215, 80, 1105, 704);
		setupViews();
		setupLayout();
	}

	private void setupLayout() {
		getContentPane().setBackground(Color.GRAY);
		getContentPane().setLayout(new GridBagLayout());
		getContentPane().add(gameViewPanel);
		setupViewsLocation();
		setupViewsBackground();
	}

	private void setupViewsBackground() {
		player1Lab.setBackground(ColorHelper.getPrimaryDark());
		player2Lab.setBackground(ColorHelper.getPrimaryDark());
		wordDefinitionLab.setBackground(ColorHelper.getPrimaryBlue());
	}

	private void setupViewsLocation() {
		
		
		add(player1Lab);
		add(wordDefinitionLab);
		add(player2Lab);
		
		getContentPane().setLayout(new GridBagLayout());
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		addComponent(player1Lab, 2.0, 1.0, 0, 0, 1, 2);
		addComponent(wordDefinitionLab, 4.0, 1.0, 2, 0, 1, 2);
		addComponent(player2Lab, 2.0, 1.0, 5, 0, 1, 2);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.WEST;
		addComponent(gameViewPanel, 1.0, 10.0, 0, 5, 7, 7);
	}

	private void setupViews() {
		initializeAll();
		setupGameViewPanel();
		Font font = new Font("微軟正黑體", Font.BOLD, 20);
		setViewsFont(font);
		setViewsSize(new Dimension(400, 100));
		setViewsText();
	}

	private void setViewsText() {
		player1Lab.setText("Player1: ");
		wordDefinitionLab.setText("Word defintion : ");
		player2Lab.setText("Player2: ");
	}

	private void setViewsSize(Dimension dimension) {
		player1Lab.setSize(dimension);
		player2Lab.setSize(dimension);
		wordDefinitionLab.setSize(new Dimension(100, 700));
		wordDefinitionLab.setMaximumSize(new Dimension(100, 400));
	}

	private void setViewsFont(Font font) {
		player1Lab.setFont(font);
		player1Lab.setForeground(Color.white);
		player2Lab.setFont(font);
		player2Lab.setForeground(Color.white);
		wordDefinitionLab.setFont(font);
		wordDefinitionLab.setForeground(Color.white);
	}

	private void setupGameViewPanel() {
		gameViewPanel.setPreferredSize(new Dimension(1050, 650));
	}

	private void initializeAll() {
		player1Lab = new Label();
		player2Lab = new Label();
		wordDefinitionLab = new Label();
		gbc = new GridBagConstraints();
		gameViewPanel = new GameViewImp(new EEFighter(new BasicMapDirector(new BasicMapBuilder())), this);
		gameViewPanel.start();
	}
	
	@Override
	public void onNextQuestion(Question question) {
		String definition = "1. ( " + question.getPartOfSpeech() + ". ) " + question.getDefinition();
		StringBuilder strBuilder = new StringBuilder("<html>");  
        strBuilder.append(definition);  
        strBuilder.append("</html>");  
		wordDefinitionLab.setText(strBuilder.toString());
	}

	public void addComponent(Component c, Double weightX, Double weightY, int row, int column, int width, int height) {
		gbc.weightx = weightX;
		gbc.weighty = weightY;
		gbc.gridx = row;
		gbc.gridy = column;
		gbc.gridwidth = width;
		gbc.gridheight = height;
		add(c, gbc);
	}
	
	public interface IGameStartView {
		void onNextQuestion(Question question);
		void onPlayerEatLetter(Sprite sprite);
	}

	@Override
	public void onPlayerEatLetter(String player, Sprite letter) {
		String str1 = player1Lab.getText();
		String str2 = player2Lab.getText();
		if (player.equals("player1"))
			player1Lab.setText(str1 + " " + letter.getSpriteName());
		else if (player.equals("player2"))
			player2Lab.setText(str2 + " " + letter.getSpriteName());
	}

	@Override
	public void onPlayerPopedLetter(String player, Sprite letter) {
		
	}

}
