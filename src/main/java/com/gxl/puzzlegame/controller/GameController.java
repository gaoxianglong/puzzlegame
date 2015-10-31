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
package com.gxl.puzzlegame.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.gxl.puzzlegame.main.Main;
import com.gxl.puzzlegame.service.GameService;

public class GameController implements ActionListener {
	private Map<Object, Boolean> imageList = Main.imageList;
	private JButton imageButton;

	public GameController(JButton imageButton) {
		this.imageButton = imageButton;
	}

	@Override
	public synchronized void actionPerformed(ActionEvent e) {
		if (e.getSource() == imageButton) {
			GameService.orderService(1);
			int x = imageButton.getX();
			int y = imageButton.getY();
			if (imageList.get(x + "," + y)) {
				imageButton.setBounds(GameService.newX, GameService.newY, 100, 100);
				imageList.remove(x + "," + y);
				imageList.put(GameService.newX + "," + GameService.newY, false);
				GameService.orderService(2);
				GameService.orderService(1);
				GameService.orderService(3);
				if (null != GameService.leftNum)
					imageList.put(GameService.leftNum, true);
				if (null != GameService.rightNum)
					imageList.put(GameService.rightNum, true);
				if (null != GameService.upNum)
					imageList.put(GameService.upNum, true);
				if (null != GameService.downNum)
					imageList.put(GameService.downNum, true);
			}
		}
	}
}