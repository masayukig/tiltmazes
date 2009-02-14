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

import java.util.List;
import java.util.LinkedList;
import static com.lecz.android.tiltmazes.Wall.*;

public final class MapDesigns {

	public static final List<MapDesign> designList = new LinkedList<MapDesign>();
	
	static {
		/*
		// This is the icon (flipped horizontally!)
		designList.add(new MapDesign(
			    "Icon",
			    3, 3,
			    new int[][] {
			            {LEFT|TOP|RIGHT, TOP, TOP|RIGHT},
			            {LEFT|BOTTOM, 0, RIGHT|BOTTOM},
			            {LEFT|BOTTOM, BOTTOM, RIGHT|BOTTOM}
			    },
			    new int[][] {
			            {0, 0, 0},
			            {0, 0, 0},
			            {0, 0, 1}
			    },
			    0, 0
		));
		*/
		
		designList.add(new MapDesign(
		    "S5A",
		    5, 5,
		    new int[][] {
		            {LEFT|TOP, TOP|BOTTOM, TOP|RIGHT, TOP, TOP|RIGHT},
		            {LEFT, 0, 0, 0, RIGHT|BOTTOM},
		            {LEFT|RIGHT, 0, BOTTOM, 0, RIGHT},
		            {LEFT, BOTTOM, 0, 0, RIGHT|BOTTOM},
		            {LEFT|BOTTOM, BOTTOM, BOTTOM|RIGHT, BOTTOM, RIGHT|BOTTOM}
		    },
		    new int[][] {
		            {0, 0, 0, 0, 0},
		            {0, 0, 0, 0, 0},
		            {0, 0, 0, 0, 0},
		            {0, 0, 0, 0, 0},
		            {0, 0, 0, 0, 1}
		    },
		    0, 0
		));

		designList.add(new MapDesign(
			"S6A",
	        6, 6,
	        new int[][] {
	                {LEFT|TOP|RIGHT, TOP, TOP|BOTTOM, TOP, TOP|RIGHT, TOP|RIGHT},
	                {LEFT, 0, 0, 0, BOTTOM, RIGHT},
	                {LEFT|RIGHT, RIGHT, RIGHT|BOTTOM, BOTTOM, 0, RIGHT|BOTTOM},
	                {LEFT|BOTTOM, 0, RIGHT, RIGHT, 0, RIGHT},
	                {LEFT, 0, 0, 0, 0, RIGHT|BOTTOM},
	                {LEFT|BOTTOM, BOTTOM|RIGHT, BOTTOM|RIGHT, BOTTOM, BOTTOM, RIGHT|BOTTOM}
	        },
	        new int[][] {
	                {0, 0, 0, 0, 0, 0},
	                {0, 0, 0, 0, 0, 0},
	                {0, 0, 0, 0, 0, 0},
	                {0, 0, 0, 1, 0, 0},
	                {0, 0, 0, 0, 0, 0},
	                {0, 0, 0, 0, 0, 0}
	        },
	        2, 2
		));

        designList.add(new MapDesign(
	        "S6B",
	        6, 6,
	        new int[][] {
	                {LEFT|TOP, TOP, TOP|RIGHT|BOTTOM, TOP, TOP, TOP|RIGHT|BOTTOM},
	                {LEFT|BOTTOM, 0, BOTTOM, 0, 0, RIGHT},
	                {LEFT, 0, 0, 0, RIGHT, RIGHT|BOTTOM},
	                {LEFT, 0, RIGHT, BOTTOM, 0, RIGHT},
	                {LEFT|RIGHT|BOTTOM, 0, 0, 0, 0, RIGHT},
	                {LEFT|BOTTOM, BOTTOM|RIGHT, BOTTOM, BOTTOM|RIGHT, BOTTOM|RIGHT, BOTTOM|RIGHT}
	        },
	        new int[][] {
	                {0, 0, 0, 0, 0, 0},
	                {0, 0, 0, 0, 0, 0},
	                {0, 0, 0, 0, 0, 1},
	                {0, 0, 0, 0, 0, 0},
	                {0, 0, 0, 0, 0, 0},
	                {0, 0, 0, 0, 0, 0}
	        },
	        0, 3
		));
        
        designList.add(new MapDesign(
    		"S7A",
            7, 7,
            new int[][] {
                    {LEFT|TOP, TOP, TOP, TOP|BOTTOM, TOP|RIGHT, TOP, TOP|RIGHT},
                    {LEFT|BOTTOM, RIGHT, 0, 0, BOTTOM, 0, RIGHT|BOTTOM},
                    {LEFT|BOTTOM, 0, RIGHT, 0, 0, 0, RIGHT},
                    {LEFT, 0, 0, 0, RIGHT|BOTTOM, 0, RIGHT|BOTTOM},
                    {LEFT, BOTTOM, RIGHT, 0, 0, 0, BOTTOM|RIGHT},
                    {LEFT|BOTTOM, RIGHT, 0, 0, 0, BOTTOM, RIGHT},
                    {LEFT|BOTTOM, BOTTOM, BOTTOM|RIGHT, BOTTOM, BOTTOM|RIGHT, BOTTOM, BOTTOM|RIGHT}
            },
            new int[][] {
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 1},
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0}
            },
            0, 2
        ));

        designList.add(new MapDesign(
    		"S7B",
            7, 7,
            new int[][] {
                    {LEFT|TOP|RIGHT, TOP, TOP|BOTTOM, TOP|RIGHT, TOP, TOP, TOP|RIGHT|BOTTOM},
                    {LEFT, 0, 0, 0, 0, 0, RIGHT},
                    {LEFT, BOTTOM, RIGHT, 0, 0, RIGHT, RIGHT},
                    {LEFT, 0, 0, RIGHT|BOTTOM, RIGHT, 0, RIGHT|BOTTOM},
                    {LEFT|BOTTOM, 0, 0, 0, BOTTOM, 0,  RIGHT},
                    {LEFT, RIGHT, RIGHT|BOTTOM, 0, 0, 0, RIGHT},
                    {LEFT|BOTTOM, BOTTOM, BOTTOM, BOTTOM|RIGHT, BOTTOM, BOTTOM|RIGHT, BOTTOM|RIGHT}
            },
            new int[][] {
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 1, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0}
            },
            4, 1
        ));
      
        designList.add(new MapDesign(
	        "S7C",
	        7, 7,
	        new int[][] {
	                {LEFT|TOP|RIGHT, TOP|BOTTOM, TOP|RIGHT, TOP, TOP, TOP|RIGHT, TOP|RIGHT},
	                {LEFT|BOTTOM, 0, 0, 0, RIGHT|BOTTOM, 0, RIGHT},
	                {LEFT, BOTTOM, 0, 0, 0, BOTTOM, RIGHT},
	                {LEFT|BOTTOM, 0, RIGHT, RIGHT, 0, 0, RIGHT},
	                {LEFT, RIGHT, BOTTOM, 0, 0, 0, BOTTOM|RIGHT},
	                {LEFT, 0, 0, BOTTOM|RIGHT, BOTTOM, 0, RIGHT},
	                {LEFT|BOTTOM|RIGHT, BOTTOM, BOTTOM, BOTTOM, BOTTOM, BOTTOM|RIGHT, BOTTOM|RIGHT}
	        },
	        new int[][] {
	                {0, 0, 0, 0, 0, 0, 0},
	                {0, 0, 0, 0, 0, 0, 0},
	                {0, 0, 0, 0, 0, 0, 0},
	                {0, 0, 0, 0, 0, 0, 0},
	                {0, 0, 0, 0, 0, 0, 0},
	                {0, 0, 0, 0, 0, 0, 0},
	                {0, 0, 0, 0, 0, 0, 1}
	        },
	        0, 0
        ));

        designList.add(new MapDesign(
	        "S8A",
	        8, 8,
	        new int[][] {
	                {LEFT|TOP, TOP|RIGHT, TOP|BOTTOM, TOP, TOP, TOP|BOTTOM, TOP|RIGHT, TOP|RIGHT},
	                {LEFT|BOTTOM, 0, 0, RIGHT|BOTTOM, 0, 0, 0, RIGHT|BOTTOM},
	                {LEFT|BOTTOM, 0, 0, 0, RIGHT, RIGHT, BOTTOM, RIGHT},
	                {LEFT, 0, RIGHT, 0, 0, 0, 0, RIGHT},
	                {LEFT, BOTTOM, 0, 0, 0, 0, 0, LEFT|BOTTOM|RIGHT},
	                {LEFT, 0, BOTTOM, 0, BOTTOM|RIGHT, 0, 0, RIGHT},
	                {LEFT|RIGHT, 0, 0, 0, BOTTOM, 0, 0, BOTTOM|RIGHT},
	                {LEFT|BOTTOM, BOTTOM|RIGHT, BOTTOM, BOTTOM, BOTTOM|RIGHT, BOTTOM, BOTTOM, BOTTOM|RIGHT}
	        },
	        new int[][] {
	                {0, 0, 0, 0, 0, 0, 0, 0},
	                {0, 0, 0, 0, 0, 0, 0, 0},
	                {0, 0, 0, 0, 0, 0, 0, 0},
	                {0, 0, 0, 0, 0, 0, 0, 0},
	                {0, 0, 0, 0, 0, 0, 0, 0},
	                {0, 0, 0, 0, 0, 0, 0, 1},
	                {0, 0, 0, 0, 0, 0, 0, 0},
	                {0, 0, 0, 0, 0, 0, 0, 0}
	        },
	        0, 2
        ));

        designList.add(new MapDesign(
	        "S9A",
	        9, 9,
	        new int[][] {
	                {LEFT|TOP|BOTTOM, TOP, TOP|RIGHT, TOP, TOP, TOP|BOTTOM, TOP|RIGHT, TOP, TOP|RIGHT|BOTTOM},
	                {LEFT, RIGHT|BOTTOM, 0, 0, BOTTOM, 0, 0, RIGHT, RIGHT},
	                {LEFT, 0, 0, 0, 0, RIGHT, RIGHT, 0, RIGHT},
	                {LEFT|BOTTOM, RIGHT, 0, BOTTOM, RIGHT, BOTTOM, 0, 0, RIGHT|BOTTOM},
	                {LEFT, 0, 0, BOTTOM, 0, BOTTOM, RIGHT, 0, RIGHT},
	                {LEFT, BOTTOM, 0, RIGHT, RIGHT, 0, BOTTOM, 0, BOTTOM|RIGHT},
	                {LEFT|RIGHT, 0, BOTTOM, 0, BOTTOM, 0, 0, 0, RIGHT},
	                {LEFT|BOTTOM, 0, 0, BOTTOM, RIGHT, 0, 0, BOTTOM, RIGHT},
	                {LEFT|BOTTOM, BOTTOM, BOTTOM, BOTTOM|RIGHT, BOTTOM, BOTTOM, BOTTOM, BOTTOM|RIGHT, BOTTOM|RIGHT}
	        },
	        new int[][] {
	                {0, 0, 0, 0, 0, 0, 0, 0, 0},
	                {0, 0, 0, 0, 0, 0, 0, 0, 0},
	                {0, 0, 0, 0, 0, 0, 0, 0, 0},
	                {0, 0, 0, 0, 0, 0, 0, 0, 0},
	                {0, 0, 0, 0, 0, 0, 0, 0, 0},
	                {0, 0, 0, 1, 0, 0, 0, 0, 0},
	                {0, 0, 0, 0, 0, 0, 0, 0, 0},
	                {0, 0, 0, 0, 0, 0, 0, 0, 0},
	                {0, 0, 0, 0, 0, 0, 0, 0, 0}
	        },
	        5, 3
        ));
        
    	designList.add(new MapDesign(
            "S9B",
            9, 9,
            new int[][] {
                    {LEFT|TOP|RIGHT, TOP, TOP|RIGHT, TOP|BOTTOM, TOP, TOP, TOP|RIGHT, TOP, TOP|RIGHT|BOTTOM},
                    {LEFT, BOTTOM, 0, BOTTOM, BOTTOM, RIGHT, BOTTOM, 0, RIGHT},
                    {LEFT|BOTTOM, RIGHT, 0, RIGHT, 0, 0, 0, RIGHT, RIGHT},
                    {LEFT, 0, BOTTOM, 0, 0, BOTTOM|RIGHT, 0, BOTTOM, RIGHT},
                    {LEFT|BOTTOM, 0, RIGHT, RIGHT, BOTTOM, 0, 0, 0, RIGHT},
                    {LEFT, 0, 0, RIGHT, 0, BOTTOM, 0, RIGHT, BOTTOM|RIGHT},
                    {LEFT|RIGHT, RIGHT, 0, BOTTOM, 0, 0, 0, 0, RIGHT},
                    {LEFT, BOTTOM, 0, 0, RIGHT, 0, RIGHT, BOTTOM, RIGHT},
                    {LEFT|BOTTOM, BOTTOM|RIGHT, BOTTOM, BOTTOM, BOTTOM, BOTTOM|RIGHT, BOTTOM, BOTTOM|RIGHT, BOTTOM|RIGHT}
            },
            new int[][] {
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 1, 0}
            },
            1, 8
    	));
    	
    	designList.add(new MapDesign(
                "S10A",
                10, 10,
                new int[][] {
                        {LEFT|TOP|RIGHT, TOP, TOP, TOP, TOP, TOP|BOTTOM, TOP|RIGHT, TOP, TOP|RIGHT, TOP|RIGHT},
                        {LEFT, RIGHT, 0, 0, 0, 0, 0, 0, 0, BOTTOM|RIGHT},
                        {LEFT|BOTTOM, 0, 0, BOTTOM|RIGHT, 0, 0, BOTTOM|RIGHT, BOTTOM, 0, RIGHT},
                        {LEFT, 0, BOTTOM|RIGHT, 0, 0, 0, RIGHT, 0, 0, RIGHT},
                        {LEFT|BOTTOM, 0, BOTTOM, RIGHT, 0, 0, BOTTOM, 0, BOTTOM|RIGHT, RIGHT},
                        {LEFT, BOTTOM, 0, 0, RIGHT, 0, 0, BOTTOM, RIGHT, RIGHT},
                        {LEFT, 0, BOTTOM|RIGHT, BOTTOM, 0, 0, BOTTOM|RIGHT, 0, 0, BOTTOM|RIGHT},
                        {LEFT|BOTTOM, 0, RIGHT, 0, 0, BOTTOM|RIGHT, 0, 0, 0, BOTTOM|RIGHT},
                        {LEFT, 0, BOTTOM, 0, 0, 0, RIGHT, BOTTOM, 0, RIGHT},
                        {LEFT|BOTTOM, BOTTOM|RIGHT, BOTTOM, BOTTOM, BOTTOM|RIGHT, BOTTOM, BOTTOM, BOTTOM, BOTTOM|RIGHT, BOTTOM|RIGHT}
                },
                new int[][] {
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 1}
                },
                0, 0
        ));

    	designList.add(new MapDesign(
                "S10B",
                10, 10,
                new int[][] {
                        {LEFT|TOP|RIGHT, TOP|BOTTOM, TOP, TOP|RIGHT, TOP, TOP, TOP|RIGHT, TOP, TOP, TOP|RIGHT|BOTTOM},
                        {LEFT, RIGHT, 0, 0, 0, 0, BOTTOM, 0, BOTTOM|RIGHT, RIGHT},
                        {LEFT|RIGHT, 0, BOTTOM|RIGHT, 0, RIGHT, 0, 0, 0, BOTTOM, RIGHT},
                        {LEFT, 0, RIGHT, 0, 0, 0, RIGHT, 0, 0, BOTTOM|RIGHT},
                        {LEFT|BOTTOM, 0, 0, 0, BOTTOM|RIGHT, BOTTOM, 0, 0, RIGHT, RIGHT},
                        {LEFT|RIGHT, RIGHT, 0, BOTTOM, RIGHT, 0, 0, 0, BOTTOM, RIGHT},
                        {LEFT, BOTTOM, 0, 0, 0, 0, 0, BOTTOM, 0, BOTTOM|RIGHT},
                        {LEFT, BOTTOM, BOTTOM, RIGHT, RIGHT, BOTTOM, 0, 0, 0, RIGHT},
                        {LEFT|BOTTOM|RIGHT, 0, 0, 0, 0, BOTTOM, RIGHT, RIGHT, BOTTOM, RIGHT},
                        {LEFT|BOTTOM, BOTTOM|RIGHT, BOTTOM, BOTTOM|RIGHT, BOTTOM, BOTTOM, BOTTOM, BOTTOM, BOTTOM|RIGHT, BOTTOM|RIGHT}
                },
                new int[][] {
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 1}
                },
                0, 0
        ));

    	designList.add(new MapDesign(
                "S10C",
                10, 10,
                new int[][] {
                        {LEFT|TOP|RIGHT, TOP|BOTTOM, TOP, TOP, TOP|RIGHT, TOP, TOP, TOP|BOTTOM, TOP|RIGHT, TOP|RIGHT},
                        {LEFT, 0, RIGHT, 0, 0, 0, 0, 0, 0, RIGHT},
                        {LEFT, 0, 0, 0, BOTTOM, 0, BOTTOM|RIGHT, 0, BOTTOM, RIGHT},
                        {LEFT|RIGHT, 0, BOTTOM, 0, BOTTOM, 0, 0, 0, RIGHT, RIGHT},
                        {LEFT|BOTTOM, 0, 0, 0, RIGHT, RIGHT, 0, 0, BOTTOM, RIGHT},
                        {LEFT|RIGHT, RIGHT, 0, 0, 0, BOTTOM, 0, BOTTOM, 0, RIGHT},
                        {LEFT, 0, 0, BOTTOM, 0, BOTTOM, 0, 0, RIGHT, RIGHT},
                        {LEFT|RIGHT, 0, 0, RIGHT, BOTTOM, 0, 0, 0, 0, BOTTOM|RIGHT},
                        {LEFT|BOTTOM, 0, 0, 0, RIGHT, 0, BOTTOM, RIGHT, BOTTOM, RIGHT},
                        {LEFT|BOTTOM, BOTTOM, BOTTOM|RIGHT, BOTTOM, BOTTOM, BOTTOM|RIGHT, BOTTOM, BOTTOM, BOTTOM|RIGHT, BOTTOM|RIGHT}
                },
                new int[][] {
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 1, 0}
                },
                1, 0
        ));
    	
    	designList.add(new MapDesign(
                "S10D",
                10, 10,
                new int[][] {
                        {LEFT|TOP|RIGHT, TOP|RIGHT, TOP, TOP, TOP|RIGHT, TOP, TOP|RIGHT, TOP|BOTTOM, TOP, TOP|RIGHT},
                        {LEFT|RIGHT, 0, BOTTOM, 0, 0, 0, 0, 0, 0, RIGHT},
                        {LEFT, 0, 0, RIGHT, RIGHT, 0, 0, RIGHT, 0, BOTTOM|RIGHT},
                        {LEFT, RIGHT, 0, 0, 0, 0, BOTTOM, 0, 0, RIGHT},
                        {LEFT|BOTTOM, 0, BOTTOM, 0, BOTTOM|RIGHT, BOTTOM, 0, BOTTOM|RIGHT, 0, BOTTOM|RIGHT},
                        {LEFT, 0, BOTTOM, 0, RIGHT, 0, 0, BOTTOM, 0, RIGHT},
                        {LEFT|BOTTOM, 0, BOTTOM, 0, 0, RIGHT, BOTTOM, 0, RIGHT, RIGHT},
                        {LEFT, RIGHT, 0, BOTTOM, RIGHT, RIGHT, 0, 0, RIGHT, RIGHT},
                        {LEFT|RIGHT, 0, 0, 0, 0, 0, 0, RIGHT, 0, RIGHT},
                        {LEFT|BOTTOM, BOTTOM, BOTTOM, BOTTOM|RIGHT, BOTTOM|RIGHT, BOTTOM|RIGHT, BOTTOM, BOTTOM, BOTTOM|RIGHT, BOTTOM|RIGHT}
                },
                new int[][] {
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 1}
                },
                0, 0
        ));
    	
    	designList.add(new MapDesign(
                "S10E",
                10, 10,
                new int[][] {
                        {LEFT|TOP, TOP|RIGHT, TOP, TOP, TOP, TOP|RIGHT, TOP, TOP|BOTTOM, TOP|RIGHT, TOP|RIGHT},
                        {LEFT, BOTTOM|RIGHT, BOTTOM, 0, BOTTOM, 0, RIGHT, 0, 0, RIGHT},
                        {LEFT|RIGHT, 0, 0, 0, 0, BOTTOM, 0, RIGHT, 0, RIGHT},
                        {LEFT, 0, BOTTOM, RIGHT, 0, 0, BOTTOM, 0, 0, RIGHT},
                        {LEFT|BOTTOM, 0, 0, 0, BOTTOM, RIGHT, 0, 0, BOTTOM, RIGHT},
                        {LEFT, 0, RIGHT, BOTTOM, RIGHT, 0, RIGHT, 0, 0, BOTTOM|RIGHT},
                        {LEFT, BOTTOM|RIGHT, 0, BOTTOM, 0, 0, BOTTOM, 0, 0, RIGHT},
                        {LEFT|BOTTOM, 0, 0, RIGHT, 0, 0, 0, 0, BOTTOM|RIGHT, RIGHT},
                        {LEFT, 0, 0, BOTTOM, 0, BOTTOM, 0, RIGHT, 0, RIGHT},
                        {LEFT|BOTTOM|RIGHT, BOTTOM, BOTTOM, BOTTOM, BOTTOM, BOTTOM, BOTTOM|RIGHT, BOTTOM|RIGHT, BOTTOM, BOTTOM|RIGHT}
                },
                new int[][] {
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
                },
                1, 1
        ));
    	
        designList.add(new MapDesign(
    			"M5A",
    			5, 5,
    			new int[][] {
    					{LEFT|TOP|BOTTOM, TOP|RIGHT, TOP, TOP|BOTTOM, TOP|RIGHT},
    					{LEFT, 0, 0, RIGHT, RIGHT},
    					{LEFT|RIGHT, RIGHT, BOTTOM, 0, RIGHT},
    					{LEFT, 0, 0, 0, RIGHT|BOTTOM},
    					{LEFT|BOTTOM|RIGHT, BOTTOM, RIGHT|BOTTOM, BOTTOM, RIGHT|BOTTOM}
    			},
    			new int[][] {
    					{1, 0, 0, 0, 1},
    					{0, 0, 0, 0, 0},
    					{0, 0, 1, 0, 0},
    					{0, 0, 0, 0, 0},
    					{1, 0, 0, 0, 1}
    			},
    			2, 3
    	));	
    	
        designList.add(new MapDesign(
    			"M5B",
    			5, 5,
    			new int[][] {
    					{LEFT|TOP|BOTTOM, TOP, TOP|BOTTOM, TOP|RIGHT, TOP|RIGHT},
    					{LEFT, 0, TOP|RIGHT, 0, RIGHT},
    					{LEFT|RIGHT, 0, BOTTOM, 0, BOTTOM|RIGHT},
    					{LEFT|BOTTOM, RIGHT, 0, BOTTOM, RIGHT|BOTTOM},
    					{LEFT|BOTTOM, BOTTOM, BOTTOM, BOTTOM, RIGHT|BOTTOM}
    			},
    			new int[][] {
    					{0, 1, 0, 1, 0},
    					{1, 0, 0, 0, 1},
    					{0, 0, 0, 0, 0},
    					{1, 0, 0, 0, 1},
    					{0, 1, 0, 1, 0}
    			},
    			2, 2
    	));	

        designList.add(new MapDesign(
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
		));
		
		designList.add(new MapDesign(
				"M6A",
				6, 6,
				new int[][] {
						{LEFT|TOP|RIGHT, TOP, TOP|RIGHT, TOP, TOP, TOP|RIGHT},
						{LEFT|BOTTOM, 0, BOTTOM, RIGHT, 0, BOTTOM|RIGHT},
						{LEFT|RIGHT, 0, 0, 0, 0, RIGHT},
						{LEFT|BOTTOM, 0, RIGHT, BOTTOM, RIGHT, BOTTOM|RIGHT},
						{LEFT, 0, BOTTOM, 0, 0, RIGHT},
						{LEFT|BOTTOM|RIGHT, BOTTOM, BOTTOM, BOTTOM|RIGHT, BOTTOM, BOTTOM|RIGHT}
				},
				new int[][] {
						{0, 0, 0, 1, 0, 0},
						{1, 0, 0, 0, 0, 0},
						{0, 0, 0, 0, 0, 0},
						{0, 0, 0, 0, 0, 1},
						{1, 0, 0, 0, 0, 1},
						{0, 0, 0, 1, 0, 0}
				},
				0, 2
		));
		
		designList.add(new MapDesign(
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
						{0, 0, 0, 1, 0, 0},
						{0, 1, 0, 0, 0, 1},
						{1, 0, 0, 1, 0, 0}
				},
				4, 1
		));
		
		designList.add(new MapDesign(
			"M6C",
			6, 6,
			new int[][] {
					{LEFT|TOP|RIGHT, TOP, TOP|RIGHT, TOP, TOP, TOP|RIGHT|BOTTOM},
					{LEFT, BOTTOM, 0, BOTTOM|RIGHT, RIGHT, RIGHT},
					{LEFT|BOTTOM, RIGHT, 0, 0, 0, BOTTOM|RIGHT},
					{LEFT, 0, BOTTOM, RIGHT, 0, RIGHT},
					{LEFT|BOTTOM|RIGHT, RIGHT, BOTTOM, 0, BOTTOM|RIGHT, RIGHT},
					{LEFT|BOTTOM, BOTTOM, BOTTOM|RIGHT, BOTTOM, BOTTOM, BOTTOM|RIGHT}
			},
			new int[][] {
					{1, 0, 1, 0, 0, 1},
					{0, 0, 0, 0, 0, 1},
					{0, 1, 0, 0, 0, 0},
					{0, 0, 0, 1, 0, 0},
					{1, 0, 1, 0, 0, 0},
					{1, 0, 0, 0, 0, 1}
			},
			1, 1
		));
		
		designList.add(new MapDesign(
	            "M7A",
	            7, 7,
	            new int[][] {
	                    {LEFT|TOP, TOP|RIGHT, TOP, TOP|RIGHT, TOP, TOP|BOTTOM, TOP|RIGHT},
	                    {LEFT|BOTTOM, 0, 0, BOTTOM, 0, 0, RIGHT},
	                    {LEFT, BOTTOM, 0, RIGHT, 0, 0, BOTTOM|RIGHT},
	                    {LEFT, 0, BOTTOM|RIGHT, 0, BOTTOM, 0, RIGHT|BOTTOM},
	                    {LEFT|BOTTOM, 0, 0, 0, RIGHT, 0, RIGHT},
	                    {LEFT|RIGHT, 0, 0, 0, 0, BOTTOM, RIGHT},
	                    {LEFT|BOTTOM, BOTTOM, BOTTOM|RIGHT, BOTTOM, BOTTOM|RIGHT, BOTTOM, BOTTOM|RIGHT}
	            },
	            new int[][] {
	                    {0, 1, 0, 1, 0, 1, 0},
	                    {0, 0, 0, 0, 0, 0, 0},
	                    {0, 0, 0, 0, 0, 0, 0},
	                    {1, 0, 0, 1, 0, 0, 1},
	                    {0, 0, 0, 0, 0, 0, 0},
	                    {0, 0, 0, 0, 0, 0, 0},
	                    {0, 1, 0, 1, 0, 1, 0}
	            },
	            6, 4
		));

		designList.add(new MapDesign(
	            "M7B",
	            7, 7,
	            new int[][] {
	                    {LEFT|TOP, TOP|RIGHT, TOP|BOTTOM, TOP, TOP|RIGHT, TOP, TOP|RIGHT},
	                    {LEFT|RIGHT, 0, 0, 0, 0, RIGHT, RIGHT},
	                    {LEFT|BOTTOM, 0, BOTTOM|RIGHT, RIGHT, 0, BOTTOM, RIGHT},
	                    {LEFT, 0, 0, RIGHT, BOTTOM, 0, RIGHT|BOTTOM},
	                    {LEFT|RIGHT, RIGHT, 0, BOTTOM, 0, 0, RIGHT|BOTTOM},
	                    {LEFT, BOTTOM, 0, 0, RIGHT, 0, RIGHT},
	                    {LEFT|BOTTOM|RIGHT, BOTTOM, BOTTOM|RIGHT, BOTTOM, BOTTOM, BOTTOM, BOTTOM|RIGHT}
	            },
	            new int[][] {
	                    {1, 0, 0, 1, 0, 0, 0},
	                    {0, 0, 0, 0, 0, 1, 0},
	                    {0, 0, 1, 0, 0, 0, 1},
	                    {0, 0, 0, 0, 0, 0, 0},
	                    {0, 1, 0, 0, 1, 0, 0},
	                    {1, 0, 0, 0, 0, 0, 1},
	                    {0, 0, 1, 0, 0, 0, 0}
	            },
	            3, 1
		));

		designList.add(new MapDesign(
	            "M8A",
	            8, 8,
	            new int[][] {
	                    {LEFT|TOP, TOP|BOTTOM, TOP|BOTTOM, TOP, TOP|BOTTOM, TOP|RIGHT, TOP, TOP|RIGHT},
	                    {LEFT, 0, 0, RIGHT, 0, 0, BOTTOM, RIGHT},
	                    {LEFT, RIGHT, 0, BOTTOM, 0, 0, RIGHT, RIGHT},
	                    {LEFT, 0, BOTTOM, 0, RIGHT, BOTTOM, 0, RIGHT},
	                    {LEFT, RIGHT, 0, 0, BOTTOM, 0, 0, RIGHT|BOTTOM},
	                    {LEFT|RIGHT|BOTTOM, 0, 0, BOTTOM, 0, 0, BOTTOM, RIGHT},
	                    {LEFT, 0, 0, 0, 0, BOTTOM|RIGHT, 0, RIGHT},
	                    {LEFT|BOTTOM, BOTTOM, BOTTOM|RIGHT, BOTTOM, BOTTOM, BOTTOM, BOTTOM|RIGHT, BOTTOM|RIGHT}
	            },
	            new int[][] {
	                    {0, 0, 0, 1, 0, 0, 1, 0},
	                    {1, 0, 0, 0, 1, 0, 0, 0},
	                    {0, 0, 0, 0, 0, 0, 1, 0},
	                    {0, 0, 0, 1, 0, 1, 0, 0},
	                    {0, 0, 0, 0, 0, 0, 0, 1},
	                    {1, 0, 1, 0, 0, 0, 0, 0},
	                    {0, 0, 0, 0, 0, 1, 1, 0},
	                    {0, 1, 0, 1, 0, 0, 0, 1}
	            },
	            4, 5
		));

		designList.add(new MapDesign(
	            "M8B",
	            8, 8,
	            new int[][] {
	                    {LEFT|TOP, TOP|BOTTOM, TOP, TOP, TOP|RIGHT, TOP|BOTTOM, TOP, TOP|RIGHT},
	                    {LEFT, 0, BOTTOM|RIGHT, 0, 0, 0, BOTTOM, RIGHT},
	                    {LEFT|BOTTOM, 0, 0, 0, RIGHT, 0, 0, RIGHT},
	                    {LEFT, 0, RIGHT, BOTTOM, 0, 0, 0, RIGHT},
	                    {LEFT, 0, 0, 0, 0, RIGHT, RIGHT, RIGHT|BOTTOM},
	                    {LEFT, RIGHT, BOTTOM, 0, 0, 0, 0, RIGHT},
	                    {LEFT|RIGHT, BOTTOM, 0, 0, 0, BOTTOM, RIGHT, RIGHT},
	                    {LEFT|BOTTOM, BOTTOM, BOTTOM, BOTTOM|RIGHT, BOTTOM, BOTTOM, BOTTOM, BOTTOM|RIGHT}
	            },
	            new int[][] {
	                    {0, 0, 0, 0, 1, 0, 0, 0},
	                    {0, 1, 0, 0, 0, 0, 0, 1},
	                    {0, 0, 0, 0, 0, 0, 1, 0},
	                    {1, 0, 0, 1, 0, 0, 0, 1},
	                    {0, 0, 0, 0, 0, 1, 0, 0},
	                    {0, 0, 0, 0, 0, 0, 0, 0},
	                    {0, 0, 1, 0, 1, 0, 0, 0},
	                    {1, 0, 0, 0, 0, 0, 1, 0}
	            },
	            3, 5
		));

		designList.add(new MapDesign(
	            "M8C",
	            8, 8,
	            new int[][] {
	                    {LEFT|TOP, TOP|BOTTOM, TOP|RIGHT, TOP, TOP, TOP, TOP|RIGHT|BOTTOM, TOP|RIGHT},
	                    {LEFT, 0, 0, 0, RIGHT, 0, 0, RIGHT},
	                    {LEFT, 0, BOTTOM, 0, 0, 0, RIGHT|BOTTOM, RIGHT},
	                    {LEFT|BOTTOM|RIGHT, RIGHT, 0, 0, 0, 0, 0, RIGHT|BOTTOM},
	                    {LEFT, 0, 0, BOTTOM|RIGHT, 0, RIGHT, 0, RIGHT},
	                    {LEFT, 0, 0, 0, BOTTOM, 0, 0, BOTTOM|RIGHT},
	                    {LEFT, 0, BOTTOM, 0, BOTTOM, 0, 0, RIGHT},
	                    {LEFT|BOTTOM|RIGHT, BOTTOM, BOTTOM, BOTTOM, BOTTOM, BOTTOM|RIGHT, BOTTOM, BOTTOM|RIGHT}
	            },
	            new int[][] {
	                    {0, 1, 0, 0, 0, 1, 0, 1},
	                    {0, 0, 0, 1, 0, 0, 0, 0},
	                    {0, 0, 0, 0, 0, 0, 1, 0},
	                    {1, 0, 1, 0, 1, 0, 0, 0},
	                    {0, 0, 0, 0, 0, 0, 0, 1},
	                    {0, 1, 0, 1, 0, 0, 0, 0},
	                    {0, 0, 0, 0, 1, 0, 0, 0},
	                    {1, 0, 1, 0, 0, 0, 1, 0}
	            },
	            4, 4
		));

		designList.add(new MapDesign(
	            "M10A",
	            10, 10,
	            new int[][] {
	                    {LEFT|TOP, TOP, TOP, TOP, TOP|BOTTOM, TOP|RIGHT, TOP, TOP|RIGHT, TOP, TOP|RIGHT},
	                    {LEFT|BOTTOM, RIGHT, BOTTOM, 0, 0, 0, 0, 0, BOTTOM, RIGHT},
	                    {LEFT|RIGHT, 0, 0, 0, BOTTOM|RIGHT, 0, 0, BOTTOM, 0, RIGHT},
	                    {LEFT, 0, 0, BOTTOM, 0, BOTTOM, 0, RIGHT, 0, BOTTOM|RIGHT},
	                    {LEFT, BOTTOM|RIGHT, 0, 0, 0, 0, RIGHT, 0, 0, RIGHT},
	                    {LEFT, 0, BOTTOM, 0, RIGHT, 0, BOTTOM, 0, 0, RIGHT},
	                    {LEFT|RIGHT, 0, 0, 0, 0, 0, 0, RIGHT, 0, BOTTOM|RIGHT},
	                    {LEFT, BOTTOM, 0, RIGHT, BOTTOM, 0, 0, 0, 0, RIGHT},
	                    {LEFT|BOTTOM, RIGHT, 0, BOTTOM, 0, RIGHT, 0, RIGHT, BOTTOM, RIGHT},
	                    {LEFT|BOTTOM, BOTTOM, BOTTOM, RIGHT|BOTTOM, BOTTOM, BOTTOM, BOTTOM|RIGHT, BOTTOM, BOTTOM, BOTTOM|RIGHT}
	            },
	            new int[][] {
	                    {1, 0, 0, 0, 1, 0, 0, 1, 0, 1},
	                    {0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
	                    {1, 0, 0, 1, 0, 1, 0, 1, 0, 0},
	                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
	                    {0, 1, 0, 0, 0, 0, 1, 0, 0, 0},
	                    {0, 0, 1, 0, 1, 0, 0, 0, 0, 1},
	                    {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
	                    {1, 0, 1, 0, 0, 0, 0, 0, 0, 0},
	                    {0, 0, 0, 0, 0, 0, 1, 0, 1, 0},
	                    {1, 0, 0, 0, 1, 0, 0, 0, 0, 1}
	            },
	            5, 6
		));
		
		designList.add(new MapDesign(
	            "M10B",
	            10, 10,
	            new int[][] {
	                    {LEFT|TOP, TOP|RIGHT, TOP, TOP|RIGHT, TOP, TOP|BOTTOM, TOP, TOP|RIGHT, TOP, TOP|RIGHT},
	                    {LEFT, BOTTOM, RIGHT, 0, 0, BOTTOM, 0, BOTTOM, 0, RIGHT},
	                    {LEFT|BOTTOM, RIGHT, 0, 0, 0, RIGHT, 0, 0, BOTTOM|RIGHT, RIGHT},
	                    {LEFT, 0, BOTTOM|RIGHT, 0, 0, 0, RIGHT, 0, 0, RIGHT},
	                    {LEFT|RIGHT, 0, 0, 0, BOTTOM|RIGHT, 0, 0, BOTTOM, RIGHT, RIGHT},
	                    {LEFT, RIGHT, RIGHT, BOTTOM, 0, RIGHT, 0, BOTTOM, 0, BOTTOM|RIGHT},
	                    {LEFT|BOTTOM, 0, 0, 0, 0, BOTTOM, 0, RIGHT, RIGHT, RIGHT},
	                    {LEFT, 0, RIGHT, 0, 0, 0, 0, 0, BOTTOM, RIGHT},
	                    {LEFT, BOTTOM, 0, 0, BOTTOM|RIGHT, RIGHT, BOTTOM, 0, RIGHT, RIGHT},
	                    {LEFT|BOTTOM, BOTTOM, BOTTOM, BOTTOM|RIGHT, BOTTOM, BOTTOM, BOTTOM, BOTTOM, BOTTOM, BOTTOM|RIGHT}
	            },
	            new int[][] {
	                    {0, 0, 0, 1, 0, 0, 0, 1, 0, 1},
	                    {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
	                    {0, 0, 1, 0, 0, 1, 0, 0, 1, 0},
	                    {1, 0, 0, 1, 0, 0, 0, 1, 0, 0},
	                    {0, 0, 0, 0, 1, 0, 0, 0, 0, 1},
	                    {0, 1, 0, 0, 0, 0, 1, 0, 0, 0},
	                    {0, 0, 0, 1, 0, 0, 0, 1, 0, 0},
	                    {1, 0, 0, 0, 0, 1, 0, 0, 0, 1},
	                    {0, 1, 0, 0, 1, 0, 0, 0, 1, 0},
	                    {0, 0, 1, 0, 0, 0, 1, 0, 0, 0}
	            },
	            5, 4
		));

	}
	
	private MapDesigns() {
	    throw new AssertionError();
	}
}
