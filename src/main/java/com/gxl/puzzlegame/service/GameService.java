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
package com.gxl.puzzlegame.service;

import java.util.Map;
import com.gxl.puzzlegame.main.Main;

public class GameService {
	public static int newX, newY;
	public static String leftNum, rightNum, upNum, downNum;
	private static int[][] widthAndHight = Main.widthAndHight;
	private static Map<Object, Boolean> imageList = Main.imageList;

	public static void orderService(int stateOrder) {
		for (int i = 0; i < widthAndHight.length; i++) {
			for (int j = 0; j < widthAndHight[j].length; j++) {
				int x = widthAndHight[i][j];
				int y = widthAndHight[i][j + 1];
				switch (stateOrder) {
				case 1:
					if (null == imageList.get(x + "," + y)) {
						newX = x;
						newY = y;
						return;
					}
					break;
				case 2:
					if (null != imageList.get(x + "," + y))
						imageList.put(x + "," + y, false);
					break;
				case 3:
					if (String.valueOf(x + "," + y).equals(newX + "," + newY)) {
						if (0 <= i - 1 && i != 3 && i != 6)
							leftNum = widthAndHight[i - 1][j] + "," + widthAndHight[i - 1][j + 1];
						else
							leftNum = null;
						if (i < imageList.size() && i != 5 && i != 2)
							rightNum = widthAndHight[i + 1][j] + "," + widthAndHight[i + 1][j + 1];
						else
							rightNum = null;
						if (i <= imageList.size() && 0 <= i - 3)
							upNum = widthAndHight[i - 3][j] + "," + widthAndHight[i - 3][j + 1];
						else
							upNum = null;
						if (i < imageList.size() && i + 3 <= imageList.size())
							downNum = widthAndHight[i + 3][j] + "," + widthAndHight[i + 3][j + 1];
						else
							downNum = null;
					}
				}
				break;
			}
		}
	}
}