//  DominanceComparator.java
//
//  Author:
//       Antonio J. Nebro <antonio@lcc.uma.es>
//       Juan J. Durillo <durillo@lcc.uma.es>
//
//  Copyright (c) 2011 Antonio J. Nebro, Juan J. Durillo
//
//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU Lesser General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU Lesser General Public License for more details.
// 
//  You should have received a copy of the GNU Lesser General Public License
//  along with this program.  If not, see <http://www.gnu.org/licenses/>.

package br.edu.ufam.icomp.nsga.comparator;

import java.util.Comparator;

import br.edu.ufam.icomp.nsga.bean.Individuo;

/**
 * This class implements a <code>Comparator</code> (a method for comparing
 * <code>Solution</code> objects) based on a constraint violation test +
 * dominance checking, as in NSGA-II.
 */
public class DominanceComparator implements Comparator<Individuo> {
	/**
	 * Compares two solutions.
	 * 
	 * @param object1
	 *            Object representing the first <code>Solution</code>.
	 * @param object2
	 *            Object representing the second <code>Solution</code>.
	 * @return -1, or 0, or 1 if solution1 dominates solution2, both are
	 *         non-dominated, or solution1 is dominated by solution22,
	 *         respectively.
	 */
	public int compare(Individuo object1, Individuo object2) {
		if (object1 == null)
			return 1;
		else if (object2 == null)
			return -1;

		Individuo solution1 = object1;
		Individuo solution2 = object2;

		int dominate1 = 0; // dominate1 indicates if some objective of solution1
		// dominates the same objective in solution2. dominate2
		int dominate2 = 0; // is the complementary of dominate1.

		int flag = 0; // stores the result of the comparison

		// Equal number of violated constraints. Applying a dominance Test then
		double value1, value2;

		// *********************** ADEQUACAO ***********************
		value1 = solution1.getAdequacao();
		value2 = solution2.getAdequacao();
		if (value1 > value2) {
			flag = -1;
		} else if (value1 < value2) {
			flag = 1;
		} else {
			flag = 0;
		}
		if (flag == -1) {
			dominate1 = 1;
		}
		if (flag == 1) {
			dominate2 = 1;
		}

		// *********************** CUSTO ***********************
		value1 = solution1.getCusto();
		value2 = solution2.getCusto();
		if (value1 < value2) {
			flag = -1;
		} else if (value1 > value2) {
			flag = 1;
		} else {
			flag = 0;
		}
		if (flag == -1) {
			dominate1 = 1;
		}
		if (flag == 1) {
			dominate2 = 1;
		}

		// *********************** RESULTADO DA DOMINANCIA ***********************
		if (dominate1 == dominate2) {
			return 0; // No one dominate the other
		}
		if (dominate1 == 1) {
			return -1; // solution1 dominate
		}
		return 1; // solution2 dominate

	} // compare
} // DominanceComparator
