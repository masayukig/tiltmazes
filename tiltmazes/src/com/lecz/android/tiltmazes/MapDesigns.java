/*
 * Copyright (c) 2009, Balazs Lecz <leczbalazs@gmail.com>
 * All rights reserved.
 * 
 * Based on the puzzle collection of Andrea Gilbert, http://www.clickmazes.com/
 * Maze designs used with permission.
 * Maze concepts and designs © Andrea Gilbert <andrea@clickmazes.com>.
 * 
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 * 
 *     * Redistributions of source code must retain the above copyright notice,
 *       this list of conditions and the following disclaimer.
 * 
 *     * Redistributions in binary form must reproduce the above copyright notice,
 *       this list of conditions and the following disclaimer in the documentation
 *       and/or other materials provided with the distribution.
 * 
 *     * Neither the name of Balazs Lecz nor the names of
 *       contributors may be used to endorse or promote products derived from this
 *       software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 */

package com.lecz.android.tiltmazes;

import static com.lecz.android.tiltmazes.Wall.*;

public final class MapDesigns {

	public static final MapDesign MapM5C = new MapDesign(
			"M5C",
			5, 5,
			new int[][] {
					{LEFT|TOP|BOTTOM, TOP|RIGHT, TOP, TOP|BOTTOM, TOP|RIGHT|BOTTOM},
					{LEFT|RIGHT, 0, 0, BOTTOM, RIGHT},
					{LEFT, BOTTOM, 0, 0, RIGHT|BOTTOM},
					{LEFT, RIGHT, RIGHT, BOTTOM, RIGHT},
					{LEFT|BOTTOM|RIGHT, BOTTOM, BOTTOM, BOTTOM, RIGHT|BOTTOM}
			},
			new int[][] {
					{1, 0, 0, 0, 1},
					{0, 1, 0, 1, 0},
					{0, 0, 0, 0, 0},
					{0, 1, 0, 1, 0},
					{1, 0, 0, 0, 1}
			},
			2, 2
	);

	public static final MapDesign MapM6B = new MapDesign(
			"M6B",
			6, 6,
			new int[][] {
					{LEFT|TOP|BOTTOM, TOP|RIGHT, TOP, TOP, TOP|BOTTOM, TOP|RIGHT},
					{LEFT, 0, BOTTOM|RIGHT, 0, RIGHT, RIGHT},
					{LEFT|BOTTOM|RIGHT, RIGHT, 0, 0, 0, BOTTOM|RIGHT},
					{LEFT, 0, 0, BOTTOM, BOTTOM, RIGHT},
					{LEFT|RIGHT, BOTTOM, 0, 0, BOTTOM, RIGHT},
					{LEFT|BOTTOM, BOTTOM|RIGHT, BOTTOM, BOTTOM|RIGHT, BOTTOM, BOTTOM|RIGHT}
			},
			new int[][] {
					{1, 0, 0, 0, 0, 1},
					{0, 0, 0, 0, 0, 0},
					{1, 0, 0, 0, 0, 0},
					{0, 0, 0, 0, 0, 0},
					{0, 1, 0, 0, 0, 1},
					{1, 0, 0, 1, 0, 0}
			},
			4, 1
	);
	
	private MapDesigns() {
	    throw new AssertionError();
	}
}
