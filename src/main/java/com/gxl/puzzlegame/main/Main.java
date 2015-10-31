/*
 * Copyright 2015-2101 gaoxianglong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gxl.puzzlegame.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import com.gxl.puzzlegame.controller.GameController;

public class Main extends JFrame {
	private static final long serialVersionUID = 3369345311434369769L;
	public static JButton[] images = new JButton[8];
	private int randomNum;
	public final static int[][] widthAndHight = { { 0, 0 }, { 100, 0 }, { 200, 0 }, { 0, 100 }, { 100, 100 },
			{ 200, 100 }, { 0, 200 }, { 100, 200 }, { 200, 200 } };
	public final static String[] IMAGE_PATH = { "/images/1.gif", "/images/2.gif", "/images/3.gif", "/images/4.gif",
			"/images/5.gif", "/images/6.gif", "/images/7.gif", "/images/8.gif" };
	public static Map<Object, Boolean> imageList;
	private List<Integer> numList;

	private Main() throws Exception {
		imageList = new HashMap<Object, Boolean>();
		numList = new ArrayList<Integer>();
		this.setTitle("puzzle game");
		this.setLayout(null);
		this.setResizable(false);
		this.setSize(305, 320);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setIconImage(ImageIO.read(this.getClass().getResource("/images/icon.png")));
		addComponent();
		this.setVisible(true);
	}

	/**
	 * 组装组件
	 * 
	 * @author gaoxianglong
	 */
	@SuppressWarnings("unused")
	public void addComponent() {
		int j = 0;
		for (int i = 0; i < images.length; i++) {
			images[i] = new JButton();
			for (; j < images.length;) {
				for (int k = 0; k < widthAndHight[j].length; k++) {
					getImgNum();
					images[i].setIcon(new ImageIcon(Main.class.getClass().getResource(IMAGE_PATH[randomNum])));
					images[i].setBounds(widthAndHight[j][k], widthAndHight[j][++k], 100, 100);
					break;
				}
				j++;
				break;
			}
			imageList.put((int) images[i].getBounds().getX() + "," + (int) images[i].getBounds().getY(),
					7 == i || 5 == i ? true : false);
			GameController gameController = new GameController(images[i]);
			images[i].addActionListener(gameController);
			add(images[i]);
		}
	}

	public void getImgNum() {
		randomNum = ((int) (Math.random() * 8));
		if (numList.contains(randomNum)) {
			getImgNum();
		} else {
			numList.add(randomNum);
		}
	}

	public static void main(String[] args) throws Exception {
		new Main();
	}
}