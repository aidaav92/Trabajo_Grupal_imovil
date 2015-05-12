/*

Copyright 2014 Profesores y alumnos de la asignatura Informática Móvil de la EPI de Gijón

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

 */

package es.uniovi.imovil.fcrtrainer.digitalinformation;

import es.uniovi.imovil.fcrtrainer.R;

public class BinaryOffsetExerciseFragment extends BaseNumericalExerciseFragment {

	private int mOffset = 32;
	private String mCorrectAnswer;

	public static BinaryOffsetExerciseFragment newInstance() {

		BinaryOffsetExerciseFragment fragment = new BinaryOffsetExerciseFragment();
		return fragment;
	}

	public BinaryOffsetExerciseFragment() {
	}

	@Override
	protected int obtainExerciseId() {
		return R.string.offset_binary;
	}

	@Override
	protected boolean isResultNumeric() {
		return true;
	}

	@Override
	protected String titleString() {
		int formatStringId;
		if (mDirectConversion) {
			formatStringId = R.string.convert_dec_to_bin_offset;
		} else {
			formatStringId = R.string.convert_bin_offset_to_dec;
		}
		String formatString = getResources().getString(formatStringId);
		return String.format(formatString, mOffset, numberOfBits());
	}

	@Override
	protected String generateRandomNumber() {
		int numberOfBits = numberOfBits();

		int min = (int) -(Math.pow(2, numberOfBits - 1));
		int max = (int) (Math.pow(2, numberOfBits - 1)) - 1;
		int numberInDecimal = mRandomGenerator.nextInt(max - min + 1) + min;;

		// Exceso central
		mOffset = (int) Math.pow(2, numberOfBits - 1);

		if (mDirectConversion) {
			mCorrectAnswer = BinaryConverter.binaryToStringWithNbits(
					numberInDecimal + mOffset, numberOfBits);
			return Integer.toString(numberInDecimal);
		} else {
			mCorrectAnswer = Integer.toString(numberInDecimal);
			return BinaryConverter.binaryToStringWithNbits(
					numberInDecimal - mOffset, numberOfBits);
		}
	}

	@Override
	protected String obtainSolution() {
		return mCorrectAnswer;
	}

	@Override
	protected boolean isCorrect(String answer) {
		return answer.equals(mCorrectAnswer);
	}

}
